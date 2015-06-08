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
 * creates a decaying oscillation offset on a property
 */
public class Pulse extends Behavior{
        /** peak offset value
         */
	public KineticProperty amount = new KineticProperty(1);
        /** osciallation rate (radians over life span)
         */
	public KineticProperty rate = new KineticProperty(.1);
        /** speed the oscillation  amplitude wave decays to zero
         */
	public KineticProperty damping = new KineticProperty(1);
        /** radian phase shift
         */
	public KineticProperty phase = new KineticProperty(0,0,1);

        /** creats new instance of Pulse
         */
	public Pulse() {
		properties.add(amount);
		properties.add(rate);
		properties.add(damping);
		properties.add(phase);
	}
        /** creates a new instance of pulse
         *@param delay_val time delay - ms
         *@param dur_val duration/lifespan - ms
         *@param amount_val peak offset amount
         *@param rate_val radians over lifespan
         *@param damping_val oscillation amplitude deacy rate
         *@param phase_val radian phase offset
         */
	public Pulse(double delay_val, double dur_val, double amount_val, double rate_val, double damping_val, double phase_val)
		{
		this();
		amount.setValue(amount_val);
		rate.setValue(rate_val);
		damping.setValue(damping_val);
		duration.setValue(dur_val);
		phase.setValue(phase_val);
		addTimeFilter(new Delay(delay_val));
		}
        /** main routine to calculate the new value for the owner property
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
			double t = current_time/duration.getEndValue();
			double i = damping.getEndValue();
			double o = 0.0;
			double c = 3.0 * i;
			double b = 3.0 *(1.0 - o - i) - c;
			double a = 1.0 - c - b;
			owner.endValue += -amount.getEndValue() * (1- a*t*t*t - b*t*t - c*t) * Math.cos(rate.getEndValue()*t+ phase.getEndValue()*Math.PI/2.0);
		}
	}
}