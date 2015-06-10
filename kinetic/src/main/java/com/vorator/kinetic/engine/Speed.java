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
/** 
 * timeplfilter the cahnges the rate of time by multiplying it.  2 -> twice as fast, 0.5 -> half normal speed
 */
public class Speed extends TimeFilter{
        /** maintains speed change amount
         */
	public KineticProperty amount = new KineticProperty(1);
        /** creates new instance of Speed with a given amount
         *@param amount_val time multiplier
         */
	public Speed(double amount_val) {
		this(amount_val, Double.POSITIVE_INFINITY);
	}
        /** creates new instance of speed with given multiplier and lifespan
         *@param amount_val time multiplier
         *@param dur (prefiltered)duration this filter is active for
         */
	public Speed(double amount_val, double dur) {
		properties.add(amount);
		amount.setValue(amount_val);
		duration.setValue(dur);
	}
        /** calculates new output value of time given input value of time
         * @param time value of time
         * @return processed out value of time
         */
	public double apply(double time) {
		PrepareVariables(time);//process properties first.
		if((time < 0)||(time > duration.getEndValue())) //only valid in given range
			return time;
		return (time * amount.getEndValue());
	}
}
