/*
 * EarlyDemos.java
 *
 * Created on October 17, 2002, 12:45 PM
 */

package com.vorator.kinetic.KT_Test;
import com.vorator.kinetic.kinetic.*;
import com.vorator.kinetic.kinetic.util.*;
import java.awt.*;
/**
 *
 * @author  Johnny Lee
 */
public class EarlyDemos implements SequenceBuilder{
    
    /** Creates a new instance of EarlyDemos */
    public EarlyDemos() {
    }
    
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {
    // create sequences********************************************************
    Behavior b,b2;
    Sequence temp_seq;
    KineticString word;
    TimeFilter tf;

    seq.setScale(1,1);
    seq.addTimeFilter(new Speed(1.0));
    seq.setDuration(70000);
    int mode = 0;
    int Intro_start = 0;
    int Intro_length = 0;
    int IceCream_start = 0;
    int IceCream_length = 0;
    int Rhythm_start = 0;
    int Rhythm_length = 0;
    int Shower_start = 0;
    int Shower_length = 0;
    int Color_start = 0;
    int Color_length = 0;
    int Salesman_start = 0;
    int Salesman_length = 0;
    int Cheer_start = 0;
    int Cheer_length = 0;
    int Tree_start = 0;
    int Tree_length = 0;
    int End_start = 0;
    int End_length = 0;
    int Tech_start = 0;
    int Tech_length = 0;
    switch(mode)
            {
            case 1:
                    Intro_start = 1000;
                    Intro_length = 4000;
                    break;
            case 2:
                    IceCream_start = 1000;
                    IceCream_length = 7000;
                    break;
            case 3:
                    Rhythm_start = 1000;
                    Rhythm_length = 17000;
                    break;
            case 4:
                    Shower_start = 000;
                    Shower_length = 18000;
                    break;
            case 5:
                    Color_start = 1000;
                    Color_length = 3000;
                    break;
            case 6:
                    Salesman_start = 1000;
                    Salesman_length = 18000;
                    break;
            case 7:
                    Cheer_start = 1000;
                    Cheer_length = 9000;
                    break;
            case 8:
                    Tree_start = 1000;
                    Tree_length = 8000;
                    break;
            case 9:
                    End_start = 1000;
                    End_length = 9000;
                    break;
            case 10:
                    Tech_start = 1000;
                    Tech_length = 1000000;
                    break;
            default:
                    Intro_start = 1000;
                    Intro_length = 4000;
                    IceCream_start = Intro_start + Intro_length;
                    IceCream_length = 7000;
                    Rhythm_start = IceCream_start + IceCream_length + 1000;
                    Rhythm_length = 13000;
                    Shower_start = Rhythm_start + Rhythm_length + 1000;
                    Shower_length = 16000;
                    Salesman_start = Shower_start + Shower_length + 1000;
                    Salesman_length = 10000;
                    Cheer_start = Salesman_start + Salesman_length + 1000;
                    Cheer_length = 9000;
                    Tree_start = Cheer_start + Cheer_length + 1000;
                    Tree_length = 8000;
                    End_start = Tree_start + Tree_length + 1000;
                    End_length = 9000;
                    break;
            }


    //Start Intro Sequence **********************************************************
    Sequence Intro = new Sequence("",Intro_start,Intro_length);
    seq.addCastMember(Intro);

    word = new KineticString();
    Intro.addCastMember(word);
    word.setString("demo start");
    word.setPosition(320,240);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 20);
    word.setColor(0.5,0.5,0.5,0.0);

    b = new Change(0,1000,1);
    word.alpha.addBehavior(b);

    b = new SymmetricCurve(3000, 1000,2,500);
    word.x.addBehavior(b);

    b = new SymmetricCurveSecondary(3000, 1000,2,0.5);
    word.xShear.addBehavior(b);
    //End Intro Sequence **********************************************************

    //Start IceCream Sequence **********************************************************
    Sequence IceCream = new Sequence("",IceCream_start,IceCream_length);
    seq.addCastMember(IceCream);
    int hop_rate = 350;

