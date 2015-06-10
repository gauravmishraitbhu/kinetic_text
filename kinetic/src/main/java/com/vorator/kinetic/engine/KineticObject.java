/**
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
 */

package com.vorator.kinetic.engine;
import java.awt.*;
import java.lang.*;
import java.util.*;
import java.awt.geom.*;
/** 
 * base class for all drawing objects in the engine
 */
public abstract class KineticObject implements java.io.Serializable{
        /** stores the paraent object if it exists
         */
	protected KineticObject parent = null;
	/** list of properties for this object
         */
        protected ArrayList properties = new ArrayList();
        /** list of time filters
        */
        protected ArrayList timeFilters = new ArrayList();        
        /** x position property
         */
	public KineticProperty x = new KineticProperty(0);
	/** y position property
         */
        public KineticProperty y = new KineticProperty(0);
	/** rotation property
         */
        public KineticProperty rotation = new KineticProperty(0);
	/** horizontal scale
         */
        public KineticProperty xScale = new KineticProperty(1.0);
	/** vertical scale
         */
        public KineticProperty yScale = new KineticProperty(1.0);
	/** horizontal shear
         */
        public KineticProperty xShear = new KineticProperty(0);
	/** vertical shear
         */
        public KineticProperty yShear = new KineticProperty(0);

        /** red color channel value
         */
	public KineticProperty red = new KineticProperty(0,0,1);
	/** green color channel value
         */ 
        public KineticProperty green = new KineticProperty(0,0,1);
	/** blue color channel value
         */
        public KineticProperty blue = new KineticProperty(0,0,1);
	/** alpha channel value
         */
        public KineticProperty alpha = new KineticProperty(1.0,0,1);
        /** sequence duration - this is used for temporal culling
        */
        public KineticProperty duration = new KineticProperty(Double.POSITIVE_INFINITY);
        /** accessor pointer to the common delay all seqeunces have
         */
        public Delay delay = new Delay(0);

        
	/** flag for visibility
         */
        protected boolean visible = true;
	/** slection flag for editting
         */
        protected boolean selected = false;
	
        /** stores that last tim ethis object was updated
         */
        protected double lastUpdateTime;
        
        /** centerpoint justification
         */
        protected int orientation = 0;
	/** utility varliable for mode switching - currently unused
         */
        protected int mode = 0;
        // 0 = editting mode
        // 1 = running mode

        /** justification option
         */
	public static final int BOTTOM_LEFT = 0;
        /** justification option
         */
	public static final int BOTTOM_CENTER = 1;
        /** justification option
         */
	public static final int BOTTOM_RIGHT = 2;

        /** justification option
         */
	public static final int MIDDLE_LEFT = 3;
        /** justification option
         */
	public static final int MIDDLE_CENTER = 4;
        /** justification option
         */
	public static final int MIDDLE_RIGHT = 5;

        /** justification option
         */
	public static final int TOP_LEFT = 6;
        /** justification option
         */
	public static final int TOP_CENTER = 7;
        /** justification option
         */
	public static final int TOP_RIGHT = 8;

        /** name of this object
         */
	public String name;
        /** creates a new instance of a kinetic object
         */
	public KineticObject() {
            initialize();
	}

        /** creates a new instance of a kinetic object
         *@param n name
         *@param del delay - ms
         *@param dur dur - ms
         */
	public KineticObject(String n, double del, double dur) {
            initialize();
            name = n;
            duration.setValue(dur);
            delay.amount.setValue(del);
	}

        /** initializes and registers internal variables
         */
        public void initialize(){
            addProperty(duration);
            addTimeFilter(delay);
            duration.setValue(Double.POSITIVE_INFINITY);
            addProperty(x);
            addProperty(xScale);
            addProperty(xShear);
            addProperty(y);
            addProperty(yScale);
            addProperty(yShear);
            addProperty(rotation);
            addProperty(red);
            addProperty(green);
            addProperty(blue);
            addProperty(alpha);            
        }
        
        /** called when this object is requested to be drawn
         *@param g2 graphics handle
         */
    public void draw(Graphics2D g2){
     		draw(g2, new AffineTransform());
    }
        /** called when this object is requested to be drawn, provides a transformation matrix from parent
         * @param g2 graphcis handle
         * @param ax current affine transform
         */
    public abstract void draw(Graphics2D g2, AffineTransform ax);
    /** used for hit testing purposes
     * @param p location of the hit test point
     * @return true if it contains the hits object
     *
     */
    public abstract boolean contains(Point p);
    
