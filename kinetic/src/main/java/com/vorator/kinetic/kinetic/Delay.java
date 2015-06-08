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
 * Basic time filter that shifts the incommin time by a given amount.  This allows an object to be delayed or shifted forward in time.
 */
public class Delay extends TimeFilter{

    /** property holdin the time shift amount
     */    
	public KineticProperty amount = new KineticProperty(0);

        /** constructs a new instance of Delay
         * @param amount_val time shift amount
         *
         */        
	public Delay(double amount_val) {
		this(amount_val, Double.POSITIVE_INFINITY);
	}
	
        /** constructs a new instance of Delay
         * @param amount_val time shift amount - ms
         * @param dur set the duration in which this timefilter is valid -ms
         */        
	public Delay(double amount_val, double dur) {
		properties.add(amount);
		amount.setValue(amount_val);
		duration.setValue(dur);
	}

        /** main routtine that calulates the new value of time
         * @param time incomming value of time - ms
         * @return the new value of time
         *
         */        
	public double apply(double time) {
		PrepareVariables(time);//process properties first.
		if((time < 0)||(time > duration.getEndValue()))
			return time;
		return (time - amount.getEndValue());
	}
}
