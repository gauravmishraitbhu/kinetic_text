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
 * Time filter that will perform a slow-in slow-out pacing operation on a system.  In and out range between (0,0) = slow start and stop, (1,1) = fast start and stop
 */
public class PaceInOut extends TimeFilter{
        /** maintains starting speed
         */
	public KineticProperty in = new KineticProperty(0.5,0.0,1.0);
	/** maintains ending speed
         */
        public KineticProperty out = new KineticProperty(0.5,0.0,1.0);

        /**creates a new instance of PaceInout
         *@param new_in start speed
         *@param new_out end speed
         *@param dur lifespan - ms
         */
	public PaceInOut(double new_in, double new_out, double dur) {
		properties.add(in);
		properties.add(out);
		in.setValue(new_in);
		out.setValue(new_out);
		duration.setValue(dur);
	}
        /** performs calculation on input vluae of time
         *@param time input value of time
         *@return processed output value of time
         */
	public double apply(double time) {
		if((time < 0)||(time > duration.getEndValue())) //only valid in given range
			return time;
			
		PrepareVariables(time);//process properties first.
		//2-point beizer curver bound to unit square (0,0) to (1,1)
		double i = in.endValue;
		double o = 1.0 - out.endValue;
		double t = time/(duration.getEndValue());
		double c = 3.0 * i;
		double b = 3.0 *(o - i) - c;
		double a = 1.0 - c - b;
		return (duration.getEndValue())*(a*t*t*t + b*t*t + c*t);
	}
	
}