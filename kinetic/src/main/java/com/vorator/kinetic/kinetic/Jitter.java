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
 * gereates a randomized offset over time
 */
public class Jitter extends Behavior{
        /** property to hold the offset range
         */
	public KineticProperty amount = new KineticProperty(10);
	/** property to hold the number of milliseconds between each shift
         */
        public KineticProperty rate = new KineticProperty(30);

        /** stores list time the behavior was called
         */
	private double last_time = 0;
	/** ??
         */
        private double target_value;
        /** constructs a new instance of the Jitter brhavior
         */
	public Jitter() {
		properties.add(amount);
		properties.add(rate);
	}

        /** constructs a new instance of the Jitter brhavior
         * @param amount_val offset range used
         * @param rate_val milliseconds between randomizations
         */

	public Jitter( double amount_val, double rate_val) {
		this();
		amount.setValue(amount_val);
		rate.setValue(rate_val);
	}

        /** constructs a new instance of the Jitter brhavior
         * @param delay_val milliseconds of delay
         * @param dur_val duration in milliseconds
         * @param amount_val offset range used
         * @param rate_val milliseconds between randomizations
         */
	public Jitter(double delay_val, double dur_val, double amount_val, double rate_val)
		{
		this();
		amount.setValue(amount_val);
		rate.setValue(rate_val);
		duration.setValue(dur_val);
		addTimeFilter(new Delay(delay_val));
		}

        /** sets the offset range
         *@param new_amount offset range
         */
	public void setAmount(double new_amount) {
		amount.setValue(new_amount);
	}
        /** sets the time between each randomization
         *@param new_rate new time delay between randomizations
         */
	public void setRate(double new_rate) {
		rate.setValue(new_rate);
	}
        /** resets internal variables
         */
        
	public void reset() {
		last_time = 0;
		super.reset();
	}

        /** main routine for doing the calculation
         */
	public void Process() {
		if(owner != null) {
			if(current_time <= 0) //limits exectution
				return;
			if(current_time > duration.getEndValue()) {//limits exectution
				isDone = true;
				return;
				}
			isDone = false;
			double diff = Math.abs(current_time - last_time);

			if(diff > rate.getEndValue()){
				last_time = current_time;
				target_value = 2.0*amount.getEndValue()*(double)(Math.random()-0.5);
			}
			owner.endValue += target_value;
		}
	}

}
