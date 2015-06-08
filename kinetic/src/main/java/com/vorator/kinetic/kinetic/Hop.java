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
 * generates an upside down parabolic like offset.  The speed at the start and end points are adjustable as well as the hop height.  A parabola is approximated by setting in and out to both 0.7.  Amount may be negative to flip the result.
 */
public class Hop extends Behavior{

    /** property to hold the starting speed
     */    
	public KineticProperty in = new KineticProperty(0.66);
        /** property to hold the ending speed
         */        
	public KineticProperty out = new KineticProperty(0.66);
        /** property to hold the hop hieght
         */        
	public KineticProperty amount = new KineticProperty(10);

        /** creates a new instance of the Hop behavior
         */        
	public Hop() {
		properties.add(in);
		properties.add(out);
		properties.add(amount);
	}

        /** creates a new isntance of the Hop behavior with only Hop parameters
         * @param new_in starting speed
         * @param new_out ending speed
         * @param new_amount offset amount
         */        
	public Hop(double new_in,double new_out, double new_amount) {
		this();
		in.setValue(new_in);
		out.setValue(new_out);
		amount.setValue(new_amount);
	}

        /** creates a new instance of the Hop behavior with more parameters
         * @param delay_val delay - ms
         * @param dur_val duration - ms
         * @param new_in startin speed
         * @param new_out ending speed
         * @param new_amount offset amount
         */        
	public Hop(double delay_val, double dur_val, double new_in,double new_out, double new_amount) {
		this();
		in.setValue(new_in);
		out.setValue(new_out);
		amount.setValue(new_amount);
		duration.setValue(dur_val);
		addTimeFilter(new Delay(delay_val));
	}

        /** sets the starting speed
         * @param new_in starting speed
         *
         */        
	public void setInAmount(double new_in) {
		in.setValue(new_in);
	}
        /** sets the ending speed
         * @param new_out ending speed
         *
         */        
	public void setOutAmount(double new_out) {
		out.setValue(new_out);
	}
        /** sets the peak offset amount
         * @param new_amount offset amount
         *
         */        
	public void setamount(double new_amount) {
		amount.setValue(new_amount);
	}

        /** main routine that makes the calculation
         */        
	public void Process() {
		if(owner != null) {
			if(current_time <= 0) //causal function
				return;
			if(current_time <= duration.getEndValue()){
				if(current_time < duration.getEndValue()/2) //do the first part of the curve
					{
					double t = 2*current_time/duration.getEndValue();
					double i = in.endValue;
					double o = 0.0;
					double c = 3.0 * i;
					double b = 3.0 *(1.0 - o - i) - c;
					double a = 1.0 - c - b;
					owner.endValue += amount.endValue*(a*t*t*t + b*t*t + c*t);
					}
				else //do the second part of the curve
					{
					double t = 2*current_time/duration.getEndValue() - 1;
					double i = 0.0;
					double o = out.endValue;
					double c = 3.0 * i;
					double b = 3.0 *(1.0 - o - i) - c;
					double a = 1.0 - c - b;
					owner.endValue += amount.endValue*(1.0-(a*t*t*t + b*t*t + c*t));
					}
				isDone = false;
			}
			else{
				isDone = true;
				return;
				}
		}
	}

}