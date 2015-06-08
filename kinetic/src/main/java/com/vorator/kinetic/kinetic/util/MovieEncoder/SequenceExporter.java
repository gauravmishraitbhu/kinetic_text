
/**
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
package com.vorator.kinetic.kinetic.util.MovieEncoder;

import com.vorator.kinetic.kinetic.*;
import com.vorator.kinetic.kinetic.util.Composites.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

/** this is a utility class that lets you easily take a completed animation sequence and push it out to a Quicktime file.  Each frame can take as long as it need to render its image.
 */

public class SequenceExporter implements BufferedImageSource{
        protected Component panel;
        protected Dimension d;
        protected Rectangle screenRect;
        protected float frameRate;
        protected Sequence seq;
        protected double duration;
        protected boolean isComplete;
        protected BufferedImage bufferedImg1;
        protected Graphics2D graphicsHandle1;
        protected BufferedImage bufferedImg2;
        protected Graphics2D graphicsHandle2;
        protected AffineTransform identity = new AffineTransform();
        protected boolean updateScreen = true;
        protected int motionBlur = 0;
        protected double lastTime =  0;
        private float layerAlpha = 1.0f;
        private double shutterAngle = 180.0;
        private double shutterPhase = 0.0;
        private boolean buffer2 = false;

        protected SequenceExportEventListener el = null;
        /** creates a new instance of a sequence exporter.  by default, the exporter updates the canvas during export.
         *@param p the drawing canvas
         *@param s the animation sequence
         *@param dur the desired duration of movie (seconds)
         *@param frameRate the desired frameRate of the movie (fps)
         */
        public SequenceExporter(Component p,Sequence s, double dur, float frameRate){
                System.err.println("Duration: " + dur);
                System.err.println("FrameRate: " + frameRate);
                panel = p;
                seq = s;
                duration = dur*1000;
                this.frameRate = frameRate;			
                d = panel.getSize();
                screenRect = new Rectangle(d);
                bufferedImg1 = new BufferedImage(d.width,d.height,BufferedImage.TYPE_INT_RGB );
                bufferedImg2 = new BufferedImage(d.width, d.height,BufferedImage.TYPE_INT_RGB);
                graphicsHandle1 = bufferedImg1.createGraphics();
                graphicsHandle1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                graphicsHandle1.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                graphicsHandle1.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
                graphicsHandle1.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
                graphicsHandle1.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);		
                graphicsHandle2 = bufferedImg2.createGraphics();
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        }
        /** set if you want to be able to respond to the exporter events such as a progress bar
         *@param e an export event listener
         */
        
        public void setEventListener(SequenceExportEventListener e){
            el = e;
        }
        public boolean endOfMedia() {
                return isComplete;
        }

        public int getWidth(){
                return panel.getSize().width;
        }

        public int getHeight(){
                return panel.getSize().height;
        }

        public float getFrameRate(){
                return frameRate;
        }
        public void updateCanvas(boolean update){
            updateScreen = update;
        }

        /** allows you to set the motion blur output of the exporter.  This is initially set to 0 (no temporal super sampling).  16 would be considered high quality motion bluring.
         *@param blur number of temporally super sampled to render
         */
        public void setMotionBlur(int blur){
             if(blur <= 0)
                 motionBlur = 0;
             else{
                 motionBlur = blur;
                layerAlpha = 1.0f/motionBlur;
                }
        }
        /** allows you to set the motion blur output of the exporter.  This is initially set to 0 (no temporal super sampling).  16 would be considered high quality motion bluring.  Shutter angle and shutter phase allow greater control of the motion blurring.  Shutter angle (0-360 degrees) controls how much of the interframe interval is closed and shutterPhase (0-360 degrees) determines the location of the "open time" is in the inter frame interval.
         *@param blur number of images to render per frame
         *@param shutterAngle shutter angle 0-360 degrees.  180 by default
         *@param shutterPhase shutter phase 0-360 degrees. 0 by default.
         */
        public void setMotionBlur(int blur, double shutterAngle, double shutterPhase){
            setMotionBlur(blur);
            //shutter angle - portion of the interval to grab 0 - 360
            //shutter phase - position in interval to start grabbing 0 - 360

            this.shutterAngle = shutterAngle;
            this.shutterPhase = shutterPhase;        
        }
        public BufferedImage read(long frameNumber){
            double time = 1000*frameNumber/frameRate;
            if(time >= duration)
                    isComplete = true;

            graphicsHandle1.setTransform(identity);
            graphicsHandle1.setBackground(java.awt.Color.white);
            graphicsHandle1.clearRect(0, 0, d.width, d.height);

            if(motionBlur > 1){
                graphicsHandle2.setTransform(identity);
                graphicsHandle2.setBackground(java.awt.Color.white);
                graphicsHandle2.clearRect(0, 0, d.width, d.height);

                //shutter angle - portion of the interval to grab 0 - 360
                //shutter phase - position in interval to start grabbing 0 - 360
                double timeInc = (shutterAngle/360.0)*(time-lastTime)/motionBlur;
                lastTime += (shutterPhase/360.0)*(time-lastTime);
                for(int i = 0; i < motionBlur; i++){
                    lastTime += timeInc;
                    seq.update(lastTime);
                    graphicsHandle2.setTransform(identity);
                    graphicsHandle2.setColor(java.awt.Color.white);
                    graphicsHandle2.fill(screenRect);
                    seq.draw(graphicsHandle2,identity);
                    graphicsHandle1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f/(i+1)));
                    graphicsHandle1.setTransform(identity);
                    graphicsHandle1.drawImage(bufferedImg2, 0, 0, null);
                }
                lastTime = time;
                if(el != null)
                    el.onExportNewFrame(lastTime);
                if(updateScreen)		
                        (panel.getGraphics()).drawImage(bufferedImg1, 0, 0, panel);
                return bufferedImg1;
            }
            else{
                graphicsHandle1.setBackground(java.awt.Color.white);
                graphicsHandle1.clearRect(0, 0, d.width, d.height);
                seq.update(time);
                seq.draw(graphicsHandle1);
                lastTime = time;
                if(el != null)
                    el.onExportNewFrame(lastTime);
                if(updateScreen)		
                        (panel.getGraphics()).drawImage(bufferedImg1, 0, 0, panel);
                return bufferedImg1;
            }
        
}

        public void export(String filename,float quality) {
            if(filename.endsWith(".mov")){
                isComplete = false;
                //System.err.println("Starting------------------------");
                if(el != null)
                    el.onExportBegin();
                BufferedImageToQTEncoder encoder = new BufferedImageToQTEncoder(filename, this,quality);
                encoder.run();
                if(el != null)
                    el.onExportFinish();
                //System.err.println("Done------------------------");
            }
            else
                System.out.println("File name must end in .mov!!!!");
        }
}