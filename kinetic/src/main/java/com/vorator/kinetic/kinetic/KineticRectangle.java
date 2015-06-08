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
import java.awt.geom.*;
import java.awt.font.*;
/** 
 * creates a rectangle that can be used in the engine and be drawn on the screen
 */

public class KineticRectangle extends KineticObject{
        /** local coordinate space bounding box
         */
	protected Rectangle2D original_bounds = new Rectangle2D.Double();
	/** global coordinate space bounding box
         */
        protected GeneralPath transformed_bounds;
        /** global coordinate x
         */
	public double absoluteX;
	/** global coordinate y
         */
        public double absoluteY;
	/** horizontal dimension
         */
        public KineticProperty xSize = new KineticProperty(100.0);
	/** vertical dimension
         */
        public KineticProperty ySize = new KineticProperty(100.0);
        
        /** creates a new instance of a KineticRectangle
         */
	public KineticRectangle() {
            super();
            addProperty(xSize);
            addProperty(ySize);

	}
    
        /** creates a new instance
        *@param n name
        *@param del delay - ms
        *@param dur dur - ms
        */
        public KineticRectangle(String n, double del, double dur) {
            super(n,del,dur);
            addProperty(xSize);
            addProperty(ySize);
        }

        
        /** main draw routine
         *@param g2 graphics handle
         */
	public void draw(Graphics2D g2) {
		draw(g2, new AffineTransform());
	}
	/** main draw routin when affinte transform is provided
         *@param g2 graphics handle
         *@param ax current affine transform
         */
	public void draw(Graphics2D g2, AffineTransform ax) {
		if(!visible)
			return;

		g2.setPaint(new Color(	(float)(red.endValue),
								(float)(green.endValue),
								(float)(blue.endValue),
								(float)(alpha.endValue)));

		AffineTransform origTransform = new AffineTransform(ax);
		ax.translate(x.endValue,y.endValue);
		ax.rotate(rotation.endValue * Math.PI/180.0);
		ax.shear(xShear.endValue, yShear.endValue);
                ax.scale(xScale.getEndValue(), yScale.getEndValue());		

		//center---------------
		double x2 = xSize.getEndValue()/2.0;
                double y2 = ySize.getEndValue()/2.0;
		original_bounds.setRect(-x2,-y2, xSize.getEndValue(), ySize.getEndValue());

		GeneralPath gp = new GeneralPath(original_bounds);
		transformed_bounds = (GeneralPath)gp.createTransformedShape(ax);
		
		Rectangle bbox = transformed_bounds.getBounds();
		absoluteX = bbox.getX() + bbox.getWidth()/2.0;
		absoluteY = bbox.getY() + bbox.getHeight()/2.0;

		g2.setTransform(ax);
		
		g2.fill(original_bounds);
		
		ax.setTransform(origTransform);
	}
	/** gets global coordinate boundin box
         *@return global coordinate bbox
         */
	public Rectangle2D getBounds(){
		return transformed_bounds.getBounds();
		}
        
    /** set size
         *@param width width
         *@param height height
         */
    public void setSize(double width, double height) {
		xSize.setValue(width);
		ySize.setValue(height);
        }

	/** hits test
         *@param p hit point
         *@return true if contains point
         */
	public boolean contains(Point p) {
            if(transformed_bounds == null)
                    return false;
            else
                    return transformed_bounds.contains(p.getX(),p.getY());
	}
	/** get width of global coordinate BBox
         *@return width
         */
	public double getWidth() {
		return transformed_bounds.getBounds().getWidth();
	}
        /** get width of global coordinate BBox
         *@return height
         */
	public double getHeight() {
		return transformed_bounds.getBounds().getHeight();
	}
	
        /** get global coordinate x
         *@return x position
         */
	public double getAbsoluteX() {
		return absoluteX;
	}
	/** get global coordinate y
         *@return y position
         */
	public double getAbsoluteY() {
		return absoluteY;
	}
}
