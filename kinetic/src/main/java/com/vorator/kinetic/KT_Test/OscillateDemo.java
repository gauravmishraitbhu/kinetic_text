/*
 * OscillateDemo.java
 *
 * Created on October 18, 2002, 2:03 AM
 */

package com.vorator.kinetic.KT_Test;
import com.vorator.kinetic.engine.*;
import com.vorator.kinetic.engine.util.*;

import java.awt.*;
/**
 *
 * @author  Johnny Lee
 */
public class OscillateDemo implements SequenceBuilder{
    
    /** Creates a new instance of EarlyDemos */
    public OscillateDemo() {
    }
    
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {
            //instantiate some variable names
        KineticString ks;
        Behavior b;
        double dur = 20000;
        Sequence sub = new Sequence("",10,dur);
        seq.setDuration(dur);
        seq.addCastMember(sub);
        double rate = 50;
        
        //position
        ks= new KineticString();
        ks.setString("Position");
        ks.setOrientation(KineticString.MIDDLE_CENTER);
        ks.setPosition(180,150);
        ks.setFont("Arial",Font.PLAIN,35.0);
        ks.setColor(0,0,0,1);
        ks.x.addBehavior(new Oscillate(0,dur,15,rate,0));
        sub.addCastMember(ks);

        //color
        ks= new KineticString();
        ks.setString("Color");
        ks.setOrientation(KineticString.MIDDLE_CENTER);
        ks.setPosition(100,250);
        ks.setFont("Arial",Font.PLAIN,35.0);
        ks.setColor(0,.5,0,.99);
        ks.green.addBehavior(new Oscillate(0,dur,.5,rate,0));
        //ks.green.addBehavior(new Oscillate(0,dur,.5,rate,3.1415));
        //ks.blue.addBehavior(new Oscillate(0,dur,.5,rate,3.1415));
        sub.addCastMember(ks);

        //fontsize
        ks= new KineticString();
        ks.setString("FontSize");
        ks.setOrientation(KineticString.MIDDLE_CENTER);
        ks.setPosition(460,150);
        ks.setFont("Arial",Font.PLAIN,35.0);
        ks.setColor(0,0,0,1);
        ks.font_size.addBehavior(new Oscillate(0,dur,20,rate,0));
        sub.addCastMember(ks);

        //tracking
        ks= new KineticString();
        ks.setString("Tracking");
        ks.setOrientation(KineticString.MIDDLE_CENTER);
        ks.setPosition(320,250);
        ks.setFont("Arial",Font.PLAIN,35.0);
        ks.setColor(0,0,0,1);
        ks.tracking.addBehavior(new Oscillate(0,dur,.2,rate,0));
        sub.addCastMember(ks);

        //fontface
        ks= new KineticString();
        ks.setString("FontFace");
        ks.setOrientation(KineticString.MIDDLE_CENTER);
        ks.setPosition(540,250);
        ks.setFont("Arial",Font.PLAIN,35.0);
        ks.setColor(0,0,0,1);
        int count = ks.font_face.getSize();
        ks.font_face.setValue(10);
        ks.font_face.addBehavior(new Oscillate(0,dur,10,rate,0));
        sub.addCastMember(ks);
        sub.setPosition(-15,-10);
    }
}
