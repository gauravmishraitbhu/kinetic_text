/*
 * MoveDemo.java
 *
 * Created on October 18, 2002, 2:17 AM
 */

package com.vorator.kinetic.KT_Test;
import com.vorator.kinetic.kinetic.*;
import com.vorator.kinetic.kinetic.util.*;
import java.awt.*;

/**
 *
 * @author  Johnny Lee
 */
public class MoveDemo implements SequenceBuilder{
    
    /** Creates a new instance of MoveDemo */
    public MoveDemo() {
    }
    
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {
        Behavior b,b2;
        Sequence temp_seq;
        KineticString word;
        TimeFilter tf;
        double dur = 35000;
        double cut = 4000;
        double delay1 = 10;
        double delay2 = 6000;
        double delay3 = 12000;
        double delay4 = 18000;
        double delay5 = 24000;
        double speed = 1500;
        seq.setDuration(dur);


        //Start Tech Sequence **********************************************************
        Sequence Move = new Sequence("",0,dur);
        seq.addCastMember(Move);
        //simple Move A--------------------------------------------------
        temp_seq = new Sequence("",delay1,dur);
        Move.addCastMember(temp_seq);
        word = new KineticString();
        temp_seq.addCastMember(word);
        word.setString("Move");
        word.setPosition(100,100);
        word.setOrientation(KineticObject.BOTTOM_CENTER);
        word.setFont("Arial Black",Font.PLAIN, 40);
        word.setColor(0,0,0,1.0);

        b= new Change(0, speed,440);
        word.x.addBehavior(b);
        b= new Change(speed, speed,-440);
        word.x.addBehavior(b);
        temp_seq.addTimeFilter(new Loop(speed*2));

        temp_seq = new Sequence("",delay2,dur);
        Move.addCastMember(temp_seq);
        word = new KineticString();
        temp_seq.addCastMember(word);
        word.setString("Move");
        word.setPosition(100,200);
        word.setOrientation(KineticObject.BOTTOM_CENTER);
        word.setFont("Arial Black",Font.PLAIN, 40);
        word.setColor(0,0,0,1.0);

        b= new Curve(0, speed,0,0,440);
        word.x.addBehavior(b);
        b= new Curve(speed, speed,0,0,-440);
        word.x.addBehavior(b);
        temp_seq.addTimeFilter(new Loop(speed*2));

        temp_seq = new Sequence("",delay3,dur);
        Move.addCastMember(temp_seq);
        word = new KineticString();
        temp_seq.addCastMember(word);
        word.setString("Move");
        word.setPosition(100,300);
        word.setOrientation(KineticObject.BOTTOM_CENTER);
        word.setFont("Arial Black",Font.PLAIN, 40);
        word.setColor(0,0,0,1.0);

        b= new SymmetricCurve(0, speed,1.5,440);
        word.x.addBehavior(b);
        b= new SymmetricCurve(speed, speed,1.5,-440);
        word.x.addBehavior(b);
        temp_seq.addTimeFilter(new Loop(speed*2));

        temp_seq = new Sequence("",delay4,dur);
        Move.addCastMember(temp_seq);
        word = new KineticString();
        temp_seq.addCastMember(word);
        word.setString("Move");
        word.setPosition(100,400);
        word.setOrientation(KineticObject.BOTTOM_CENTER);
        word.setFont("Arial Black",Font.PLAIN, 40);
        word.setColor(0,0,0,1.0);

        b= new SymmetricCurve(0, speed,1.5,440);
        word.x.addBehavior(b);
        b= new SymmetricCurve(speed, speed,1.5,-440);
        word.x.addBehavior(b);
        temp_seq.addTimeFilter(new Loop(speed*2));

        b= new SymmetricCurveSecondary(0, speed,1.5,.5);
        word.xShear.addBehavior(b);
        b= new SymmetricCurveSecondary(speed, speed,1.5,-.5);
        word.xShear.addBehavior(b);
        temp_seq.addTimeFilter(new Loop(speed*2));
        
        temp_seq = new Sequence("",delay5,dur);
        Move.addCastMember(temp_seq);
        KineticRectangle ks = new KineticRectangle();
        temp_seq.addCastMember(ks);
        ks.setSize(640,320);
        ks.setColor(1,1,1,1);
        ks.setPosition(320,200);
    }
}
