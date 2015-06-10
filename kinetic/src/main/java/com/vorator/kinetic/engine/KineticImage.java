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
import java.awt.event.*;
import java.lang.*;
import java.io.File;
import java.awt.geom.*;
import java.net.URL;

/**
 * Uses Java's toolkit image capabilites that can load a GIF, JPEG, or PNG file either locally or a URL
 *
 * @author  Johnny Lee
 */
public class KineticImage extends KineticRectangle{

    /** image data */
     private Image image = null;
     /** media tracker used to load image data */
    private MediaTracker tracker;
    /** Creates a new instance of KineticImage */
    public KineticImage() {
        super();
    }
    
    /** creates a new instance
    *@param n name
    *@param del delay - ms
    *@param dur dur - ms
    */
    public KineticImage(String n, double del, double dur) {
        super(n,del,dur);
    }
    

    /** loads the image data from a local file
     *@param path full path for the file
     */
    public void load(String path) throws Exception{
        
        File f = new File(path); 
        if(!f.exists())
            throw new Exception("File not found" );

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.createImage(path);
        tracker = new MediaTracker(new Canvas());//??????
        tracker.addImage(image, 0);
        tracker.waitForID(0);
        xSize.setValue(image.getWidth(null)); 
        ySize.setValue(image.getHeight(null)); 
    }
    /** loads the image data from a URL
     *@param url url for image
     */
    public void load(URL url) throws Exception{
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        image = toolkit.createImage(url);
        tracker = new MediaTracker(new Canvas());//??????
        tracker.addImage(image, 0);
        tracker.waitForID(0);
        if(image.getWidth(null) == -1)
            throw new Exception("File could not be downloaded");
        xSize.setValue(image.getWidth(null)); 
        ySize.setValue(image.getHeight(null)); 
    }

    
    /** main draw routin when affinte transform is provided
     *@param g2 graphics handle
     *@param ax current affine transform
     */
    public void draw(Graphics2D g2, AffineTransform ax) {
            if(!visible)
                    return;
            if(image == null)
                    return;
            AffineTransform origTransform = new AffineTransform(ax);
            ax.translate(x.endValue,y.endValue);
            ax.rotate(rotation.endValue * Math.PI/180.0);
            ax.shear(xShear.endValue, yShear.endValue);
            ax.scale(xScale.getEndValue(), yScale.getEndValue());		

            g2.setPaint(Color.black);
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
            g2.drawImage(image, (int)-x2, (int)-y2, null);
            ax.setTransform(origTransform);
    }    
}
