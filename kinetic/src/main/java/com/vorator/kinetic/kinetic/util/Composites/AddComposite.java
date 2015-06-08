/*
 * AddComposite.java
 *
 * Created on September 15, 2002, 12:46 AM
 *
 * 
 * Kinetic Typography Engine - java library for animating expressive text
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
 *
 */

package com.vorator.kinetic.kinetic.util.Composites;
import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.DataBuffer;
/**
 *
 * @author  Johnny Lee
 */
public class AddComposite implements Composite{
    private int count = 1;
    /** Creates a new instance of AddComposite */
    public AddComposite(int layerCount) {
        if (layerCount <= 0) {
	    throw new IllegalArgumentException("layer count out of bounds");
	}
        count = layerCount;
    }
    /** creates the composite context for this composite technique
     */
    public java.awt.CompositeContext createContext(java.awt.image.ColorModel srcColorModel, java.awt.image.ColorModel dstColorModel, java.awt.RenderingHints hints) {
        /**Currently ignoring rendering hints.  Should parse and respond appropriately
         *KEY_ALPHA_INTERPOLATION 
         *  VALUE_ALPHA_INTERPOLATION_DEFAULT 
         *  VALUE_ALPHA_INTERPOLATION_QUALITY 
         *  VALUE_ALPHA_INTERPOLATION_SPEED 
         *KEY_COLOR_RENDERING 
         *  VALUE_COLOR_RENDER_DEFAULT 
         *  VALUE_COLOR_RENDER_QUALITY 
         *  VALUE_COLOR_RENDER_SPEED 
         *KEY_DITHERING 
         *  VALUE_DITHER_DEFAULT
         *  VALUE_DITHER_DISABLE
         *  VALUE_DITHER_ENABLE
         *KEY_RENDERING 
         *  VALUE_RENDER_DEFAULT
         *  VALUE_RENDER_QUALITY
         *  VALUE_RENDER_SPEED
         */
        return new AddCompositeContext(count);
    }
    
    public class AddCompositeContext implements CompositeContext{
        private int count = 1;
        private double blend = 1.0;
        private Rectangle srcBnds;
        private Rectangle dstInBnds;
        private Rectangle dstOutBnds;
        private int startX;
        private int startY;
        private int endX;
        private int endY;
        private int srcData[] = null;
        private int dstInData[] = null;
        private int dstOutData[] = null;
        private double srcPixel[] = new double[4];
        private double dstInPixel[] = new double[3];
        private double dstOutPixel[] = new double[3];
        
        public AddCompositeContext(int layerCount){
            if (layerCount <= 0) {
                throw new IllegalArgumentException("layer count out of bounds");
            }
            count = layerCount;
            blend = 1.0/count;
        }
        public double limit(double upper, double lower, double val){
            if(val > upper)
                return upper;
            if(val < lower)
                return lower;
            return val;
        }
        /** composes the two raster sources into the writableRaster
         */
        public void compose(java.awt.image.Raster src, java.awt.image.Raster dstIn, java.awt.image.WritableRaster dstOut) {
            srcBnds = src.getBounds();
            dstInBnds = dstIn.getBounds();
            dstOutBnds = dstOut.getBounds();
            srcData = null;
            dstInData = null;
            dstOutData = null;
            /*
            srcData = src.getPixels(        srcBnds.x,
                                            srcBnds.y,
                                            srcBnds.width,
                                            srcBnds.height,
                                            srcData);
            dstInData = dstIn.getPixels(    dstInBnds.x,
                                            dstInBnds.y,
                                            dstInBnds.width,
                                            dstInBnds.height,
                                            dstInData);
            
            dstOutData = dstOut.getPixels(  dstOutBnds.x,
                                            dstOutBnds.y,
                                            dstOutBnds.width,
                                            dstOutBnds.height,
                                            dstOutData);
             */
            startX = Math.max(srcBnds.x, dstInBnds.x);
            endX = Math.min(srcBnds.x+srcBnds.width, dstInBnds.x+dstInBnds.width);
            startY = Math.max(srcBnds.y, dstInBnds.y);
            endY = Math.min(srcBnds.y+srcBnds.height, dstInBnds.y+dstInBnds.height);
            /*
            System.err.println( System.currentTimeMillis() + " : " + 
                                src.getWidth() + " " + 
                                dstIn.getWidth()+ " " + 
                                dstOut.getWidth());
             */
            for(int x = startX; x < endX; x++){
                for(int y = startY; y < endY; y++){
                    srcPixel = src.getPixel(x,y,srcPixel);
                    dstInPixel = dstIn.getPixel(x,y,dstInPixel);
                    dstOutPixel[0] = limit(255,0,dstInPixel[0]+srcPixel[0]*blend);
                    dstOutPixel[1] = limit(255,0,dstInPixel[1]+srcPixel[1]*blend);
                    dstOutPixel[2] = limit(255,0,dstInPixel[2]+srcPixel[2]*blend);
                    dstOut.setPixel(x, y, dstOutPixel);
                }                
            }
        }
        /** relieves any resources used
         */
        public void dispose() {
            srcData = null;
            dstInData = null;
            dstOutData = null;
            srcPixel = null;
            dstInPixel = null;
            dstOutPixel = null;
        }
        
    }
}
