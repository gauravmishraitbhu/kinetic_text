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
 * implements the derviative function for the SymmetricCurve behavior.  If parameters are match, this motion with be correctly synchronized.
 */
public class SymmetricCurveSecondary extends Behavior{
        /** curvature property
         */
	public KineticProperty curvature = new KineticProperty(0.0);
        /** offset amount
         */
	public KineticProperty amount = new KineticProperty(10);
        /** creates a new instance of a Symmetric Curve Secondary
         */

	public SymmetricCurveSecondary() {	
		properties.add(curvature);
		properties.add(amount);
	}
        /** creates a new instance of symmetric curve secondary with paramters
         *@param new_curvature curvature
         *@param new_amount offset amount
         */

	public SymmetricCurveSecondary(double new_curvature, double new_amount) {
		this();
		curvature.setValue(new_curvature);
		amount.setValue(new_amount);
		duration.setMaxValue();
	}
        /** create a new instance of Symmetric curve secondary with paramaters
         *@param delay_val time until start - ms
         *@param dur_val duration of motion
         *@param new_curvature new curvature
         *@param new_amount offsetamount
         */

	public SymmetricCurveSecondary(double delay_val, double dur_val, double new_curvature, double new_amount) {
		this();
		curvature.setValue(new_curvature);
		amount.setValue(new_amount);
		duration.setValue(dur_val);
		addTimeFilter(new Delay(delay_val));
	}

        /** set curavture
         *@param new_curvature new curvature
         */
	public void setcurvature(double new_curvature) {
		curvature.setValue(new_curvature);
	}
        /** set offset amount
         *@param new_amount offset amount
         */
	public void setamount(double new_amount) {
		amount.setValue(new_amount);
	}

        /** calculates new parent property value
         */
	public void Process() {
		if(owner != null) {
			if(current_time <= 0) //causal function
				return;
			if(current_time < duration.getEndValue()){
				if(current_time < duration.getEndValue()/2) //do the first part of the SymmetricCurveSecondary
					{
					double t = 2*current_time/duration.getEndValue();
					double in = 0.0;
					double out = curvature.endValue;
					double c = 3.0 * in;
					double b = 3.0 *(1.0 - out - in) - c;
					double a = 1.0 - c - b;
					owner.endValue += amount.endValue*(3*a*t*t + 2*b*t + c)/2;
					}
				else //do the second part of the SymmetricCurveSecondary
					{
					double t = 2*current_time/duration.getEndValue() - 1;
					double in = curvature.endValue;
					double out = 0.0;
					double c = 3.0 * in;
					double b = 3.0 *(1.0 - out - in) - c;
					double a = 1.0 - c - b;
					owner.endValue += amount.endValue*(3*a*t*t + 2*b*t + c)/2;
					}
				isDone = false;
			}
			else {
				isDone = true;
				return;
			}
		}
	}
}
