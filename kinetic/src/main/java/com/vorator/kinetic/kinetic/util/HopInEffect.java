/*
 * HopIn.java
 *
 * Created on August 31, 2002, 1:59 PM
 *
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

 *
 */

package com.vorator.kinetic.kinetic.util;
import com.vorator.kinetic.kinetic.*;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 * Hopin is a composite effect which will automatically animate chunks of text.  Text is broken into segments by a Segmenter Object.
 * Hopin jumps each word up onto the canvas using squash and stretch.  It varies the height each word as well as it's landind position.  Each new word appears where the previous word land making it easier to read.  Words fade out after the peak of thier jump letting newer words to take the foreground.  Pacing is determined by the Segmenter object.
 * @author  Johnny Lee
 */
public class HopInEffect extends CompositeEffect {
    
/**
 * speed of the hopping animation
 */
    public EffectParameter.BoundedDouble speed = new EffectParameter.BoundedDouble("Speed",1,0.01,10);
/**
 * energy or height of the jumping
 */
    public EffectParameter.BoundedDouble energy = new EffectParameter.BoundedDouble("Energy",1.5,0,5);
/**
 * horizontal and vertical variation
 */
    public EffectParameter.Percentage predictability = new EffectParameter.Percentage("Predictability",50);
/**
 * magnitude of squash and stretch
 */

    public EffectParameter.BoundedDouble squishiness = new EffectParameter.BoundedDouble("Squishiness",1,-10,10);
    
/**
 * base hieght of the text
 */
    protected double startingHeight = 0;
/**
 * base width 
 */
    protected double baseWidth = 0;
/**
 * base height
 */
    protected double baseHeight = 0;
/**
 * height variation
 */
    protected double heightVariation = 0;
/**
 * width variation
 */
    protected double widthVariation = 0;
/**
 * portion of the hop to begin fading.  0.5 means halfway.
 */
    protected double fadeOutTime = 0.5; //percentage of segment duration
/**
 * base vertical squish amount
 */
    protected double baseYSquishAmount = 0.3;
/**
 * base width squish amount
 */
    protected double baseXSquishAmount = -0.05;
/**
 * y squish amount
 */
    protected double ySquishAmount = 0;
/**
 * x squish amount
 */
    protected double xSquishAmount = 0;
/**
 * default font size
 */
    protected double defaultFontSize = 40;
/**
 * default duration of a hop
 */
    protected double defaultDuration = 1400;
/**
 * default font
 */
    protected String defaultFontFamily = "Arial Black";
    
/**
 * a tag that this compose effect recognizes
 */
    protected String emphasizeTag = "Emphasize";
    /** Creates a new instance of HopIn */
    
/**
 * creates a new instance of the HopInEffect
 */
    public HopInEffect() {
       this(new WordSegmenter());
    }
/**
 * creates a new instance of the HopInEffect using a provided segmenter.  The segmenter should already contain the text ot be animated.
 * @param segmenter a Segmenter object
 */

    public HopInEffect(Segmenter segmenter) {
        super(segmenter);
        name = "Hop";
        addParameter(speed);
        addParameter(energy);
        addParameter(predictability);
        addParameter(squishiness);
        addTag(emphasizeTag);
    }
    /** builds the hop in effect using the given parameters
     * @param seq the sequence that will contain the resulting animation
     * @param seg the segmenter that contains the text ot be aniamted
     * @param g2 graphics handle for intializing fonts
     * @param bounds the dimensions of the drawing canvas
     * @param delay the position time(ms) where the animation will be added to seq.
     * @return duration of the resulting animation
     */
        
    public double build(Sequence seq, Segmenter seg, Graphics2D g2, Rectangle bounds, double delay) {
        mSegmenter = seg;
        double startTime = 0;
        Font f;
        boolean emphasize = false;
        double hopDuration = defaultDuration/speed.getValue();
        baseWidth = bounds.width/2.0;
        baseHeight = energy.getValue()*bounds.height/3.0;
        heightVariation = (bounds.height/1.5) * (1.0-predictability.getNormalizedValue());
        widthVariation = (bounds.width/1.0)* (1.0-predictability.getNormalizedValue());
        startingHeight = bounds.height + defaultFontSize;
        
        ySquishAmount = baseYSquishAmount*squishiness.getValue();
        xSquishAmount = baseXSquishAmount*squishiness.getValue();
        
        Sequence HopInSeq = new Sequence("HopIn text",delay,0);
        seq.addCastMember(HopInSeq);
        
        double x1,x2,y;
        x1 = baseWidth + widthVariation * (Math.random() - 0.5);
        x2 = baseWidth + widthVariation * (Math.random() - 0.5);
        y = baseHeight + heightVariation * (Math.random() - 0.5);
        
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
            
            Sequence segmentSeq = new Sequence(segment.getText(), startTime, hopDuration);
            if((startTime + hopDuration) > HopInSeq.getDuration())
                HopInSeq.setDuration(startTime + hopDuration);
            HopInSeq.addCastMember(segmentSeq);
            if(mSegmenter.hasMoreSegements())
                startTime += segment.getDuration();
            else
                startTime += hopDuration;

            x1 = x2;
            x2 = baseWidth + widthVariation * (Math.random() - 0.5);
            y = baseHeight + heightVariation * (Math.random() - 0.5);
            
           if(emphasize){
                startTime += 2*segment.getRunTime();
                    y = baseHeight + heightVariation/2;
                while (Math.abs(x1-x2) < widthVariation/2)
                    x2 = baseWidth + widthVariation * (Math.random() - 0.5);
           }
            KineticString kString = new KineticString();
            segmentSeq.addCastMember(kString);
            kString.setString(segment.getText());
            kString.setPosition(x1,startingHeight);
            kString.setOrientation(KineticObject.MIDDLE_CENTER);
            kString.setFont(f);
            kString.initBounds(g2);
            kString.setColor(segment.getForground());
            
            kString.alpha.setValue(.99);
            kString.x.addBehavior(new Change(0,segmentSeq.getDuration(),x2 - x1));
            kString.y.addBehavior(new Hop(0,segmentSeq.getDuration(),0.7,0.7,-y));
            kString.yScale.addBehavior(new HopSecondary(0,segmentSeq.getDuration(),0.7,0.7,ySquishAmount));
            kString.xScale.addBehavior(new HopSecondary(0,segmentSeq.getDuration(),0.7,0.7,xSquishAmount));
            kString.alpha.addBehavior(new Change(segmentSeq.getDuration()*(1.0 - fadeOutTime),segmentSeq.getDuration()*fadeOutTime,-0.99));                   
        }
        return startTime;
    }
}
