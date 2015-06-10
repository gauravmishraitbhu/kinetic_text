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
import java.util.*;
/** 
 * specialized version of the nomral KineticProperty.  contains a list of items in a lookup array.  Treats the value as a bounded double type and rounds it to an index when the value must be retrieved
 */

public class KineticDiscreteProperty extends KineticProperty implements java.io.Serializable {
        /** list of choices
         */
	private ArrayList choices = new ArrayList();

        /**creates a new instance of KineticDiscreteProperty
         */
	public KineticDiscreteProperty() {
		upper_bound = 0;
		lower_bound = 1;
		clipValue();
	}

        /**creates a new instance of KineticDiscreteProperty
         *@param new_val sets the value of this property
         */
	public KineticDiscreteProperty(double new_val) {
		this();
		setValue(new_val);
	}

        /**creates a new instance of KineticDiscreteProperty
         *@param new_val sets the value of this property
         *@param l_bound sets the lower bound of the property
         *@param u_bound sets the upper bound of the property
         */
	public KineticDiscreteProperty(double new_val, double l_bound, double u_bound) {
		upper_bound = u_bound;
		lower_bound = l_bound;
		setValue(new_val);
		clipValue();
	}
        /** gets the rounded interger of the property
         *@return floor() integer value of the property
         */
	public double getEndValue() {
		return Math.floor(endValue);
	}
        /** returns the object pointed by the current value
         *@return currectly selected object
         */
	public Object getEndChoice() {
		return choices.get((int)Math.round(endValue));
	}
	/** adds a new option the choice list in this property
         *@param c instance of an object to be added
         */
	public void addChoice(Object c) {
		if(choices.contains(c))
			return;
		choices.add(c);
		upper_bound = choices.size();		
	}
	/** assigns the linked list inside this property to the one passed
         *@param new_list ArrayList of object choices
         */
	public void setChoices(ArrayList new_list) {
		choices = new_list;
		upper_bound = choices.size();
		clipValue();
	}
	/** removes a particular choice from the choice list
         *@param c object to be removed
         */
	public void removeChoice(Object c) {
		choices.remove(c);
		upper_bound = choices.size();
		clipValue();
	}
	/** get the indexed object from the choice list
         * @return indexed object
         * @param i index value
         */
	public Object getChoice(int i) {
		return choices.get(i);
	}
	/** return the size of the choice list
         *@return size of list
         */
	public int getSize() {
		return choices.size();
	}

        /** clips the current value to the defined bounds
         */
	public void clipValue() {
		if(endValue < 0)
			endValue = 0;
		if(choices == null) {
			endValue = 0;
			return;
			}
		if(endValue > choices.size() - 1)
			endValue = choices.size() - 1;
	}
}