    word = new KineticString();
    IceCream.addCastMember(word);
    word.setString("who wants ice cream?");
    word.setPosition(-300,300);
    word.setFont("Arial Narrow",Font.PLAIN, 40);
    word.setColor(0,0,0,.99);
    word.setOrientation(KineticObject.BOTTOM_CENTER);

    b= new Change(1050, 150, 620);
    word.x.addBehavior(b);
    b = new Pulse(1200, 500, 0.3,20,1,0);
    word.xShear.addBehavior(b);

    word = new KineticString();
    IceCream.addCastMember(word);
    word.setString("me");
    word.setPosition(320,910);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 80);
    word.setColor(0.7,0.7,0.7,.99);

    b= new Change(1900,100,-500);
    word.y.addBehavior(b);


    b= new Hop(0, hop_rate, 1,1,-200);
    word.y.addBehavior(b);
    b.addTimeFilter(new Loop(hop_rate));
//    b.addTimeFilter(new PaceInOut(0.5,0.5,hop_rate));

    b= new HopSecondary(0, hop_rate, 1,1,0.2);
    word.yScale.addBehavior(b);
    b.addTimeFilter(new Loop(hop_rate));
//    b.addTimeFilter(new PaceInOut(0.5,0.5,hop_rate));

    b= new HopSecondary(0, hop_rate, 1,1,-0.1);
    word.xScale.addBehavior(b);
    b.addTimeFilter(new Loop(hop_rate));
  //  b.addTimeFilter(new PaceInOut(0.5,0.5,hop_rate));
    
    b= new Jitter(0, 50000, 0,30);
    b2 = new Hop(0, hop_rate, 0,0,50);
    word.rotation.addBehavior(b);
    b2.addTimeFilter(new Loop(hop_rate));
    ((Jitter)b).amount.addBehavior(b2);
    //End IceCream Sequence **********************************************************


    //Start Rhythm Sequence **********************************************************
    Sequence Rhythm = new Sequence("",Rhythm_start,Rhythm_length);
    int left_point = 110;

    int R_rate = 1000;
    int H1_rate = 1000;
    int Y_rate = 1000;
    int T_rate = 1000;
    int H2_rate = 1000;
    int M_rate = 1000;
    int r_length = 13000;
    int r_hold = 0000;

    int R_delay = 8000;
    int H1_delay = 3000;
    int Y_delay = 0;
    int T_delay = 2500;
    int H2_delay = 3500;
    int M_delay = 8500;

    seq.addCastMember(Rhythm);
    temp_seq = new Sequence("",R_delay,r_length + r_hold);
    temp_seq.delay.amount.addBehavior(new Curve(0,r_length-0,1,0,-R_delay));
    Rhythm.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("R");//--------------------------------------------
    word.setPosition(left_point,240);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 100);
    word.setColor(0,0,0,.99);

    b = new Curve(0, 500, 1,0,-.99);
    tf = new Loop(R_rate);
    b.addTimeFilter(tf);
    word.alpha.addBehavior(b);

    temp_seq = new Sequence("",H1_delay,r_length + r_hold);
    temp_seq.delay.amount.addBehavior(new Curve(0,r_length-0,1,0,-H1_delay));
    Rhythm.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("H");//--------------------------------------------
    word.setPosition(left_point+75,240);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 100);
    word.setColor(0,0,0,.99);

    b = new Curve(0, 500, 1,0,-.99);
    tf = new Loop(H1_rate);
    b.addTimeFilter(tf);
    word.alpha.addBehavior(b);


    temp_seq = new Sequence("",Y_delay,r_length + r_hold);
    Rhythm.addCastMember(temp_seq);
    temp_seq.delay.amount.addBehavior(new Curve(0,r_length-0,1,0,-Y_delay));
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Y");//--------------------------------------------
    word.setPosition(left_point+160,240);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 100);
    word.setColor(0,0,0,1);

    b = new Curve(0, 500, 1,0,-.99);
    tf = new Loop(Y_rate);
    b.addTimeFilter(tf);
    word.alpha.addBehavior(b);


    temp_seq = new Sequence("",T_delay,r_length + r_hold);
    Rhythm.addCastMember(temp_seq);
    temp_seq.delay.amount.addBehavior(new Curve(0,r_length-0,1,0,-T_delay));
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("T");//--------------------------------------------
    word.setPosition(left_point+235,240);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 100);
    word.setColor(0,0,0,.99);

    tf = new Loop(T_rate);
    b = new Curve(0, 500, 1,0,-.99);
    b.addTimeFilter(tf);
    word.alpha.addBehavior(b);

    temp_seq = new Sequence("",H2_delay,r_length + r_hold);
    Rhythm.addCastMember(temp_seq);
    temp_seq.delay.amount.addBehavior(new Curve(0,r_length-0,1,0,-H2_delay));
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("H");//--------------------------------------------
    word.setPosition(left_point+305,240);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 100);
    word.setColor(0,0,0,.99);

    tf = new Loop(H2_rate);
    b = new Curve(0, 500, 1,0,-.99);
    b.addTimeFilter(tf);
    word.alpha.addBehavior(b);

    temp_seq = new Sequence("",M_delay,r_length + r_hold);
    Rhythm.addCastMember(temp_seq);
    temp_seq.delay.amount.addBehavior(new Curve(0,r_length-0,1,0,-M_delay));
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("M");//--------------------------------------------
    word.setPosition(left_point+390,240);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 100);
    word.setColor(0,0,0,.99);

    tf = new Loop(M_rate);
    b = new Curve(0, 500, 1,0,-.99);
    b.addTimeFilter(tf);
    word.alpha.addBehavior(b);
    //End Rhythm Sequence **********************************************************

    //Start Shower Sequence **********************************************************
    Sequence Shower = new Sequence("",Shower_start,Shower_length);
    seq.addCastMember(Shower);
    temp_seq = new Sequence("",0,4000);
    Shower.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Shower");
    word.setPosition(320,240);
    word.setOrientation(KineticObject.MIDDLE_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 20);
    word.setColor(0,0,0,0.0);

    word.alpha.addBehavior( new Change (0,1000,1));
    word.alpha.addBehavior( new Change (2000,1000,-1));

    double d,s,x,y,r;
    for(int i = 0 ; i < 150; i++)
    {
//            d = Math.random()*12000;
            d = i*12000.0/160.0;
            s = .8;
            x = 400*(Math.random()-.5);
            y = 400*(Math.random()-.5);
            r = 30*(Math.random()-.5);
            temp_seq = new Sequence("",2000+d,1000);
            Shower.addCastMember(temp_seq);
            word = new KineticString();
            temp_seq.addCastMember(word);
            word.setString("drop");
            word.setPosition(320,240);
            word.setRotation(r);
            word.setOrientation(KineticObject.MIDDLE_CENTER);
            word.setFont("Arial Black",Font.PLAIN, 200);
            word.setScale(.1,.1);
            word.setColor(0,0,0,0);
            word.alpha.addBehavior( new Change(0,900,.89));
            word.alpha.addBehavior( new Change(900,100,-.89));
            word.xScale.addBehavior(new ExponentialChange(0,1000,5,s));
            word.yScale.addBehavior(new ExponentialChange(0,1000,5,s));
            word.x.addBehavior(new Change(0,1000,x));
            word.y.addBehavior(new Change(0,1000,y));
    }
    //End Shower Sequence **********************************************************
    
    //Start Salesman Sequence **********************************************************
    Sequence Salesman = new Sequence("",Salesman_start,Salesman_length);
    seq.addCastMember(Salesman);
    temp_seq = new Sequence("",0,6000);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Hi");
    word.setPosition(-300,240);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Narrow",Font.PLAIN, 200);
    word.setColor(0,0,0,.99);

    b= new Change(0, 150, 620);
    word.x.addBehavior(b);
    b = new Pulse(150, 700, 0.4,60,1,0);
    word.xShear.addBehavior(b);
    b= new Change(4400, 200, -.99);
    word.alpha.addBehavior(b);
    
    temp_seq = new Sequence("",1000,1200);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("how are you?");
    word.setPosition(-300,300);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Narrow",Font.PLAIN, 20);
    word.setColor(0,0,0,.99);

    b= new Change(0, 150, 620);
    word.x.addBehavior(b);
    b= new Change(0, 50, 0.5);
    word.xShear.addBehavior(b);
    b= new Change(150,0, -0.5);
    word.xShear.addBehavior(b);
    b= new Change(1000, 150, 620);
    word.x.addBehavior(b);
    b= new Change(1000, 50, 0.5);
    word.xShear.addBehavior(b);
    b = new Pulse(150, 500, 0.2,40,1,0);
    word.xShear.addBehavior(b);

    temp_seq = new Sequence("",2000,6000);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("would you like to buy some:");
    word.setPosition(-300,300);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 30);
    word.setColor(0,0,0,.99);

    b= new Change(0, 150, 620);
    word.x.addBehavior(b);
    b= new Change(0, 50, 0.5);
    word.xShear.addBehavior(b);
    b= new Change(150,0, -0.5);
    word.xShear.addBehavior(b);
    b = new Pulse(150, 500, 0.2,40,1,0);
    word.xShear.addBehavior(b);
    b= new Change(2400, 200, -.99);
    word.alpha.addBehavior(b);


    temp_seq = new Sequence("",2500,2000);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("cookies");
    word.setPosition(-300,340);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 20);
    word.setColor(0,0,0,.99);

    b= new Change(0, 150, 620);
    word.x.addBehavior(b);
    b= new Change(0, 50, 0.5);
    word.xShear.addBehavior(b);
    b= new Change(150,0, -0.5);
    word.xShear.addBehavior(b);
    b= new Change(2000, 150, 620);
    word.x.addBehavior(b);
    b= new Change(2000, 50, 0.5);
    word.xShear.addBehavior(b);
    b = new Pulse(150, 500, 0.2,40,1,0);
    word.xShear.addBehavior(b);

    temp_seq = new Sequence("",2700,1800);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("life insurance");
    word.setPosition(-300,360);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 20);
    word.setColor(0,0,0,.99);

    b= new Change(0, 150, 620);
    word.x.addBehavior(b);
    b= new Change(0, 50, 0.5);
    word.xShear.addBehavior(b);
    b= new Change(150,0, -0.5);
    word.xShear.addBehavior(b);
    b= new Change(1800, 150, 620);
    word.x.addBehavior(b);
    b= new Change(1800, 50, 0.5);
    word.xShear.addBehavior(b);
    b = new Pulse(150, 500, 0.2,40,1,0);
    word.xShear.addBehavior(b);

    temp_seq = new Sequence("",2900,1600);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("telephones");
    word.setPosition(-300,380);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 20);
    word.setColor(0,0,0,.99);

    b= new Change(0, 150, 620);
    word.x.addBehavior(b);
    b= new Change(0, 50, 0.5);
    word.xShear.addBehavior(b);
    b= new Change(150,0, -0.5);
    word.xShear.addBehavior(b);
    b= new Change(1600, 150, 620);
    word.x.addBehavior(b);
    b= new Change(1600, 50, 0.5);
    word.xShear.addBehavior(b);
    b = new Pulse(150, 500, 0.2,40,1,0);
    word.xShear.addBehavior(b);

    temp_seq = new Sequence("",3100,1400);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("junk");
    word.setPosition(-300,400);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 20);
    word.setColor(0,0,0,.99);

    b= new Change(0, 150, 620);
    word.x.addBehavior(b);
    b= new Change(0, 50, 0.5);
    word.xShear.addBehavior(b);
    b= new Change(150,0, -0.5);
    word.xShear.addBehavior(b);
    b= new Change(1400, 150, 620);
    word.x.addBehavior(b);
    b= new Change(1400, 50, 0.5);
    word.xShear.addBehavior(b);
    b = new Pulse(150, 500, 0.2,40,1,0);
    word.xShear.addBehavior(b);

    temp_seq = new Sequence("",5000,3000);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("no?");
    word.setPosition(320,240);
    word.setOrientation(KineticObject.MIDDLE_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 30);
    word.setColor(0,0,0,0.0);

    b= new Hop(0, 2000,0,0, .99);
    word.alpha.addBehavior(b);

    temp_seq = new Sequence("",5000,3000);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("?");
    word.setPosition(320,240);
    word.setOrientation(KineticObject.MIDDLE_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 20);
    word.setColor(0,0,0,0.3);

    b= new Change(0, 1000, -0.3);
    word.alpha.addBehavior(b);
    b= new Curve(0, 1000,1,0, 30);
    word.xScale.addBehavior(b);
    b= new Curve(0, 1000,1,0, 30);
    word.yScale.addBehavior(b);

    temp_seq = new Sequence("",7000,5000);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("why");
    word.setPosition(320,240);
    word.setOrientation(KineticObject.MIDDLE_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 40);
    word.setColor(0,0,0,0.0);

    b= new Change(0, 1000, .99);
    word.alpha.addBehavior(b);
    b= new Jitter(1000,1000,20,0);
    ((Jitter)b).amount.addBehavior(new Curve(0,1000,1,0,-20));
    word.x.addBehavior(b);
    b= new Jitter(1000,1000,20,0);
    ((Jitter)b).amount.addBehavior(new Curve(0,1000,1,0,-20));
    word.y.addBehavior(b);
    b= new Jitter(1000,1000,20,0);
    ((Jitter)b).amount.addBehavior(new Curve(0,1000,1,0,-20));
    word.rotation.addBehavior(b);
    b= new Change(2500, 500, -.99);
    word.alpha.addBehavior(b);

    temp_seq = new Sequence("",8000,2000);
    Salesman.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("?");
    word.setPosition(400,240);
    word.setOrientation(KineticObject.MIDDLE_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 160);
    word.setColor(0,0,0,.99);

    b= new Pulse(0,500,0.5,40,1,0);
    word.xScale.addBehavior(b);
    b= new Pulse(0,500,0.5,40,1,0);
    word.yScale.addBehavior(b);
    b= new Change(1000, 500, -.99);
    word.alpha.addBehavior(b);
    //End Salesman Sequence **********************************************************

    //Start Cheer Sequence **********************************************************
    Sequence Cheer = new Sequence("",Cheer_start,Cheer_length);
    seq.addCastMember(Cheer);
    double hopCurve = .7;
    double hopTime = 300;
    temp_seq = new Sequence("",0,5000);
    Cheer.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Yay!");
    word.setPosition(320,410);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 80);
    word.setColor(0,0,0,.99);

    b= new Hop(0, hopTime,hopCurve,hopCurve, -80);
    b.addTimeFilter(new Loop(hopTime));
    word.y.addBehavior(b);
    b= new HopSecondary(0, hopTime, hopCurve,hopCurve, 0.2);
    b.addTimeFilter(new Loop(hopTime));
    word.yScale.addBehavior(b);
    b = new HopSecondary(0, hopTime, hopCurve,hopCurve, -0.1);
    b.addTimeFilter(new Loop(hopTime));
    word.xScale.addBehavior(b);

    temp_seq = new Sequence("",0,5000);
    Cheer.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Yay!");
    word.setPosition(-100,410);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 40);
    word.setColor(0,0,0,.99);

    hopTime = 200;
    b= new Hop(0, hopTime,hopCurve,hopCurve, -80);
    b.addTimeFilter(new Loop(hopTime));
    word.y.addBehavior(b);
    b= new HopSecondary(0, hopTime, hopCurve,hopCurve, 0.2);
    b.addTimeFilter(new Loop(hopTime));
    word.yScale.addBehavior(b);
    b = new HopSecondary(0, hopTime, hopCurve,hopCurve, -0.1);
    b.addTimeFilter(new Loop(hopTime));
    word.xScale.addBehavior(b);

    b= new Curve(0, 3000,1,0,550);
    word.x.addBehavior(b);

    temp_seq = new Sequence("",0,5000);
    Cheer.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Yay!");
    word.setPosition(750,410);
    word.setOrientation(KineticObject.BOTTOM_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 60);
    word.setColor(0,0,0,.99);

    hopTime=250;
    b= new Hop(0, hopTime,hopCurve,hopCurve, -80);
    b.addTimeFilter(new Loop(hopTime));
    word.y.addBehavior(b);
    b= new HopSecondary(0, hopTime, hopCurve,hopCurve, 0.2);
    b.addTimeFilter(new Loop(hopTime));
    word.yScale.addBehavior(b);
    b = new HopSecondary(0, hopTime, hopCurve,hopCurve, -0.1);
    b.addTimeFilter(new Loop(hopTime));
    word.xScale.addBehavior(b);

    
    b= new Curve(0, 3000,1,0,-600);
    word.x.addBehavior(b);

    temp_seq = new Sequence("",5000,1000);
    Cheer.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Yay!");
    word.setPosition(320,240);
    word.setOrientation(KineticObject.MIDDLE_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 30);
    word.setColor(0,0,0,1.0);

    b= new Jitter(0, 1000, 5,0);
    word.x.addBehavior(b);
    b= new Jitter(0, 1000, 5,0);
    word.y.addBehavior(b);
    b= new Jitter(0, 1000, 30,0);
    word.rotation.addBehavior(b);

    temp_seq = new Sequence("",6000,1000);
    Cheer.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Yay!");
    word.setPosition(320,240);
    word.setOrientation(KineticObject.MIDDLE_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 80);
    word.setColor(0,0,0,1.0);

    b= new Jitter(0, 1000, 20,0);
    word.x.addBehavior(b);
    b= new Jitter(0, 1000, 20,0);
    word.y.addBehavior(b);
    b= new Jitter(0, 1000, 30,0);
    word.rotation.addBehavior(b);

    temp_seq = new Sequence("",7000,2000);
    Cheer.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Yay!");
    word.setPosition(320,240);
    word.setOrientation(KineticObject.MIDDLE_CENTER);
    word.setFont("Arial Black",Font.PLAIN, 200);
    word.setColor(0,0,0,1.0);

    b= new Jitter(0, 2000, 100,0);
    word.x.addBehavior(b);
    b= new Jitter(0, 2000, 100,0);
    word.y.addBehavior(b);
    b= new Jitter(0, 2000, 30,0);
    word.rotation.addBehavior(b);
    //End Cheer Sequence **********************************************************

    //Start Tree Sequence **********************************************************
    Sequence Tree = new Sequence("",Tree_start,Tree_length);
    seq.addCastMember(Tree);
    temp_seq = new Sequence("",0,4000);
    Tree.addCastMember(temp_seq);
    word = new KineticString();
    temp_seq.addCastMember(word);
    word.setString("Club-------------");
    word.setPosition(400,400);
    word.setOrientation(KineticObject.BOTTOM_RIGHT);
    word.setFont("Arial Black",Font.PLAIN, 30);
    word.setColor(0,0,0,1.0);
    word.setRotation(90);

    b = new Curve(1000,1000,0,3, -90);
    word.rotation.addBehavior(b);
    b= new Jitter(2000, 4000, 15,0);
    ((Jitter)b).amount.addBehavior(new Curve(0,500,1,0,-15));
    word.x.addBehavior(b);
    b= new Jitter(2000, 4000, 15,0);
    ((Jitter)b).amount.addBehavior(new Curve(0,500,1,0,-15));
    word.y.addBehavior(b);
    //End Tree Sequence **********************************************************
    }
}
