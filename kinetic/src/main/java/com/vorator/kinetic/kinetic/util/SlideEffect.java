/**
 * * Kinetic Typography Engine - java library for animating expressive text
 * Copyright (C) 2002 Johnny Chung Lee 
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

 */

package com.vorator.kinetic.kinetic.util;
import com.vorator.kinetic.kinetic.*;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
/** segments are presented in times square format
 * @author Johnny Lee
 */
public class SlideEffect extends CompositeEffect {
    
    public EffectParameter.BoundedDouble speed = new EffectParameter.BoundedDouble("Speed (pts/sec)",-150,-500,100);
    public EffectParameter.BoundedDouble life = new EffectParameter.BoundedDouble("Life Span (sec)",2,0,10);
    public EffectParameter.Percentage pacing = new EffectParameter.Percentage("Pacing (% of movement)",100);
    
    //percentages of total duration
    protected double defaultAttack = .3;
    protected double defaultRelease = .3;
    protected double defaultSpeed = 50; //pixels/sec
   
    protected double defaultFontSize = 40;
    protected String defaultFontFamily = "Arial Black";
    
    protected String emphasizeTag = "Emphasize";
    
    public SlideEffect() {
       this(new WordSegmenter());
    }

    public SlideEffect(Segmenter segmenter) {
        super(segmenter);
        name = "Slide";
        addParameter(speed);
        addParameter(life);
        addParameter(pacing);
    }
          
    public double build(Sequence seq, Segmenter seg, Graphics2D g2, Rectangle bounds, double delay) {
        mSegmenter = seg;
        double startTime = 0;
        Font f;
        boolean emphasize = false;
        double baseY = bounds.height/2.0;
        double baseX = 0;
        if(speed.getValue() < 0)
            baseX = (-life.getValue()*speed.getValue() + bounds.width)/2.0;
        double dur = 0;
        double position = 0;
        double segWidth = 0;
        double totalSegWidth = 0;
        int segCount = 0;
        Sequence SlideSeq = new Sequence("Slide text",delay,0);
        seq.addCastMember(SlideSeq);
        double aVeryLongTime = Integer.MAX_VALUE;
        SlideSeq.x.addBehavior(new Change(0,aVeryLongTime,speed.getValue()*aVeryLongTime/1000));
        while(mSegmenter.hasMoreSegements()){
            Segment segment = mSegmenter.getNextSegment();
            
            if(segment.getText().compareTo(" ")==0){
                    startTime += segment.getDuration();
                    continue;
                }
            if(segment.hasTag("+ time")){
                    startTime += segment.getDuration();
                    continue;
                }
            
            if(segment.hasTag("- time")){
                    startTime -= segment.getDuration();
                    continue;
                }
            if(segment.attributes == null){
                emphasize = false;
                f = new Font(defaultFontFamily,Font.PLAIN, (int)defaultFontSize);
            }else{
                f = segment.getFont();
                emphasize = segment.hasTag(emphasizeTag);
            }
            dur = life.getValue()*1000;
            KineticString k_string  = new KineticString();
            k_string.setString(segment.getText());
            k_string.setOrientation(KineticObject.BOTTOM_LEFT);
            k_string.setColor(segment.getForground());
            k_string.alpha.setValue(0);
            k_string.setFont(f);
            k_string.initBounds(g2);
            k_string.setPosition(baseX + position,baseY);
             
            k_string.alpha.addBehavior(new Change(0,defaultAttack*dur,.99));
            k_string.alpha.addBehavior(new Change(dur*(1-defaultRelease),dur*defaultRelease,-.99));

            segWidth = k_string.getAdvance(g2,true);
            position += segWidth;
            segCount++;
            Sequence segmentSeq = new Sequence("Segment",startTime,dur);
            segmentSeq.addCastMember(k_string);            
            if((startTime + dur) > SlideSeq.getDuration())
                SlideSeq.setDuration(startTime + dur);
            SlideSeq.addCastMember(segmentSeq);
            startTime += 1000*(pacing.getValue()/100.0)*segWidth/Math.abs(speed.getValue());
        }
        if(speed.getValue() < 0)
            SlideSeq.setPosition(-position/segCount,0); //tries to center the text
        return startTime + dur - 1000*(pacing.getValue()/100.0)*segWidth/Math.abs(speed.getValue());
    }
}
