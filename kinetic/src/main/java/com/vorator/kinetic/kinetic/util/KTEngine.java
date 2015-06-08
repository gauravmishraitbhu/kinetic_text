/*
 * KTEngine.java
 *
 * Created on August 30, 2002, 3:03 AM
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

 *
 */

package com.vorator.kinetic.kinetic.util;
import com.vorator.kinetic.kinetic.*;
import com.vorator.kinetic.kinetic.util.Composites.*;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.geom.AffineTransform;
/** Utiltiy class that implements an animation thread that takes aniamtion sequence and will draw double buffered images to the screen.  The animation can be stop, reset, or set to a specific time.  This drawing thread also supports motion blur if the processing power if available in real time to support it.
 * @author Johnny Lee
 */
public class KTEngine implements Runnable, java.io.Serializable{
    
    private Sequence MainSequence = new Sequence("Main Sequence",0.0,0.0);
    private double EditTime = 0.0;
    private Component stage;
    private Rectangle screenRect;
    private Dimension d;
    private SequenceBuilder builder;
    private BufferedImage bufferedImg1;
    private BufferedImage bufferedImg2;
    private Graphics2D graphicsHandle1;
    private Graphics2D graphicsHandle2;
    private boolean buffer2 = false;
    private KTEngineEventListener eventListener = null;
    private boolean isRunning = false;
    //variables for handling animation thread
    private Thread AnimationThread;
    private double AnimationSpeed = 1.0;
    private double AnimationTime = 0.0;
    private double FrameDelay = 30;
    private double last_callTime = 0.0;
    private double lastTime = 0.0;
    private int motionBlur = 0;
    private float layerAlpha = 1.0f;
    private double shutterAngle = 180.0;
    private double shutterPhase = 0.0;
    /** anti aliasing flag for drawing, can be turned off to boost performance
     *
     */    
    public boolean antialiasing = true;
    
    /** Creates a new instance of KTEngine */
    public KTEngine() {
    }
    
    /** sets the motion blur value
     * @param blur number of temporally super sampled images to render per frame
     * @param shutterAngle portion of the time step to take the super sampled images from.  360 degrees means the shutter never closes.
     * @param shutterPhase position of the portion of the timestep for taking the super sampled images.  0-360 degrees.
     */    
    public void setMotionBlur(int blur, double shutterAngle, double shutterPhase){
        setMotionBlur(blur);
        //shutter angle - portion of the interval to grab 0 - 360
        //shutter phase - position in interval to start grabbing 0 - 360
        this.shutterAngle = shutterAngle;
        this.shutterPhase = shutterPhase;        
    }
    
    /** set the motion blur (assumes default values for other parameters)
     * @param blur number of temporally super sampled images to render per frame
     */    
    public void setMotionBlur(int blur){
     if(blur <= 0)
         motionBlur = 0;
     else{
         motionBlur = blur;
         layerAlpha = 1.0f/blur;
        }
    }
    /** start the animation thread fro the last given animation time
     *
     */    
    public void run(){
          Thread me = Thread.currentThread();
        double temp;
        while (AnimationThread == me) {
            try {
                AnimationTime = lastTime +(System.currentTimeMillis() - last_callTime);
                last_callTime = System.currentTimeMillis();
                if(AnimationTime > MainSequence.getDuration()){
                        draw(MainSequence.getDuration()-1);
                        stop();
                        if(eventListener != null)
                               eventListener.onAnimationFinished(AnimationTime);
                        }
                else{
                    draw(AnimationTime);
                }
            } catch (Exception e) { 
                System.err.println(e);
                return; 
                }
        }
        AnimationThread = null;      
    }

    /** starts the animation thread from time 0ms
     */    
    public void start() {
        if(isRunning)
            stop();
        AnimationThread = new Thread(this);
        AnimationThread.setPriority(Thread.MIN_PRIORITY);
        last_callTime = System.currentTimeMillis();
        AnimationThread.start();
        isRunning = true;
        if(eventListener != null)
               eventListener.onAnimationStart(AnimationTime);
    }
    /** stops the animation thread
     */    
    public synchronized void stop() {
        AnimationThread = null;
        isRunning = false;
        if(eventListener != null)
               eventListener.onAnimationStop(AnimationTime);
    }
    