    /** updates this object with the new time
     *@param time value of update time - ms
     */
    public void update(double time){
            lastUpdateTime = time;
            double curr_time = PrepareVariables(time);
            if((curr_time > duration.getEndValue())||(curr_time < 0)) {
                    visible = false;
                    return;
                    }
            else
                    visible = true;
            if(curr_time < 0) //limits exectution
                    return;
    }
    /** prepare all properties with the filtered value of time
     *@param time input time
     *@return processed output time
     */
    public double PrepareVariables(double time){
            double curr_time = time;
            //process the time with time filters.
            for(int i = 0; i < timeFilters.size(); i++)
                    curr_time = ((TimeFilter)timeFilters.get(i)).apply(curr_time);
            //then run the behaviors for each of it's properties.
            for(int i = 0; i < properties.size(); i++)
                    ((KineticProperty)properties.get(i)).update(curr_time);
            return curr_time;
    }
    /** called when the update is finished and provides a method of doing more processing
     *@param time post-processed input time
     */
    public void onUpdate(double time){};
    
    /** Called when all animation data is to be erased. Clears all time filters attached to this object and calls clear() on all properties.  Should be overridden if more tasks are neccesary.
     */    
    public void clear(){
        for(int i = 0; i < timeFilters.size(); i++)
                ((TimeFilter)timeFilters.get(i)).clear();
        timeFilters.clear();
        for(int i = 0; i < properties.size(); i++)
                ((KineticProperty)properties.get(i)).clear();
    };
    
    /** sets orientation
     *@param orient orientation option
     */
    public void setOrientation(int orient) {
            orientation = orient;
    }
    /** sets the name of this object
     *@param n new name
     */
    public void setName(String n) {
            name = n;
    }
    /** gets the name of this object
     *@return name of the object
     */
    public String getName() {
            return name;
    }
    /** gets the list of properties in this object
     *@return list of internal properties
     */
    public ArrayList getPropertyList(){
            return properties;
    }
    /** gets the list of timefilters for this object
     *@return list of time filters
     */
    public ArrayList getTimeFilterList(){
            return timeFilters;
    }
    /** called to reset internal variables used in this object
     */
    public void reset(){
        for(int i = 0; i < properties.size(); i++)
            ((KineticProperty)properties.get(i)).reset();
        for(int i = 0; i < timeFilters.size(); i++)
            ((TimeFilter)timeFilters.get(i)).reset();
        
    }
    
    /** used to support interactive capabilites
     *@param e mouse event
     *@param button_event true if event was produced by a button
     *@param time timestamps of the event
     *@param g2 graphics handle
     */
    public void handleMouseEvent(java.awt.event.MouseEvent e, boolean button_event, double time, Graphics2D g2){

    }
    /** set the current mode
     *@param newMode new mode value
     */
    public void setMode(int newMode){
            mode = newMode;
    }
    /** set delay
     *@param delayVal new delay time -ms
     */
    public void setDelay(double delayVal){
        delay.amount.setValue(delayVal);
    }
    
        /** add a new time filter
     *@param tf instance of a time filter
     */
    public void addTimeFilter(TimeFilter tf){
            if(tf == null)
                    return;
            timeFilters.add(tf);
	}
    /** set position
     * @param x_val x position
     * @param y_val y position
     */
    public void setPosition(double x_val, double y_val) {
		x.setValue(x_val);
		y.setValue(y_val);
        }
        /** set scale
         *@param x_val x scale
         *@param y_val y scale
         */
    public void setScale(double x_val, double y_val) {
		xScale.setValue(x_val);
		yScale.setValue(y_val);
        }
        /** set rotation
         *@param rot new rotation
         */
    public void setRotation(double rot) {
		rotation.setValue(rot);	
        }
	/** get duration
         *@return duration
         */
    public double getDuration() {
            return duration.getEndValue();
        }
        /** set duration
         *@param d new duration
         */
    public void setDuration(double d) {
            duration.setValue(d);
        }
    
    /** get delay
    *@return delay - ms
    */
    public double getDelay(){
    return delay.amount.getEndValue();
    }
        /** set color with alpha
         *@param r red channel
         *@param g green channel
         *@param b blue channel
         *@param a alpha channel
         */
	public void setColor(double r, double g, double b, double a){
		red.setValue(r);
		green.setValue(g);
		blue.setValue(b);
		alpha.setValue(a);
	}
        /** set color, alpha = 1.0
         *@param r red channel
         *@param g green channel
         *@param b blue channel
         */
	public void setColor(double r, double g, double b){
		red.setValue(r);
		green.setValue(g);
		blue.setValue(b);
	}

        /** set color, alpha = 1.0
         *@param c an instance of java.awt.Color
         */
	public void setColor(Color c){
            red.setValue(c.getRed()/255.0);
            green.setValue(c.getGreen()/255.0);
            blue.setValue(c.getBlue()/255.0);
            alpha.setValue(c.getAlpha()/255.0);
	}

        /** utility function for registering a new property onto this object
         * @param p the KineticProperty to be added
         */        
        protected void addProperty(KineticProperty p){
            p.setOwner(this);
            properties.add(p);
        }
        
        /** gets maximum or unprocessed value of duration
         * @return the maximum unprocessed duration
         */
        public double getMaxDuration(){
            return duration.getInitialValue();
    }
} // End KineticObject class
