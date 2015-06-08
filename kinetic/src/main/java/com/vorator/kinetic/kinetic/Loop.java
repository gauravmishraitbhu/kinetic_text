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

/** 
 * time filter used to easily produce repetion
 */
public class Loop extends TimeFilter{
        /** property to maintain the period of the loop - ms
         */
	public KineticProperty period = new KineticProperty(1000);
        /** create a new isntance of Loop with a given looping period -ms, lasts forever
         *@param period_val looping period -ms
         */
	public Loop(double period_val) {
		this(period_val, Double.POSITIVE_INFINITY);
	}
	/** create new instance of Loop with a given period and life span
         *@param period_val looping period - ms
         *@param dur lifespan of the loop - ms
         */
	public Loop(double period_val, double dur) {
		properties.add(period);
		period.setValue(period_val);
		duration.setValue(dur);
	}
        /** executes the timefilter on the passed value of time
         *@param time input value of time
         *@return processed value of time
         */
	public double apply(double time) {
		PrepareVariables(time);//process properties first.
		if((time < 0)||(time > duration.getEndValue())) //only valid in given range
			return time;			
		if(period.getEndValue() < 1.0)
			return 0;
		else
			return (time % period.getEndValue());
	}
}