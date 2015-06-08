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
 * base time fitler class.  provides all the routines the enigne needs to execute time filters
 */

abstract public class TimeFilter implements java.io.Serializable{
        /** internal property list
         */
	protected ArrayList properties = new ArrayList();
	/** common duration property
         */
	protected KineticProperty duration = new KineticProperty(Double.POSITIVE_INFINITY);
	/** flag used for completion
         */
        protected boolean isDone;
	/** creates a new instance of a time filter
         */
	public TimeFilter() {
		properties.add(duration);
	}
        /** method called by engine to calculate ne output time value
         * @param time input value of time
         * @return processed output value of time
         */
	public abstract double apply(double time);
        /** prepare internal variables/properties with unprocessed time
         *@param time value of time
         */
	public void PrepareVariables(double time){
		for(int i = 0; i < properties.size(); i++)
			((KineticProperty)properties.get(i)).update(time);
	}
        /** get property list
         *@return list of internal properties
         */
	public ArrayList getPropertyList(){
		return properties;
	}
        /** get duration
         *@return duration
         */
	public double getDuration() {
		return duration.getEndValue();
	}
        /** set duration
         *@param dur duration
         */
	public void setDuration(double dur) {
		duration.setValue(dur);
	}
	/** checks if it is done
         *@return isDone
         */
	public boolean isDone() {
		return isDone;
	}
        /** reset internal variables
         */
	public void reset() {
		for(int i = 0; i < properties.size(); i++)
			((KineticProperty)properties.get(i)).reset();
		isDone = false;
	}
        /** clears all known animation data
         */
	public void clear() {
            for(int i = 0; i < properties.size(); i++)
                    ((KineticProperty)properties.get(i)).clear();
	}
}