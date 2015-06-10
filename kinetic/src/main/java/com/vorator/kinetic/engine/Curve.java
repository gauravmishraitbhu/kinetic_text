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
 *This behavior allow you to specify the startin and ending speed of a movement. (0,0) yields a slow-in slow-out behavior.  (1,1) produces an fast-in fast-out result.  These values may go beyond the bounds of [0,1].  Doing so produces an over shoot, or pull-back result.
 */
public class Curve extends Behavior{

    /** property to store the starting speed
     */    
	public KineticProperty in = new KineticProperty(0.0);
        /** property to store the ending speed
         */        
	public KineticProperty out = new KineticProperty(0.0);
        /** property to store the amount of offset
         */        
	public KineticProperty amount = new KineticProperty(10);

        /** creates a new instance of Curve
         */        
	public Curve() {
		properties.add(in);
		properties.add(out);
		properties.add(amount);
	}

        /** basic constructor that defines only the values specific to Curve
         * @param new_in startin speed
         * @param new_out ending speed
         * @param new_amount offset amount
         */        
	public Curve(double new_in,double new_out, double new_amount) {
		this();
		in.setValue(new_in);
		out.setValue(new_out);
		amount.setValue(new_amount);
	}

        /** constructor that will add a delay time fitler for convenience
         * @param delay_val time until starting - milliseconds
         * @param new_in set the starting speed
         * @param new_out set the ending speed
         * @param dur_val duration of behavior in milliseconds
         * @param new_amount amount of offset
         */        
	public Curve(double delay_val, double dur_val, double new_in,double new_out, double new_amount) {
		this();
		in.setValue(new_in);
		out.setValue(new_out);
		amount.setValue(new_amount);
		duration.setValue(dur_val);
		addTimeFilter(new Delay(delay_val));
	}

        /** set the starting speed
         * @param new_in starting speed
         */        
	public void setInAmount(double new_in) {
		in.setValue(new_in);
	}
        /** set the ending speed
         * @param new_out ending speed
         */        
	public void setOutAmount(double new_out) {
		out.setValue(new_out);
	}
        /** set the offset amount
         * @param new_amount offset amount
         */        
	public void setamount(double new_amount) {
		amount.setValue(new_amount);
	}

        /** main routine the executes the calculation
         */        
	public void Process() {
		if(owner != null) {
			if(current_time <= 0) //causal function
				return;
			if(current_time <= duration.getEndValue()){
				double t = current_time/(duration.getEndValue());
				double i = in.endValue;
				double o = out.endValue;
				double c = 3.0 * i;
				double b = 3.0 *(1.0 - o - i) - c;
				double a = 1.0 - c - b;
				owner.endValue += amount.endValue*(a*t*t*t + b*t*t + c*t);
				isDone = false;
			}
			else {
				owner.endValue += amount.endValue;
				isDone = true;
				}
		}
	}

}