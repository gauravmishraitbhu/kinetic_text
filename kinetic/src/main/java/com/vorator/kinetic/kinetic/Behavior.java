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

package com.vorator.kinetic.kinetic;
import java.awt.*;
import java.lang.*;
import java.util.*;

/** 
 * base class used for all behaviors.  provides the necessary routines that the engine calls to run the animation.
 */
public abstract class Behavior implements java.io.Serializable{

    /** stores what property this behavior is attached to
     *
     */    
	protected KineticProperty owner;

        /** list of properties the behavior has
         */        
	protected ArrayList properties = new ArrayList();
        /** list of time filters attached to the behavior
         */        
	protected ArrayList timeFilters = new ArrayList();

        /** standard duration property
         */        
	public KineticProperty duration = new KineticProperty(Double.POSITIVE_INFINITY);
	
        /** stores the current time
         *
         */        
	protected double current_time;
	
        /** flag used to indicate completion
         *
         */        
	public boolean isDone;

        /** constructs and empty behavior
         */        
	protected Behavior() {
		properties.add(duration);
	}

        /** sets the owner of this property
         * @param p the owner of this behavior
         *
         */        
	public void setOwner(KineticProperty p){
		owner = p;
	}

        /** method called when the animation time is updated
         * @param time new animation
         *
         */        
	public void apply(double time) {
		if(owner != null) {
			PrepareVariables(time);//process properties first.
			Process();
		}
	}

        /** main routine that calculates a new value for the owner property
         *
         */        
	public abstract void Process();

        /** utility function to update that values all of internal properties and time filters.  Returns the value of time after being processed by any timefilters.
         *
         * @param time aniamtion update time in milliseconds
         * @return the value of time after being processed by the timefilters this time should be used by the remainder of processing
         */        
	public double PrepareVariables(double time){
		current_time = time;
		//process the time with time filters.
		for(int i = 0; i < timeFilters.size(); i++)
			current_time = ((TimeFilter)timeFilters.get(i)).apply(current_time);
		//then run the behaviors for each of it's properties.
		for(int i = 0; i < properties.size(); i++)
			((KineticProperty)properties.get(i)).update(current_time);
		return current_time;
	}

        /** adds a new entry to this behavior's time filter list
         * @param tf instance of a timefilter
         *
         */        
	public void addTimeFilter(TimeFilter tf){
		if(tf == null)
			return;
		timeFilters.add(tf);
	}
	
        /** return the list of internal properties
         * @return internal list of properties
         *
         */        
	public ArrayList getPropertyList(){
		return properties;
	}
	
        /** returns the duration of the behavior
         * @return duration of the behavior in milliseconds
         *
         */        
	public double getDuration() {
		return duration.getEndValue();
	}
	
        /** returns true is the behavior has completed (currentTime > duration)
         * @return returns true if the behavior has completed
         *
         */        
	public boolean isDone() {
		return isDone;
	}
	
        /** resets all/any persistent values inside the behavior
         */        
	public void reset() {
		isDone = false;
		//reset time filters
		for(int i = 0; i < timeFilters.size(); i++)
			((TimeFilter)timeFilters.get(i)).reset();
		//reset all properties.
		for(int i = 0; i < properties.size(); i++)
			((KineticProperty)properties.get(i)).reset();	
	}
        /** clears all animation data this behavior knows about
         */
	public void clear() {
		//reset time filters
		for(int i = 0; i < timeFilters.size(); i++)
			((TimeFilter)timeFilters.get(i)).clear();
                timeFilters.clear();
                //reset all properties.
		for(int i = 0; i < properties.size(); i++)
			((KineticProperty)properties.get(i)).clear();
	}
    /** gets the list of timefilters for this object
     *@return list of time filters
     */
    public ArrayList getTimeFilterList(){
            return timeFilters;
    }
}
