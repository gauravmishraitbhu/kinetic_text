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

/** 
 * This class produces a uniform change onto any attached property.
 *
 * It applies an offset to the incomming property value.  The offset is 0 when t<=0, and offset=amount when t>= duration.
 */
public class Change extends Behavior{

    /** property storing the desired offset this behavior produces
     *
     */    
	protected KineticProperty amount = new KineticProperty(10);

        /** constructs a new instance of the Change behavior with default parameters
         */        
	public Change() {
		properties.add(amount);
	}

        /** creates a new instance of the Change behavior using the parameters
         * @param delay_val time delay before starting - milliseconds
         *
         * @param dur_val lifespan of the before, time needed to complete - milliseconds
         * @param amount_val amount of offset
         *
         */        
	public Change(double delay_val, double dur_val, double amount_val)
		{
		this();
		amount.setValue(amount_val);
		duration.setValue(dur_val);
		addTimeFilter(new Delay(delay_val));
		}

        /** sets the amount of offset
         * @param new_amount new offset
         */        
	public void setAmount(double new_amount) {
		amount.setValue(new_amount);
	}
	
        /** main routine that produces that effect
         */        
	public void Process() {
            if(owner != null) {
                if(current_time <= 0) //causal function
                        return;
                if(current_time < duration.endValue) {
                        owner.endValue += (current_time/duration.endValue)*amount.endValue;
                        isDone = false;
                        }
                else {
                        owner.endValue += amount.endValue;
                        isDone = true;
                        }
            }
	}
}
