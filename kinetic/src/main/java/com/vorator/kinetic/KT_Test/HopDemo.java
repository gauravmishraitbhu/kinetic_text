/*
 * HopDemo.java
 *
 * Created on October 18, 2002, 2:18 AM
 */

package com.vorator.kinetic.KT_Test;
import com.vorator.kinetic.engine.*;
import com.vorator.kinetic.engine.util.*;

import java.awt.*;

/**
 *
 * @author  Johnny Lee
 */
public class HopDemo implements SequenceBuilder{
    
    /** Creates a new instance of HopDemo */
    public HopDemo() {
    }
    
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {
        Behavior b,b2;
        Sequence temp_seq;
        KineticString word;
        TimeFilter tf;
        double dur = 30000;
        double delay1 = 10;
        double delay2 = 6000;
        double delay3 = 12000;
        double delay4 = 18000;
        double speed = 1500;
        seq.setDuration(dur);
        //Hop-------------------------------------------------------------

        Sequence Hop = new Sequence("",delay1,dur);
        Hop.setPosition(0,-50);
        seq.addCastMember(Hop);
        temp_seq = new Sequence("",0,dur);
        Hop.addCastMember(temp_seq);
        word = new KineticString();
        temp_seq.addCastMember(word);
        word.setString("Hop");
        word.setPosition(100,440);
        word.setOrientation(KineticObject.MIDDLE_CENTER);
        word.setFont("Arial Black",Font.PLAIN, 40);
        word.setColor(0,0,0,1.0);

        b= new Hop(0, speed,.7,.7,-300);
        word.y.addBehavior(b);
        temp_seq.addTimeFilter(new Loop(speed));

        temp_seq = new Sequence("",delay2,dur);
        Hop.addCastMember(temp_seq);
        word = new KineticString();
        temp_seq.addCastMember(word);
        word.setString("Hop");
        word.setPosition(300,440);
        word.setOrientation(KineticObject.MIDDLE_CENTER);
        word.setFont("Arial Black",Font.PLAIN, 40);
        word.setColor(0,0,0,1.0);

        b= new Hop(0, speed,1,1, -300);
        temp_seq.addTimeFilter(new Loop(speed));
  //      b.addTimeFilter(new PaceInOut(0.6,0.6,speed));
        word.y.addBehavior(b);

        temp_seq = new Sequence("",delay3,dur);
        Hop.addCastMember(temp_seq);
        word = new KineticString();
        temp_seq.addCastMember(word);
        word.setString("Hop");
        word.setPosition(500,440);
        word.setOrientation(KineticObject.MIDDLE_CENTER);
        word.setFont("Arial Black",Font.PLAIN, 40);
        word.setColor(0,0,0,1.0);

        b= new Hop(0, speed,1,1, -300);
        temp_seq.addTimeFilter(new Loop(speed));
        word.y.addBehavior(b);
        b= new HopSecondary(0, speed, 1,1, 0.3);
        word.yScale.addBehavior(b);
        b = new HopSecondary(0, speed, 1,1, -0.1);
        word.xScale.addBehavior(b);    
        
        temp_seq = new Sequence("",delay4,dur);
        Hop.addCastMember(temp_seq);
        KineticRectangle ks = new KineticRectangle();
        temp_seq.addCastMember(ks);
        ks.setSize(640,400);
        ks.setColor(1,1,1,1);
        ks.setPosition(120,300);
    }
}
