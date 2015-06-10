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
import java.awt.geom.*;
/** 
 * core organizational object itneh engine.  Sequence are used to build the animation hierachies that allow you to break down a complex piece.  Each Sequence contains a "cast" list which are of KineticObjects.  This means this list can contain KineticStrings or other Sequences.  Each Seqeunces perform geometric transofmarions and well as temporal transfomrations before updating or drawing it's children.  Sequences propagate time updates and draw requests.  Sequences are alos used to temporal culling.  This is important for long a complex pieces.  Once a KineticObject is completed with and no longer visible, unless it is wrapped in a sequence that has terminated it will continue ot be hit on redraw requests.   All objects within a sequence are drawn until the sequence has ended. Subsequences should be used minimize touching words that are no longer visible.  Wrapping every word in it's own sequence can be beneficial in many cases but is not necessary. 
 */
public class Sequence extends KineticObject{
    /** cast list
     */
    public ArrayList cast = new ArrayList();
    /** create a new instance of a sequence
     */
    public Sequence() {
            this("Sequence",0.0,0.0);
    }
    /** create a new instance of sequence with parameters
     * @param n Sequence name
     * @param delayVal time until this sequence starts - ms
     * @param dur duration of this sequence
     */
    public Sequence(String n, double delayVal, double dur) {
            name = n;
            setDelay(delayVal);
            setDuration(dur);
            visible = false;
    }
    /** drawing routine with out and affine transform - a new transofrm is created and the main drawing routine is called
     *@param g2 graphics handle
     */
    public void draw(Graphics2D g2) {
            draw(g2, new AffineTransform());
    }
    /** main drawing routine
     *@param g2 graphics handle
     *@param ax current affine transform
     */
    public void draw(Graphics2D g2, AffineTransform ax) {
        if(!visible)
                return;
        AffineTransform origTransform = new AffineTransform(ax);
        ax.translate(x.getEndValue(),y.getEndValue());
        ax.rotate(rotation.getEndValue() * Math.PI/180.0);
        ax.shear(xShear.getEndValue(), yShear.getEndValue());
        ax.scale(xScale.getEndValue(), yScale.getEndValue());

        for(int i = 0; i < cast.size(); i++)
                ((KineticObject)cast.get(i)).draw(g2,ax);
        ax.setTransform(origTransform);
        }

    /** add a new cast member to the cast list
     *@param obj instance of a KineticObject
     */
    public void addCastMember(KineticObject obj) {
            if(!cast.contains(obj))
                    {
                    obj.parent = this;
                    cast.add(obj);
                    }
            return;
    }
    /** empty the cast list and time fitlers
     */
    public void clear(){
        timeFilters.clear();
        cast.clear();
    }
    /** updates cast members
     *@param time input time
     */
    public void update(double time){
        lastUpdateTime = time;
        double curr_time = PrepareVariables(time);
        if((curr_time > duration.getEndValue())||(curr_time < 0)) {
            visible = false;
            return;
        }
        else
            visible = true;
        if(curr_time < 0) //limits exectution
            return;

        for(int i = 0; i < cast.size(); i++)
                ((KineticObject)cast.get(i)).update(curr_time);
    }
    /** get cast list size
     *@return cast size
     */
    public int getCastSize(){
            return cast.size();
    }
    /** access a particular cast member
     *@param i cast index
     *@return cast member
     */
    public KineticObject getCastMember(int i) {
            return (KineticObject)cast.get(i);
    }
    /** hit test - currently always return true since seuqence have no visual bounds
     * @param p hit point
     * @return  returns true if it contains the point
     */
    public boolean contains(Point p) {
            return true; //*************************
    }
    /** reset internal variables and cast members
     */
    public void reset() {
        super.reset();
        //reset all cast members
        for(int i = 0; i < cast.size(); i++)
                ((KineticObject)cast.get(i)).reset();
    }
    /** set mode
    *@param newMode new mode
    */
    public void setMode(int newMode){
        for(int i = 0; i < cast.size(); i++)
                ((KineticObject)cast.get(i)).setMode(newMode);
    }
    /** utility function to expand the duration of the sequence to fit the duration of all of it's children (the result of getMaxDuration) automatically. NOTE: there is a possibility that the sequence duration will be set to POSITIVE_INFINITY if one of the children has this value.  This may cause problems when trying to play the animation using the supplied animation thread in KTEngine.
     */    
    public void expandDurationToFitChildren(){
        duration.setValue(getMaxDuration());
    }
    
    /** gets maximum or unprecessed value of duration.  NOTE: This function may return POSITIVE_INFINITY if any node in the sub tree has this value.
     * @return maximum duration of this object or any object in the subtree
     */
    public double getMaxDuration(){
        double max = 0;
        for(int i = 0; i < cast.size(); i++)
                max = Math.max(max,((KineticObject)cast.get(i)).getMaxDuration());
        return max;
    }
}

