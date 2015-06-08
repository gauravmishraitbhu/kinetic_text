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

package com.vorator.kinetic.kinetic;
import java.awt.*;
import java.lang.*;
import java.util.*;

/** 
 * basic class used for storing animatable values in the engine.  Stores a continous value with bounding information.  Contains a raw unprocessed value with is used at the initial state of each frame.  Contains an end)value which contains the process value used to actualyl render the data to the screen.
 */

public class KineticProperty implements java.io.Serializable {
        /** upper bound
         */
	protected double upper_bound;
	/** lower bound
         */
         protected double lower_bound;
	/** raw unprocessed value
         */
         protected double initialValue;
	/** processed value used to render frame
         */
         protected double endValue;

        /** indicates if the value shas changed
         */
         protected boolean changed = false;

         /** the owner of this property
          */
	protected KineticObject owner;
        /** list of behaviors atached to this property
         */
	private ArrayList behaviors = new ArrayList();

        /** construct s anew instance of a KineticPropert
         **/
	public KineticProperty() {
		upper_bound = Double.POSITIVE_INFINITY;
		lower_bound = Double.NEGATIVE_INFINITY;
		clipValue();
	}
        /** create new instance with an initial  value
         *@param new_val property value
         */
	public KineticProperty(double new_val) {
		upper_bound = Double.POSITIVE_INFINITY;
		lower_bound = Double.NEGATIVE_INFINITY;
		setValue(new_val);
	}
        /** create new instance with an initial value and bounds
         *@param new_val property value
         *@param l_bound lower bound
         *@param u_bound upper bound
         */
	public KineticProperty(double new_val, double l_bound, double u_bound) {
		upper_bound = u_bound;
		lower_bound = l_bound;
		setValue(new_val);
	}
        /** static utility routine to clip values to given range
         *@param test_value value to clip
         *@param upper upper bound
         *@param lower lower bound
         *@return clipped value
         */
	public static double limit(double test_value, double upper, double lower){
		if(test_value > upper)
			return upper;
		if(test_value < lower)
			return lower;
		return test_value;
	}
        /** clips the internal value to the bounds
         */
	public void clipValue(){
		endValue = limit(endValue, upper_bound, lower_bound);
	}
        /** sets the values of this property with a long
         *@param val new property value
         */
	public void setValue(long val){
		setValue((double)val);
	}
        /** sets the value of this property
         *@param val new property value
         */
	public void setValue(double val){
		initialValue = limit(val, upper_bound, lower_bound);
		endValue = initialValue; //makes sure there somethign there to draw
	}
        /** get the unprocessed initial value of the property
         *@return gets unprocessed value of the property
         */
	public double getInitialValue() {
		return initialValue;
	}

        /** gets the processed value of this property
         *@return processed value of the property
         */
	public double getEndValue() {
		return endValue;
	}
        /** queries if the value changed during last update
         *@return true if value changed during last update
         */
	public boolean hasChanged() {
		return changed;
	}

        /** sets the raw value to the upper bound
         */
	public void setMaxValue() {
		initialValue = upper_bound;
	}
        /** sets the raw value to the lower bound
         */
	public void setMinValue() {
		initialValue = lower_bound;
	}
        /** sets the own of this property
         *@param obj new owner
         */
	public void setOwner(KineticObject obj) {
		owner = obj;
	}
        /** add a behavior to this property
         *@param b instance of a behavior
         */
    	public void addBehavior(Behavior b){
		behaviors.add(b);
		b.setOwner(this);
		//return b;
	}
        /** calculate the value for this property given the value of time
         *@param time value of time - ms
         */
	public void update(double time){
                double lastValue = endValue;
		endValue = initialValue;//reset the data
		for(int i = 0; i < behaviors.size(); i++) {
			((Behavior)behaviors.get(i)).apply(time);
			}
		clipValue();
                if(lastValue != endValue)
                    changed = true;
                else
                    changed = false;
	}

        /** used to support interactivity
         *@param e mouse event object
         */
	public void handleMouseEvent(java.awt.event.MouseEvent e){
	}
        /** restore the raw value into the end value
         */
	public void resetEndValue(){
                if(endValue != initialValue)
                    changed = true;
                else
                    changed = false;
		endValue = initialValue;
	}
        /** reset internal variables in the property
         */
	public void reset() {
		resetEndValue();
		for(int i = 0; i < behaviors.size(); i++) {
			((Behavior)behaviors.get(i)).reset();
			}
	}
        
        /** clears all animation data this property knows about
         */
        public void clear(){
            resetEndValue();
            for(int i = 0; i < behaviors.size(); i++)
                    ((Behavior)behaviors.get(i)).clear();
            behaviors.clear();
        }
        
        /** run the behavior on this property with this value of time
         *@param b instance of a behavior
         *@param time value of time - ms
         */
	public void applyBehavior(Behavior b,double time){
		b.owner = this;
		b.apply(time);
		clipValue();
	}
        
    /** gets the list of behaviors attached to this property
     *@return list of behaviors
     */
    public ArrayList getBehaviorList(){
            return behaviors;
    }

}