    /** check to see if the animation is still running
     * @return boolean flag if the animation thread is currently running
     */    
    public boolean isRunning(){
        return isRunning;
    }
    /** sets the animation time of the engine
     * @param t the desired aniamtione time (ms)
     */    
    public void setAnimationTime(double t){
        AnimationTime = t;
        lastTime = t;
    }
    /** reset the animation time to 0ms
     */    
    public void resetAnimationTime(){
        AnimationTime = 0.0;
        lastTime = 0.0;
        draw(0);
    }
    /** gets the position of the playback head the last time the animation thread was called.
     * @return position of the play head (ms)
     */    
    public double getLastTime(){
     return lastTime;   
    }
    /** set the drawing canvas for the engine
     * @param newStage a AWT component that will be used as the drawing canvas for the animation
     */    
    public void setStage(Component newStage){
        stage = newStage;		
        d = stage.getSize();
        screenRect = new Rectangle(d);
        bufferedImg1 = new BufferedImage(d.width, d.height,BufferedImage.TYPE_INT_RGB);
        bufferedImg2 = new BufferedImage(d.width, d.height,BufferedImage.TYPE_INT_RGB);
        graphicsHandle1 = bufferedImg1.createGraphics();
        if(antialiasing){
            graphicsHandle1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphicsHandle1.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        else{
            graphicsHandle1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
            graphicsHandle1.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
            
        graphicsHandle1.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
        graphicsHandle1.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
        graphicsHandle1.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);

        graphicsHandle2 = bufferedImg2.createGraphics();
        if(antialiasing){
            graphicsHandle2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphicsHandle2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        else{
            graphicsHandle2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
            graphicsHandle2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);   
        }
        graphicsHandle2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
        graphicsHandle2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
        graphicsHandle2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
    }
    
    /** renders the animation at a specified time on the current canvas
     * @param time position of the play head in the animation
     */    
    public void draw(double time){
        if(eventListener != null)
            eventListener.onAnimationNewFrame(AnimationTime);
        
        if(stage == null)
            return;
        
        graphicsHandle1 = null;
        d = stage.getSize();
        screenRect.width = d.width;
        screenRect.height = d.height;
        if (bufferedImg1 == null) {
            bufferedImg1 = new BufferedImage(d.width, d.height,BufferedImage.TYPE_INT_RGB);
        }
        graphicsHandle1 = bufferedImg1.createGraphics();
        if(antialiasing){
            graphicsHandle1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphicsHandle1.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        else{
            graphicsHandle1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
            graphicsHandle1.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
        graphicsHandle1.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
        graphicsHandle1.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
        graphicsHandle1.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);

        if(motionBlur > 1){
            AffineTransform identity = new AffineTransform();
            if(bufferedImg2 == null)
                bufferedImg2 = new BufferedImage(d.width, d.height,BufferedImage.TYPE_INT_RGB);
            if(antialiasing){
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            }
            else{
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
                graphicsHandle2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);   
            }
            graphicsHandle2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphicsHandle2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
            graphicsHandle2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
            graphicsHandle2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            
            //shutter angle - portion of the interval to grab 0 - 360
            //shutter phase - position in interval to start grabbing 0 - 360
            
            double timeInc = (shutterAngle/360.0)*(time-lastTime)/motionBlur;
            lastTime += (shutterPhase/360.0)*(time-lastTime);
            //this one is slightely better (linear blending), but takes 4-5 times longer
            //graphicsHandle1.setComposite(new AddComposite(motionBlur));
            graphicsHandle1.setBackground(java.awt.Color.white);
            graphicsHandle1.setColor(java.awt.Color.white);
            graphicsHandle1.fill(screenRect);
            graphicsHandle1.setColor(java.awt.Color.white);
            graphicsHandle2.setBackground(java.awt.Color.white);
            graphicsHandle2.fill(screenRect);
            for(int i = 0; i < motionBlur; i++){
                lastTime += timeInc;
                MainSequence.update(lastTime);
                graphicsHandle2.setTransform(identity);
                graphicsHandle2.setColor(java.awt.Color.white);
                graphicsHandle2.fill(screenRect);
                MainSequence.draw(graphicsHandle2,identity);
                graphicsHandle1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f/(i+1)));
                graphicsHandle1.setTransform(identity);
                graphicsHandle1.drawImage(bufferedImg2, 0, 0, null);                               
            }
            (stage.getGraphics()).drawImage(bufferedImg1, 0, 0, stage);
            //graphicsHandle1.dispose();
            //graphicsHandle2.dispose();
        }
        else{
            graphicsHandle1.setBackground(java.awt.Color.white);
            graphicsHandle1.clearRect(0, 0, d.width, d.height);
            MainSequence.update(time);
            MainSequence.draw(graphicsHandle1);
            (stage.getGraphics()).drawImage(bufferedImg1, 0, 0, stage);
            //graphicsHandle1.dispose();
        }
        
        lastTime = time;
    }
    
    /** sets an event listener that can respond to event generated by the engine
     * @param newEventListener an engine event listener
     */    
    public void setKTEngineEventListener(KTEngineEventListener newEventListener){
        eventListener = newEventListener;
    }
    
    /** returns the animation sequence associated with the engine
     * @return animation sequence
     */    
    public Sequence getSequence(){
        return MainSequence;
    }
    /** sets the animation sequence associated with the engine
     * @param seq animation sequence
     */    
    public void setSequence(Sequence seq){
        MainSequence = seq;
        
    }

    /** sets the sequence builder associated with the engine.  Utility function or easily inserting programmatically defined animations
     * @param b sequence builder
     */    
    public void setSequenceBuilder(SequenceBuilder b){
        builder = b;
    }
    
    /** builds the animation with the sequence builder associated with the engine
     */    
    public void buildAnimation(){
       builder.buildSequence(MainSequence, graphicsHandle1,screenRect);
    }
}