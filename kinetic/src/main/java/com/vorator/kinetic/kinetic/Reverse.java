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
 * time filter that reverses the flow of time over a given duration.  For exmaple, if the duration is set to 10 seconds for the first 10 seconds, the time will count down from 10 to 0.  After the lifspan is over it will resume at 10 seconds.
 */
public class Reverse extends TimeFilter{
        /** creates new instance of reverse with a duration
         *@param dur window of time to count backwords
         */
	public Reverse(double dur) {
		duration.setValue(dur);
	}
        /** processed input value of time to produce an new output value of time
         *@param time input value of time
         *@return processed output value of time
         */
	public double apply(double time) {
		PrepareVariables(time);//process properties first.
		if((time < 0)||(time > duration.getEndValue()))
			return time;
		return (duration.getEndValue() - time);
	}
}
