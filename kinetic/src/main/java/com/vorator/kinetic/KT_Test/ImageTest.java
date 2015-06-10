/*
 * ImageTest.java
 *
 * Created on November 20, 2002, 6:57 PM
 */

package com.vorator.kinetic.KT_Test;
import com.vorator.kinetic.engine.*;
import com.vorator.kinetic.engine.util.*;

import java.awt.*;
import java.net.URL;

/**
 *
 * @author  Johnny Lee
 */
public class ImageTest implements SequenceBuilder{
    
    /** Creates a new instance of ImageTest */
    public ImageTest() {
    }
    
    /** constructs the animation using the given parameters
     * @param seq the sequence that will contain the resulting animation
     * @param stageSize dimensions of the canvas
     * @param g2 graphics handle
     */
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {

        KineticImage ki;
        
        ki = new KineticImage("google",0,10000);
        try{ ki.load("/Users/apple/temp/watermark.png");}
        catch (Exception x){ System.out.println(x);}
        ki.setPosition(320,240);
        ki.setScale(2,2);
        ki.rotation.addBehavior(new Curve(0,10000,0,1,10000));
        seq.addCastMember(ki);

        seq.setDuration(10000);

    }
    
}
