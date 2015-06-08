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
/**
 * Construct Effect assembles lines of text on the screen by character with character level offsets.  If fades in during construction and then fades out.
 * @author  Johnny Lee
 */
public class ConstructEffect extends CompositeEffect {
    /** text speed */
    public EffectParameter.BoundedDouble speed = new EffectParameter.BoundedDouble("Speed",1,0.01,10);
    /** consruction fade time
     */
    public EffectParameter.BoundedDouble fade = new EffectParameter.BoundedDouble("Construct Time (sec)",2,0,10);
    /** positional offset
     */
    public EffectParameter.Percentage offset = new EffectParameter.Percentage("Offset",50);
    
    /** percentage word time fade out
     */
     protected double fadeOutTime = .4;
     /** rotational offset range
      */
     protected double defaultRotOffset = 360;
     /** positional offset range
      */
     protected double defaultPosOffset = 300;
    
    /** default font size
     */
     protected double defaultFontSize = 40;
     /** default font family
      */
     protected String defaultFontFamily = "Arial Black";
    
     /** Emphasize Tag
      */
    protected String emphasizeTag = "Emphasize";
    /** Creates a new instance of Constuct Effect */
    
    public ConstructEffect() {
       this(new WordSegmenter());
    }
    /** creates new instance of Construct effect
     *@param segmenter text segmenter
     */
    public ConstructEffect(Segmenter segmenter) {
        super(segmenter);
        name = "Construct";
        addParameter(speed);
        addParameter(fade);
        addParameter(offset);
    }
    /** builds the animation
     *@param seq animation seuqence to be added to
     *@param seg text segmenter
     *@param g2 graphics handle
     *@param bounds drawing size
     *@param delay time delay
     *@return animation duration
     */
    public double build(Sequence seq, Segmenter seg, Graphics2D g2, Rectangle bounds, double delay) {
        mSegmenter = seg;
        double startTime = 0;
        Font f;
        boolean emphasize = false;
        double baseY = bounds.height/2.0;
        double baseX = bounds.width/2.0;
        double yVariation = (bounds.height) * (offset.getNormalizedValue());
        double xVariation = (bounds.width)* (offset.getNormalizedValue());
        double rotVariation = (defaultRotOffset)* (offset.getNormalizedValue());
        double dur;
        Sequence ConstructSeq = new Sequence("Construct text",delay,0);
        seq.addCastMember(ConstructSeq);
            
        while(mSegmenter.hasMoreSegements()){
            Segment segment = mSegmenter.getNextSegment();
            Segmenter ws = new WordSegmenter(segment);
            dur = segment.getDuration()/speed.getValue() + fade.getValue()*1000;
            Sequence lineSeq = new Sequence("Construct line", startTime,dur);
            double lineWidth = 0;
            double timeShift = 0;
            double wordDur = (segment.getDuration())/speed.getValue();
            while(ws.hasMoreSegements()){
                Segment word = ws.getNextSegment();
                if(word.getText().compareTo(" ")==0){
                    timeShift += word.getDuration();
                    continue;
                }
                if(word.hasTag("+ time")){
                    timeShift += word.getDuration();
                    continue;
                }
                
                if(word.hasTag("- time")){
                    timeShift -= word.getDuration();
                    continue;
                }
                
                if(word.attributes == null){
                    emphasize = false;
                    f = new Font(defaultFontFamily,Font.PLAIN, (int)defaultFontSize);
                }else{
                    f = word.getFont();
                    emphasize = word.hasTag(emphasizeTag);
                }
                double x_offset = 0;
                double y_offset = 0;
                double rot_offset = 0;
                KineticString k_string  = new KineticString();

                k_string.setString(word.getText());
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(baseX+ lineWidth,baseY);
                k_string.setFont(f);
                k_string.initBounds(g2);
                k_string.setColor(word.getForground());
                k_string.alpha.setValue(0);

                double segAdvance = k_string.getAdvance(g2,true);
                Sequence segmentSeq = k_string.breakIntoCharacters(0, dur+timeShift,g2);
                lineSeq.addCastMember(segmentSeq);
                if((timeShift + dur) > lineSeq.getDuration())
                    lineSeq.setDuration(timeShift + dur);
                lineWidth += segAdvance;
                for(int i = 0; i < segmentSeq.cast.size(); i++){
                    x_offset = xVariation*(Math.random()-0.5);
                    y_offset = yVariation*(Math.random()-0.5);
                    rot_offset = rotVariation*(Math.random()-0.5);

                    KineticString ks = (KineticString)segmentSeq.cast.get(i);
                    ks.alpha.addBehavior(new Change(timeShift,fade.getValue()*1000,.99));
                    ks.x.addBehavior(new Change(timeShift,0,x_offset)); //immediatley shifts it
                    ks.x.addBehavior(new Curve(timeShift,fade.getValue()*1000,1,0,-x_offset)); //slowly brings it back
                    ks.y.addBehavior(new Change(timeShift,0,y_offset)); //immediatley shifts it
                    ks.y.addBehavior(new Curve(timeShift,fade.getValue()*1000,1,0,-y_offset)); //slowly brings it back
                    ks.rotation.addBehavior(new Change(timeShift,0,rot_offset)); //immediatley shifts it
                    ks.rotation.addBehavior(new Curve(timeShift,fade.getValue()*1000,1,0,-rot_offset*.99)); //slowly brings it back
                    ks.alpha.addBehavior(new Change(timeShift+ dur - wordDur*fadeOutTime,wordDur*fadeOutTime,-.99));
                }
            }
            lineSeq.setPosition(- lineWidth/2.0,0);
            ConstructSeq.addCastMember(lineSeq);
            if((startTime + lineSeq.getDuration()) > ConstructSeq.getDuration())
                ConstructSeq.setDuration(startTime + lineSeq.getDuration());
            startTime += lineSeq.getDuration();
        }
        return startTime;
    }
}
