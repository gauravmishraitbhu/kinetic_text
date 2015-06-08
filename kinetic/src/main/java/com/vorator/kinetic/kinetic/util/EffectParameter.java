/*
 * CompositeEffectParameter.java
 *
 * Created on September 1, 2002, 1:05 PM
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

package com.vorator.kinetic.kinetic.util;
import java.util.ArrayList;
/**
 * base class for effect paramter types
 * @author  Johnny Lee
 */
public abstract class EffectParameter {
    
    /** name of this Effect Parameter
     */    
    public java.lang.String name;
    /** used to indicate what variable this parameter uses
     */    
    public int type;
    /** default value of the effect parameter
     */    
    public Object defaultValue = null;
    /** current value of the effect parameter
     */    
    public Object currentValue = null;
    
    /** Unknown type
     *
     */    
    public static final int TYPE_UNKNOWN = 0;
    /** Double type
     */    
    public static final int TYPE_DOUBLE = 1;
    /** Integer Type
     */    
    public static final int TYPE_INTEGER = 2;
    /** String Type
     */    
    public static final int TYPE_STRING = 3;
    /** Boolean Type
     */    
    public static final int TYPE_BOOLEAN = 4;
    /** Double value bounded between 0 and 1
     */    
    public static final int TYPE_ZERO_TO_ONE = 5;
    /** Double type bounded between 0 and 100
     */    
    public static final int TYPE_PERCENTAGE = 6;
    /** Generic bounded double type
     */    
    public static final int TYPE_BOUNDED_DOUBLE = 7;
    /** Generic bounded interger type
     *
     */    
    public static final int TYPE_BOUNDED_INTEGER = 8;
    /** enumerated string type
     */    
    public static final int TYPE_ENUMERATED_STRING = 9;
    
    /** Creates a new instance of EffectParameter
     * @param n name for this effect parameter
     * @param t integer indicating the variable type of this effect parameter
     *
     * @param d object representing the default value for this parameter
     * @param c object representing the current value for this parameter
     */    
    public EffectParameter(java.lang.String n, int t, Object d, Object c) {
        name = n;
        type = t;
        defaultValue = d;
        currentValue = c;
    }
    
    /** Unbouned Double Parameter Type Class
     */
    public static class Double extends EffectParameter{
    /** creates a new instance of an unbounded double type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     */
        
