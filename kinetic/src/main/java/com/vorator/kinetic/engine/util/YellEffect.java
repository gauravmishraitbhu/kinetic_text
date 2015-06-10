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

package com.vorator.kinetic.engine.util;
import com.vorator.kinetic.engine.*;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
/** produces a yelling effect of the segments on the screen
 * @author Johnny Lee
 */
public class YellEffect extends CompositeEffect {
    
    public EffectParameter.BoundedDouble speed = new EffectParameter.BoundedDouble("Speed",1,0.01,10);
    public EffectParameter.BoundedDouble energy = new EffectParameter.BoundedDouble("Energy",1.5,1,10);
    public EffectParameter.BoundedDouble shaking = new EffectParameter.BoundedDouble("Shaking",1,0,5);
    public EffectParameter.Percentage predictability = new EffectParameter.Percentage("Predictability",75);
    
    //percentages of total duration
    protected double fadeInTime = .1;
    protected double fadeOutTime = .5;
    protected double shrinkTime = .05;
    protected double whiteValue = 0.8;
    protected double lingerDuration = 1000;
    
    
    protected double growAmount = 130;
    protected double shrinkAmount = -40;
    protected double defaultJitterAmount = 5;
    protected double defaultFontSize = 130;
    protected String defaultFontFamily = "Arial Black";

    
    protected String emphasizeTag = "Emphasize";
    /** Creates a new instance of HopIn */
    
    public YellEffect() {
       this(new WordSegmenter());
    }

    public YellEffect(Segmenter segmenter) {
        super(segmenter);
        name = "Yell";
        addParameter(speed);
        addParameter(energy);
        addParameter(predictability);
        addParameter(shaking);
        //addTag(emphasizeTag);
    }
          
    public double build(Sequence seq, Segmenter seg, Graphics2D g2, Rectangle bounds, double delay) {
        mSegmenter = seg;
        double startTime = 0;
        Font f;
        boolean emphasize = false;
        double jitterAmount = shaking.getValue()*defaultJitterAmount;
        double baseY = bounds.height/2.0;
        double baseX = bounds.width/2.0;
        double yVariation = (bounds.height) * (1.0-predictability.getNormalizedValue());
        double xVariation = (bounds.width)* (1.0-predictability.getNormalizedValue());
        double growSize = defaultFontSize;
        double dur;
        Sequence YellSeq = new Sequence("Yell text",delay,0);
        seq.addCastMember(YellSeq);
        
        
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
                growSize = f.getSize();
                emphasize = segment.hasTag(emphasizeTag);
            }
            dur = (segment.getDuration()+lingerDuration)/speed.getValue();
            Sequence segmentSeq = new Sequence(segment.getText(), startTime, dur);
            if((startTime + dur) > YellSeq.getDuration())
                YellSeq.setDuration(startTime + dur);
            YellSeq.addCastMember(segmentSeq);
	    startTime += segment.getDuration()/speed.getValue();

            double x = baseX + xVariation * (Math.random() - 0.5);
            double y = baseY + yVariation * (Math.random() - 0.5);

            KineticString k_string  = new KineticString();
            segmentSeq.addCastMember(k_string);
            k_string.setString(segment.getText());
            k_string.setOrientation(KineticObject.MIDDLE_CENTER);
            k_string.setPosition(x,y);
            k_string.setColor(segment.getForground());
            k_string.alpha.setValue(.99);
            k_string.setFont(f);
            k_string.initBounds(g2);
            k_string.setScale(0,0);
            k_string.alpha.addBehavior(new Change(dur*(1-fadeOutTime),dur*fadeOutTime,-.99));
            k_string.xScale.addBehavior(new Curve(0,dur*fadeInTime,0,1,energy.getValue()));
            k_string.xScale.addBehavior(new Change(dur*fadeInTime,dur*shrinkTime,-(energy.getValue()-1)));
            k_string.yScale.addBehavior(new Curve(0,dur*fadeInTime,0,1,energy.getValue()));
            k_string.yScale.addBehavior(new Change(dur*fadeInTime,dur*shrinkTime,-(energy.getValue()-1)));
            k_string.red.addBehavior(new Change(dur*fadeInTime,dur*shrinkTime,whiteValue));
            k_string.green.addBehavior(new Change(dur*fadeInTime,dur*shrinkTime,whiteValue));
            k_string.blue.addBehavior(new Change(dur*fadeInTime,dur*shrinkTime,whiteValue));
            k_string.x.addBehavior(new Jitter(0,dur,jitterAmount,6));
            k_string.y.addBehavior(new Jitter(0,dur,jitterAmount,6));
            k_string.rotation.addBehavior(new Jitter(0,dur,jitterAmount,6));                 
        }
        return startTime;
    }
}
