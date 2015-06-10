/*
 * ExponentialChange.java
 *
 * Created on September 13, 2002, 4:58 PM
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

/** 
 * generates a offset driven by an exponetial curve.  The curve is bound by the values of amount and duration, but the supplied exponent determines the curvature of offset over time.
 * @author Johnny Lee
 */
public class ExponentialChange extends Behavior{
    
    /** porpoerty to hold the offset amount
     */    
    protected KineticProperty amount = new KineticProperty(10);
    /** property to hold the curvature exponent
     */    
    protected KineticProperty exponent = new KineticProperty(5);

    /** Creates a new instance of ExponentialChange */
    public ExponentialChange() {
        properties.add(amount);
        properties.add(exponent);
    }
    /** creates a new instance of ExponetialChange using parameters
     * @param delay_val time until the behavior starts -ms
     * @param dur_val life span of the bahavior - ms
     * @param exp curvature exponent
     * @param amount_val offset amount
     */    
    public ExponentialChange(double delay_val, double dur_val, double exp, double amount_val)
        {
        this();
        amount.setValue(amount_val);
        exponent.setValue(exp);
        duration.setValue(dur_val);
        addTimeFilter(new Delay(delay_val));
        }
    /** sets the offset amount
     * @param new_amount offset amount
     *
     */    
    public void setAmount(double new_amount) {
            amount.setValue(new_amount);
    }
    /** main routine that makes the calculation
     *
     */    
    public void Process() {
        if(owner != null) {
            if(current_time <= 0) //causal function
                    return;
            if(current_time < duration.endValue) {
                    if(Math.abs(exponent.getEndValue()) < 0.01)                    
                        owner.endValue += (current_time/duration.endValue)*amount.getEndValue();
                    else{
                        //creates an exponential curve bounded to a unit square
                        owner.endValue += amount.getEndValue()*(Math.pow(Math.E,exponent.getEndValue()*(current_time/duration.endValue)) - 1)/
                                (Math.pow(Math.E,exponent.getEndValue()) - 1);                        
                    }
                    isDone = false;
                    }
            else {
                    owner.endValue += amount.endValue;
                    isDone = true;
                    }
        }
    }
    
}