        public Double(java.lang.String n, double v){
            super(n, TYPE_DOUBLE, new java.lang.Double(v), new java.lang.Double(v));
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public double getValue(){
            return ((java.lang.Double)super.getCurrentObject()).doubleValue();
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(double v){
            super.setCurrentObject(new java.lang.Double(v));
        }
    }
    
    /** Unbounded Integer Parameter Class
     */
    public static class Integer extends EffectParameter{
    /** creates a new instance of the type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     */
        public Integer(java.lang.String n, int v){
            super(n, TYPE_INTEGER, new java.lang.Integer(v), new java.lang.Integer(v));
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public int getValue(){
            return ((java.lang.Integer)super.getCurrentObject()).intValue();
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(int v){
            super.setCurrentObject(new java.lang.Integer(v));
        }
    }
    /** Unrestricted String Parameter Class
     */
    public static class String extends EffectParameter{
    /** creates a new instance of the type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     */
        public String(java.lang.String n, java.lang.String v){
            super(n, TYPE_STRING, new java.lang.String(v), new java.lang.String(v));
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public java.lang.String getValue(){
            return (java.lang.String)super.getCurrentObject();
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(java.lang.String v){
            super.setCurrentObject(new java.lang.String(v));
        }
    }
    
    /** Boolean Parameter Class
     */
    public static class Boolean extends EffectParameter{
    /** creates a new instance of the type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     */
        public Boolean(java.lang.String n, boolean v){
            super(n, TYPE_BOOLEAN, new java.lang.Boolean(v), new java.lang.Boolean(v));
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public boolean getValue(){
            return ((java.lang.Boolean)super.getCurrentObject()).booleanValue();
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(boolean v){
            super.setCurrentObject(new java.lang.Boolean(v));
        }
    }
    /** Bounded [0.0-1.0] Double Parameter Class
     */
    public static class ZeroToOne extends EffectParameter{
    /** upper bound 
     */
        double upperBound = 1.0;
    /** lower bound 
     */
        double lowerBound = 0.0;
    /** creates a new instance of the type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     */
        public ZeroToOne(java.lang.String n, double v){
            super(n, TYPE_ZERO_TO_ONE, new java.lang.Double(v), new java.lang.Double(v));
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public double getValue(){
            return ((java.lang.Double)super.getCurrentObject()).doubleValue();
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(double v){
            super.setCurrentObject(new java.lang.Double(v));
        }
    /** gets the upper bound
     * @return upper bound
     */
        public double getUpperBound(){
            return upperBound;
        }
    /** gets the lower bound
     * @return lower bound
     */
        public double getLowerBound(){
            return lowerBound;
        }
    }
    /** Bounded [0.0-100.0] Double Parameter Class
     */
    public static class Percentage extends EffectParameter{
    /** upper bound 
     */
        double upperBound = 100.0;
    /** lower bound 
     */
        double lowerBound = 0.0;
    /** creates a new instance of the type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     */
        public Percentage(java.lang.String n, double v){
            super(n, TYPE_PERCENTAGE, new java.lang.Double(v), new java.lang.Double(v));
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public double getValue(){
            return ((java.lang.Double)super.getCurrentObject()).doubleValue();
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(double v){
            super.setCurrentObject(new java.lang.Double(v));
        }
    /** gets the upper bound
     * @return upper bound
     */
        public double getUpperBound(){
            return upperBound;
        }
    /** gets the lower bound
     * @return lower bound
     */
        public double getLowerBound(){
            return lowerBound;
        }
    /** gets the percentage as a value between 0-1
     * @return percentage value between 0-1
     */
        public double getNormalizedValue(){
            return getValue()/100.0;
        }
    }
    
    /** Bounded Double Parameter Class
     */
    public static class BoundedDouble extends EffectParameter{
    /** upper bound 
     */
        double upperBound = 1.0;
    /** lower bound 
     */
        double lowerBound = 0.0;
    /** creates a new instance of the type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     * @param lower lower bound for the parameter
     * @param upper upper bound for the parameter
     */
        public BoundedDouble(java.lang.String n, double v, double lower, double upper){
            super(n, TYPE_BOUNDED_DOUBLE, new java.lang.Double(v), new java.lang.Double(v));
            upperBound = upper;
            lowerBound = lower;
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public double getValue(){
            return ((java.lang.Double)super.getCurrentObject()).doubleValue();
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(double v){
            super.setCurrentObject(new java.lang.Double(v));
        }
    /** gets the upper bound
     * @return upper bound
     */
        public double getUpperBound(){ return upperBound; }
    /** gets the lower bound
     * @return lower bound
     */
        public double getLowerBound(){ return lowerBound; }
    /** sets the upper bound
     * @param v upper bound
     */
        public void setUpperBound(double v){ upperBound = v; }
    /** sets the lower bound
     * @param v lower bound
     */
        public void setLowerBound(double v){ lowerBound = v; }
    }
 
    /** Bounded Integer Parameter Class
     */
    public static class BoundedInteger extends EffectParameter{
    /** upper bound 
     */
        int lowerBound = 0;
    /** lower bound 
     */
        int upperBound = 100;
    /** creates a new instance of the type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     * @param lower lower bound for the parameter
     * @param upper upper bound for the parameter
     */
        public BoundedInteger(java.lang.String n, int v, int lower, int upper){
            super(n, TYPE_BOUNDED_INTEGER, new java.lang.Integer(v), new java.lang.Integer(v));
            upperBound = upper;
            lowerBound = lower;
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public int getValue(){
            return ((java.lang.Integer)super.getCurrentObject()).intValue();
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(int v){
            super.setCurrentObject(new java.lang.Integer(v));
        }
    /** gets the upper bound
     * @return upper bound
     */
        public int getUpperBound(){ return upperBound; }
    /** gets the lower bound
     * @return lower bound
     */
        public int getLowerBound(){ return lowerBound; }
    /** sets the upper bound
     * @param v upper bound
     */
        public void setUpperBound(int v){ upperBound = v; }
    /** sets the lower bound
     * @param v lower bound
     */
        public void setLowerBound(int v){ lowerBound = v; }
    }
    
    /** Enumerated String Parameter Class
     */
    public static class EnumeratedString extends EffectParameter{
    /** list of possible values
     */
        ArrayList objectList = new ArrayList();
    /** creates a new instance of the type parameter
     * @param n name of the parameter
     * @param v the default and initial value for the parameter
     */
        public EnumeratedString(java.lang.String n, java.lang.String v){
            super(n, TYPE_ENUMERATED_STRING, v, v);
            objectList.add(v);
        }
    /** gets the type casted version of the current value object
     * @return value of the parameter
     */
        public java.lang.String getValue(){
            return ((java.lang.String)super.getCurrentObject());
        }
        /** sets the current value object with a type casted variable
         * @param v new value for the parameter
         */
        public void setValue(java.lang.String v){
            super.setCurrentObject(v);
            addChoice(v);
        }
    /** gets the number of available choices
     * @return size of the choice list
     */
        public int getSize(){ return objectList.size(); }
    /** adds a choice to the choice list
     * @param v a new choice
     */
        public void addChoice(java.lang.String v){
            if(!objectList.contains(v))
                objectList.add(v);
        }
    /** adds an array of choices to the choice list
     * @param array and array of new choices
     */
        public void addChoices(java.lang.String[] array){
            for(int i = 0; i < array.length; i++)
                addChoice(array[i]);
        }
    /** return the choice list as an array
     * @return an array of the available choices
     */
        public java.lang.String[] getChoices(){
            return (java.lang.String[])objectList.toArray();
        }
    }
    
    /** return the default value object
     * @return default value object for this parameter
     *
     */    
    public Object getDefaultObject(){ return defaultValue;};
    /** sets the default value object for this parameter
     * @param v default value object for this parameter
     */    
    public void setDefaultObject(Object v){ defaultValue = v;};
    
    /** gets the current value object for this parameter
     * @return current value object
     *
     */    
    public Object getCurrentObject(){ return currentValue;};
    /** sets the current value object for this parameter
     * @param v a new value object
     */    
    public void setCurrentObject(Object v){ currentValue = v;};
    /** get the value type integer for this parameter
     * @return type integer
     */    
    public int getType(){ return type;};
    /** sets the value type integer for this parameter
     * @param t new type integer
     */    
    public void setType(int t){ type = t;};
    /** get the name of this parameter
     * @return the name of this parameter
     */    
    public java.lang.String getName(){ return name;};
    /** sets the name of this parameter
     * @param n new name
     */    
    public void setName(java.lang.String n){ name = n;};
}
