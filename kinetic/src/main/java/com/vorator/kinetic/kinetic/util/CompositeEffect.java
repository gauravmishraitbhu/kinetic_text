/*
 * CompositeEffect.java
 *
 * Created on August 31, 2002, 1:32 PM
 *  
 * Kinetic Typography Engine - java library for animating expressive text
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
 *
 *
 */

package com.vorator.kinetic.kinetic.util;
import com.vorator.kinetic.kinetic.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 * Composite Effect encapsulates complex generalized aniatmion templates.  It provides an interface for connecting to a segmenter which produces segmenet of words and manages the progression of time through an effect.  This is a base class to be extended by specific effects.
 *
 * It alos makes efforts in being able to describe itself in enough detail to a UI
 * @author  Johnny Lee
 */
public abstract class CompositeEffect {
    /** effect name
     */
    protected String name;
    /** text segmenter
     */
    protected Segmenter mSegmenter;
    /** list of parameters
     */
    protected ArrayList mParameterList = new ArrayList();
    /** list of tags
     */
    protected ArrayList mTagList = new ArrayList();
    
    /** Creates a new instance of CompositeEffect */
    public CompositeEffect() {
        this(new WordSegmenter());
    }
    /** creates a new instance of CompositeEffect with a segmenter 
     *@param segmenter a text segmenter
     */
    public CompositeEffect(Segmenter segmenter) {
        if(segmenter == null)
            setSegmenter(new WordSegmenter());
        else
            setSegmenter(segmenter);
    }
    /** routine to be called when the animation is requested to be built
     *@param seq sequence to have the anmiation added to
     *@param seg segmenter that will feed the animation template
     *@param g2 graphics handle
     *@param bounds drawing window size
     *@param delay time delay before animation should start - ms
     *@return the length of the resulting animation sequence ms
     **/
    public abstract double build(Sequence seq, Segmenter seg, Graphics2D g2, Rectangle bounds, double delay);

    /** helper build routine when no segmeneter is provided, word segmenter is defaulted
     *@param seq sequence to have the anmiation added to
     *@param g2 graphics handle
     *@param bounds drawing window size
     *@param delay time delay before animation should start - ms
     *@return the length of the resulting animation sequence ms
     **/    
    public double build(Sequence seq, Graphics2D g2, Rectangle bounds, double delay) {
        if(mSegmenter == null)
            return 0;
        else
            return build(seq,mSegmenter,g2,bounds,delay);
    }
    /** set segmenter
     *@param newSegmenter new segmenter
     */
    public void setSegmenter(Segmenter newSegmenter){
        mSegmenter = newSegmenter;
    }
    /** register/add paramter for UI
     *@param p instance of parameter
     */
    protected void addParameter(EffectParameter p){
        mParameterList.add(p);
    }
    /** register/add tag for UI
     *@param tag name of a tag
     */
    protected void addTag(String tag){
        mTagList.add(tag);
    }
    /** get paramter by index
     *@param index parameter index
     *@return indexed paramter
     */
    protected Object getParameter(int index){
        return mParameterList.get(index);
    }

    /** get paramter list
     *@return paramter list
     */
    public ArrayList getParameterList(){
        return mParameterList;
    }
    /** get tag list
     *@return list of tags
     */
    public ArrayList getTagList(){
        return mTagList;
    }

    /** set parameters
     *@param values array of paramter type objects
     */
    public void setParameters(Object[] values){
        int size = Math.min(mParameterList.size(), values.length);
        EffectParameter ep;
        for(int i = 0; i < size; i++) {
            ep = (EffectParameter)mParameterList.get(i);
            ep.setCurrentObject(values[i]);
        }
    }
    /** set the value object of an indexed parameter
     *@param index parameter index
     *@param o value object
     */
    public void setParamterCurrentObject(int index,Object o){
       ((EffectParameter)mParameterList.get(index)).setCurrentObject(o);
    }
    /** get name
     *@return name
     */
    public String getName(){
        return name;
    }
}
