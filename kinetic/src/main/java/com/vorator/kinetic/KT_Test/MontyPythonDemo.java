package com.vorator.kinetic.KT_Test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

import com.vorator.kinetic.engine.*;
import com.vorator.kinetic.engine.util.*;


public class MontyPythonDemo implements SequenceBuilder{

        public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {
		double line1_start = 0;
		double line1_dur = 1700; //what do  you want?
		double line2_start = line1_start + line1_dur + 1000;
		double line2_dur = 1500; //well i was told...
		double line3_start = line2_start + line2_dur + 0;
		double line3_dur = 2700; //don't give me that...
		double line4_start = line3_start + line3_dur + 2500;
		double line4_dur = 500; //what
		double line5_start = line4_start + line4_dur + 300;
		double line5_dur = 11000; //shut your festering
		double line6_start = line5_start + line5_dur + 500;
		double line6_dur = 2700; //look, I came here for an argument
		double line7_start = line6_start + line6_dur + 600;
		double line7_dur = 4700; //oh, oh....
		double line8_start = line7_start + line7_dur + 3500;
		double line8_dur = 3200; //oh i see
		double line9_start = line8_start + line8_dur + 600;
		double line9_dur = 4000;  //ah yes, you want
		double line10_start = line9_start + line9_dur + 1800;
		double line10_dur = 2800; //oh thank you very muc
		double line11_start = line10_start + line10_dur + 800;
		double line11_dur = 1200; //not at all
		double line12_start = line11_start + line11_dur + 1200;
		double line12_dur = 1000; // thank you
		double line13_start = line12_start + line12_dur + 1800;
		double line13_dur = 2000; //stupid git.

                seq.setDuration(line13_start + line13_dur + 1000);
		String clerk_font = "Times New Roman";
		String customer_font = "Arial Narrow";

		if(false)
			{
			line1_start = 0;
			line1_dur = 0;
			line2_start = 0;
			line2_dur = 0;
			line3_start = 0;
			line3_dur = 0;
			line4_start = 0;
			line4_dur = 0;
			line5_start = 0;
			line5_dur = 0;
			line6_start = 0;
			line6_dur = 0;
			line7_start = 0;
			line7_dur = 0;
			line8_start = 0;
			line8_dur = 00;
			line9_start = 0;
			line9_dur = 10000;
			line10_start = 0;
			line10_dur = 0;
			line11_start = 0;
			line11_dur = 0;
			line12_start = 0;
			line12_dur = 0;
			line13_start = 0;
			line13_dur = 0;
			}

		//clerk - line 1 ***************************************************************
		Sequence new_seq = new Sequence("new",line1_start,line1_dur);
		seq.addCastMember(new_seq);
		new_seq.addTimeFilter(new Speed(0.9));
		new_seq.setPosition(300,200);
		KineticString k_string;
		double jitter_amount = 20;
		double in_time = 200;
		Sequence sub;		
		//what
		sub = new Sequence("what",0,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("What");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(-130,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(-5);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,100));
		k_string.font_size.addBehavior(new Change(in_time,100,-30));
		k_string.alpha.addBehavior(new Change(in_time,100,-0.8));
		k_string.x.addBehavior(new Jitter(0,2000,5,0));
		k_string.y.addBehavior(new Jitter(0,2000,5,0));
		k_string.rotation.addBehavior(new Jitter(0,in_time,10,0));

		//do
		sub = new Sequence("do",200,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("do");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(0,-10);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(2);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,100));
		k_string.font_size.addBehavior(new Change(in_time,100,-30));
		k_string.alpha.addBehavior(new Change(in_time,100,-0.8));
		k_string.x.addBehavior(new Jitter(0,2000,5,0));
		k_string.y.addBehavior(new Jitter(0,2000,5,0));
		k_string.rotation.addBehavior(new Jitter(0,in_time,10,0));

		//you
		sub = new Sequence("you",500,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("you");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(100,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(10);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,100));
		k_string.font_size.addBehavior(new Change(in_time,100,-30));
		k_string.alpha.addBehavior(new Change(in_time,100,-0.8));
		k_string.x.addBehavior(new Jitter(0,2000,5,0));
		k_string.y.addBehavior(new Jitter(0,2000,5,0));
		k_string.rotation.addBehavior(new Jitter(0,in_time,10,0));

		//want
		sub = new Sequence("want",700,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("want");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(-50,20);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(5);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,100));
		k_string.font_size.addBehavior(new Change(in_time,100,-30));
		k_string.alpha.addBehavior(new Change(in_time,100,-0.3));
		k_string.x.addBehavior(new Jitter(0,2000,5,0));
		k_string.y.addBehavior(new Jitter(0,2000,5,0));
		k_string.rotation.addBehavior(new Jitter(0,in_time,10,0));

		//?
		sub = new Sequence("?",900,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("?");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(50,20);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(-5);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,250));
		k_string.x.addBehavior(new Jitter(0,3000,15,0));
		k_string.y.addBehavior(new Jitter(0,3000,15,0));
		k_string.rotation.addBehavior(new Jitter(0,3000,10,0));

		//customer - line 2 ***************************************************************
		new_seq = new Sequence("line2",line2_start,line2_dur);
		new_seq.addTimeFilter(new Speed(0.8));

		seq.addCastMember(new_seq);
		new_seq.setPosition(520,300);
		in_time = 100;

		//well
		sub = new Sequence("well",0,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Well");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(0,-30);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Change(0,in_time,10));
		k_string.font_size.addBehavior(new Change(in_time,300,-20));
		k_string.alpha.addBehavior(new Curve(in_time,300,.3,0,-1));
		k_string.x.addBehavior(new Change(0,500,-30));

		//I
		sub = new Sequence("I",200,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("I");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(0,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Change(0,in_time,10));
		k_string.font_size.addBehavior(new Change(in_time,300,-10));
		k_string.alpha.addBehavior(new Curve(in_time,300,.3,0,-.6));
		k_string.x.addBehavior(new Change(0,1500,-400));
		k_string.alpha.addBehavior(new Change(800,200,-.4));

		//was
		sub = new Sequence("was",400,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("was");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(0,15);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Change(0,in_time,10));
		k_string.font_size.addBehavior(new Change(in_time,300,-10));
		k_string.alpha.addBehavior(new Curve(in_time,300,.3,0,-.6));
		k_string.x.addBehavior(new Change(0,1500,-300));
		k_string.alpha.addBehavior(new Change(800,200,-.4));
		
		//told
		sub = new Sequence("told",600,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("told");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(0,-20);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Change(0,in_time,10));
		k_string.font_size.addBehavior(new Change(in_time,300,-10));
		k_string.alpha.addBehavior(new Curve(in_time,300,.3,0,-.6));
		k_string.x.addBehavior(new Change(0,1500,-200));
		
		//outside
		sub = new Sequence("outside",800,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("outside");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(0,-5);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Change(0,in_time,10));
		k_string.font_size.addBehavior(new Change(in_time,300,-10));
		k_string.alpha.addBehavior(new Curve(in_time,300,.3,0,-.6));
		k_string.x.addBehavior(new Change(0,1500,-100));

		//that
		sub = new Sequence("that",1050,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("that");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(0,10);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Change(0,in_time,10));
		k_string.font_size.addBehavior(new Change(in_time,300,-10));
//		k_string.alpha.addBehavior(new Curve(in_time,300,.3,0,-.6));
		k_string.x.addBehavior(new Curve(0,1000,1,0,100));
		k_string.y.addBehavior(new Curve(0,1000,1,0,50));


		//clerk - line 3 ***************************************************************
		new_seq = new Sequence("line3",line3_start,line3_dur);
		seq.addCastMember(new_seq);
		new_seq.addTimeFilter(new Speed(0.6));
		new_seq.setPosition(300,220);
		jitter_amount = 20;
		in_time = 200;
		double grow_size = 130;
		double shrink = -40;
		
		//Don't
		sub = new Sequence("Don't",0,800);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Don't");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(0,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,300));
		k_string.font_size.addBehavior(new Change(in_time+100,100,-200));
		k_string.red.addBehavior(new Change(in_time+100,100,0.9));
		k_string.green.addBehavior(new Change(in_time+100,100,0.9));
		k_string.blue.addBehavior(new Change(in_time+100,100,0.9));
		k_string.x.addBehavior(new Jitter(0,4000,20,0));
		k_string.y.addBehavior(new Jitter(0,4000,20,0));
		k_string.rotation.addBehavior(new Jitter(0,400,10,0));

		ActionSet.yell(new_seq, "give", 300, -50,50, clerk_font);
		ActionSet.yell(new_seq, "me", 500, 0,10, clerk_font);
		ActionSet.yell(new_seq, "that", 700, -80,60, clerk_font);

		//black matte
		sub = new Sequence("matte",900,50);
		new_seq.addCastMember(sub);
		KineticRectangle matte  = new KineticRectangle();
		sub.addCastMember(matte);
                matte.setPosition(0,0);
                matte.setSize(1000,1000);
		matte.setColor(0,0,0,1);

		ActionSet.yell(new_seq, "you", 900, 50,-10, clerk_font);
		ActionSet.yell(new_seq, "snotty", 1100, 0,0, clerk_font);
		ActionSet.yell(new_seq, "faced", 1300, 50,50, clerk_font);
		ActionSet.yell(new_seq, "heap", 1500, 0,-50, clerk_font);
		ActionSet.yell(new_seq, "of", 1700, -100,-20, clerk_font);
		ActionSet.yell(new_seq, "parrot", 1800, -100,-100, clerk_font);

		//black matte
		sub = new Sequence("matte",2000,50);
		new_seq.addCastMember(sub);
		matte  = new KineticRectangle();
		sub.addCastMember(matte);
                matte.setPosition(0,0);
                matte.setSize(1000,1000);
		matte.setColor(0,0,0,1);

		ActionSet.yell(new_seq, "droppings", 2100, -20,0, clerk_font);

		//!
		sub = new Sequence("!",2200,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("!");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(80,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,300));
		k_string.x.addBehavior(new Jitter(0,4000,30,0));
		k_string.y.addBehavior(new Jitter(0,4000,30,0));
		k_string.rotation.addBehavior(new Jitter(0,4000,10,0));


		//customer - line 4 ***************************************************************
		new_seq = new Sequence("line4",line4_start,line4_dur);
		new_seq.addTimeFilter(new Speed(.6));

		seq.addCastMember(new_seq);
		new_seq.setPosition(500,300);
		in_time = 100;

		//what
		sub = new Sequence("What?",0,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("What?");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(0,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.alpha.addBehavior(new Change(200,300,-.9));
		k_string.x.addBehavior(new Change(0,500,-70));


		//clerk - line 5 ***************************************************************
		new_seq = new Sequence("line5",line5_start,line5_dur);
		seq.addCastMember(new_seq);
		new_seq.addTimeFilter(new Speed(0.6));
		new_seq.setPosition(320,240);
		
		//Shut
		sub = new Sequence("Shut",0,800);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Shut");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(0,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,300));
		k_string.font_size.addBehavior(new Change(in_time+100,100,-200));
		k_string.red.addBehavior(new Change(in_time+100,100,0.9));
		k_string.green.addBehavior(new Change(in_time+100,100,0.9));
		k_string.blue.addBehavior(new Change(in_time+100,100,0.9));
		k_string.x.addBehavior(new Jitter(0,4000,20,0));
		k_string.y.addBehavior(new Jitter(0,4000,20,0));
		k_string.rotation.addBehavior(new Jitter(0,400,10,0));

		ActionSet.yell(new_seq, "your", 300, -50,-50, clerk_font);
		ActionSet.yell(new_seq, "festering", 500, -50,50, clerk_font);
		ActionSet.yell(new_seq, "gob", 700, 0,0, clerk_font);
		ActionSet.yell(new_seq, "you", 900, -20,-50,600, clerk_font);


		//TIT
		sub = new Sequence("TIT",1200,600);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("TIT");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(50,50);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,300));
		k_string.font_size.addBehavior(new Change(in_time+100,100,-100));
		//k_string.red.addBehavior(new Change(in_time+100,100,0.9));
		//k_string.green.addBehavior(new Change(in_time+100,100,0.9));
		//k_string.blue.addBehavior(new Change(in_time+100,100,0.9));
		k_string.x.addBehavior(new Jitter(0,4000,10,0));
		k_string.y.addBehavior(new Jitter(0,4000,10,0));
		k_string.rotation.addBehavior(new Jitter(0,400,10,0));

		//black matte
		sub = new Sequence("matte",1300,50);
		new_seq.addCastMember(sub);
		matte  = new KineticRectangle();
		sub.addCastMember(matte);
                matte.setPosition(0,0);
                matte.setSize(1000,1000);
		matte.setColor(0,0,0,1);

		ActionSet.yell(new_seq, "Your", 1900, -100,0, clerk_font);
		ActionSet.yell(new_seq, "type", 2100, 100,30, clerk_font);
		ActionSet.yell(new_seq, "really", 2300, -50,-50, clerk_font);
		ActionSet.yell(new_seq, "makes", 2500, 80,0,700, clerk_font);
		ActionSet.yell(new_seq, "me", 2700, -50,50,500, clerk_font);

		//puke
		sub = new Sequence("puke",2900,600);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("puke");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(0,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,300));
		k_string.font_size.addBehavior(new Change(in_time+100,100,-100));
//		k_string.red.addBehavior(new Change(in_time+100,100,0.9));
//		k_string.green.addBehavior(new Change(in_time+100,100,0.9));
//		k_string.blue.addBehavior(new Change(in_time+100,100,0.9));
		k_string.x.addBehavior(new Jitter(0,4000,10,0));
		k_string.y.addBehavior(new Jitter(0,4000,10,0));
		k_string.rotation.addBehavior(new Jitter(0,400,10,0));

		//black matte
		sub = new Sequence("matte",3000,50);
		new_seq.addCastMember(sub);
		matte  = new KineticRectangle();
		sub.addCastMember(matte);
                matte.setPosition(0,0);
                matte.setSize(1000,1000);
		matte.setColor(0,0,0,1);


		ActionSet.yell(new_seq, "You", 3600, -30,-10, clerk_font);
		ActionSet.yell(new_seq, "vacuous", 3800, -10,-20, clerk_font);
		
		//black matte
		sub = new Sequence("matte",4000,50);
		new_seq.addCastMember(sub);
		matte  = new KineticRectangle();
		sub.addCastMember(matte);
                matte.setPosition(0,0);
                matte.setSize(1000,1000);
		matte.setColor(0,0,0,1);

		
		ActionSet.yell(new_seq, "puffy", 4500, -50,-50, clerk_font);

		//black matte
		sub = new Sequence("matte",4700,50);
		new_seq.addCastMember(sub);
		matte  = new KineticRectangle();
		sub.addCastMember(matte);
                matte.setPosition(-100,0);
                matte.setSize(1000,1000);
		matte.setColor(0,0,0,1);

		ActionSet.yell(new_seq, "nosed", 4700, 20,50, clerk_font);

		//black matte
		sub = new Sequence("matte",4900,50);
		new_seq.addCastMember(sub);
		matte  = new KineticRectangle();
		sub.addCastMember(matte);
                matte.setPosition(-100,0);
                matte.setSize(1000,1000);
		matte.setColor(0,0,0,1);


		ActionSet.yell(new_seq, "malodorous", 5200, 80,0, clerk_font);
		
		ActionSet.yell(new_seq, "PERVERT", 5700, 0,50, clerk_font);

		//black matte
		sub = new Sequence("matte",5900,50);
		new_seq.addCastMember(sub);
		matte  = new KineticRectangle();
		sub.addCastMember(matte);
                matte.setPosition(-100,0);
                matte.setSize(1000,1000);
		matte.setColor(0,0,0,1);

		//customer - line 6 ***************************************************************
		new_seq = new Sequence("line6",line6_start,line6_dur);
		new_seq.addTimeFilter(new Speed(0.8));

		seq.addCastMember(new_seq);
		new_seq.setPosition(550,300);
		in_time = 100;

		//Look
		sub = new Sequence("Look",00,3000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Look");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(-40,50);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,1);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,.9));
		k_string.font_size.addBehavior(new Change(0,in_time,60));
		k_string.font_size.addBehavior(new Change(in_time,300,-60));
		k_string.alpha.addBehavior(new Change(in_time,200,-.9));

		//I
		sub = new Sequence("I",300,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("I");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(10,0);
		k_string.setColor(0,0,0,1);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.font_size.addBehavior(new Change(0,50,15));
		k_string.font_size.addBehavior(new Change(50,300,-15));
		k_string.alpha.addBehavior(new Change(100,300,-.9));
		k_string.x.addBehavior(new Change(0,300,-50));

		//came
		sub = new Sequence("came",500,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("came");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(-50,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,.9));
		k_string.font_size.addBehavior(new Change(0,50,2));
		k_string.font_size.addBehavior(new Change(50,300,-2));
		k_string.alpha.addBehavior(new Change(100,300,-.9));
		k_string.x.addBehavior(new Change(0,300,-50));

		//here
		sub = new Sequence("here",700,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("here");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(-150,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,.9));
		k_string.font_size.addBehavior(new Change(0,50,2));
		k_string.font_size.addBehavior(new Change(50,300,-2));
		k_string.alpha.addBehavior(new Change(100,300,-.9));
		k_string.x.addBehavior(new Change(0,300,-50));

		//for
		sub = new Sequence("for",900,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("for");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(-230,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,.9));
		k_string.font_size.addBehavior(new Change(0,50,2));
		k_string.font_size.addBehavior(new Change(50,300,-2));
		k_string.alpha.addBehavior(new Change(100,300,-.9));
		k_string.x.addBehavior(new Change(0,300,-50));

		//an
		sub = new Sequence("an",1100,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("an");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(-300,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,.9));
		k_string.font_size.addBehavior(new Change(0,50,2));
		k_string.font_size.addBehavior(new Change(50,300,-2));
		k_string.alpha.addBehavior(new Change(100,300,-.9));
		k_string.x.addBehavior(new Change(0,300,-50));

		//argument
		sub = new Sequence("for",1300,800);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("argument");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(-350,-10);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,.9));
		k_string.font_size.addBehavior(new Change(0,50,15));
		k_string.font_size.addBehavior(new Change(50,300,-30));
		k_string.alpha.addBehavior(new Change(200,300,-.5));
		k_string.x.addBehavior(new Curve(0,400,1,0,-100));
		k_string.x.addBehavior(new Curve(400,400,0,1,100));
//		k_string.alpha.addBehavior(new Change(200,300,-.5));

		double space = 10;
		double left = -450;

		//I'm
		sub = new Sequence("I'm",1900,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("I'm");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.x.addBehavior(new Change(0,1000,100));
	   	
	   	k_string.initBounds(g2);
             	left += k_string.getWidth()*1.3;
		
		sub = new Sequence("not",2000,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("not");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left +space,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.x.addBehavior(new Change(0,1000,100));

	   	k_string.initBounds(g2);
             	left += k_string.getWidth()*1.3 + 10;

		sub = new Sequence("going",2100,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("going");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left +space,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.x.addBehavior(new Change(0,1000,100));

	   	k_string.initBounds(g2);
         	left += k_string.getWidth()*1.3;

		sub = new Sequence("to",2200,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("to");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left+space,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.x.addBehavior(new Change(0,1000,100));

	   	k_string.initBounds(g2);
                left += k_string.getWidth()*1.3 + 10;

		sub = new Sequence("just",2300,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("just");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left+space,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.x.addBehavior(new Change(0,1000,100));


	   	k_string.initBounds(g2);
                left += k_string.getWidth()*1.3;
     	
		sub = new Sequence("stand",2400,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("stand");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left +space,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.x.addBehavior(new Change(0,1000,100));

	   	k_string.initBounds(g2);
                left += k_string.getWidth()*1.3;

		sub = new Sequence("here",2500,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("here");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left +space,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.x.addBehavior(new Change(0,1000,100));

	   	k_string.initBounds(g2);
                left += k_string.getWidth()*1.3;
     	
		sub = new Sequence("and",2600,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("and");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left +space,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.x.addBehavior(new Change(0,1000,100));


		//clerk - line 7 ***************************************************************
		new_seq = new Sequence("line7",line7_start,line7_dur);
		seq.addCastMember(new_seq);
		new_seq.addTimeFilter(new Speed(0.6));
		new_seq.setPosition(320,240);
		
		
		KineticRectangle kSquare = new KineticRectangle();
		new_seq.addCastMember(kSquare);
		kSquare.setPosition(320,240);
		kSquare.setSize(1000,1000);
		kSquare.setColor(1,1,1,0);
		kSquare.alpha.addBehavior(new Change(line7_dur-200,200,.99));
		
		//Oh
		sub = new Sequence("Oh",0,150);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Oh");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(-50,-20);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,150,1,0,200));
		k_string.x.addBehavior(new Jitter(0,4000,20,0));
		k_string.y.addBehavior(new Jitter(0,4000,20,0));
		k_string.rotation.addBehavior(new Jitter(0,400,10,0));

		//Oh
		sub = new Sequence("Oh",1000,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Oh");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(-50,-20);
		k_string.setColor(0,0,0,.99);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(5);
		k_string.font_size.addBehavior(new Curve(0,100,1,0,150));
		k_string.font_size.addBehavior(new Change(100,50,-60));
		k_string.alpha.addBehavior(new Change(600,400,-.99));

		k_string.rotation.addBehavior(new Change(100,50,-5));


		//I'm
		sub = new Sequence("I'm",2000,150);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("I'm");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(-100,50);
		k_string.setColor(0,0,0,1);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(0);
		k_string.font_size.addBehavior(new Curve(0,100,1,0,40));
		k_string.font_size.addBehavior(new Change(100,50,-5));

		//sorry
		sub = new Sequence("sorry",2150,800);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("sorry");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(-40,50);
		k_string.setColor(0,0,0,1);
		k_string.setFont(clerk_font, Font.BOLD,1);
		k_string.setRotation(0);
		k_string.font_size.addBehavior(new Curve(0,100,1,0,40));
		k_string.font_size.addBehavior(new Change(100,50,-5));
		k_string.alpha.addBehavior(new Change(200,400,-1));

		left = -150;
		//but
		sub = new Sequence("but",3200,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("but");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,-20);
		k_string.setColor(0,0,0,1);
		k_string.setFont(clerk_font, Font.BOLD,35);

	   	k_string.initBounds(g2);
         	left += k_string.getAdvance(g2,true);

		//this
		sub = new Sequence("this",3350,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("this");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,-20);
		k_string.setColor(0,0,0,1);
		k_string.setFont(clerk_font, Font.BOLD,35);

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);

		//is
		sub = new Sequence("is",3500,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("is");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,-20);
		k_string.setColor(0,0,0,1);
		k_string.setFont(clerk_font, Font.BOLD,35);

	   	k_string.initBounds(g2);
             	left += k_string.getAdvance(g2,true);

		//abuse
		sub = new Sequence("abuse",3650,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("\"Abuse\"");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,-20);
		k_string.setColor(0,0,0,1);
		k_string.setFont(clerk_font, Font.BOLD,35);
		k_string.y.addBehavior(new Hop(0,200,.7,.7,-10));

		//customer - line 8 ***************************************************************
		new_seq = new Sequence("line6",line8_start,line8_dur);
		new_seq.addTimeFilter(new Speed(1));

		seq.addCastMember(new_seq);
		new_seq.setPosition(450,300);
		in_time = 100;


		//oh
		sub = new Sequence("Oh",0,800);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Oh");
                k_string.setOrientation(KineticObject.MIDDLE_CENTER);
                k_string.setPosition(-40,80);
		k_string.setColor(0,0,0,0.9);
		k_string.setFont(customer_font, Font.PLAIN,1);
		k_string.setRotation(0);
		k_string.font_size.addBehavior(new Curve(0,500,1,0,60));
		k_string.alpha.addBehavior(new Change(400,300,-.9));

		//I
		sub = new Sequence("I",600,400);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("I");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(0,100);
		k_string.setColor(0,0,0,0.9);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(200,200,-.9));

		//see
		sub = new Sequence("see",800,800);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("see");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(10,100);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.alpha.addBehavior(new Change(200,600,-.9));
		k_string.x.addBehavior(new Curve(000,800,.3,0,50));

	   	
	   	left = -200;
	   	double y_pos = 50;
	   	
		sub = new Sequence("Well",1700,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Well");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.alpha.addBehavior(new Change(600,200,-.9));

	   	//k_string.initBounds(g2);
             	left += k_string.getAdvance(g2,true);

		sub = new Sequence("that",1900,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("that");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
                k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.alpha.addBehavior(new Change(600,200,-.9));
//		k_string.x.addBehavior(new Change(0,3000,100));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);

		sub = new Sequence("explains",2100,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("explains");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.alpha.addBehavior(new Change(600,200,-.9));
//		k_string.x.addBehavior(new Change(0,3000,100));

	   	k_string.initBounds(g2);
             	left += k_string.getAdvance(g2,true);

		sub = new Sequence("it",2300,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("it");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.alpha.addBehavior(new Change(600,200,-.9));
//		k_string.x.addBehavior(new Change(0,3000,100));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,false);

		sub = new Sequence(".",2400,2000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString(".");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,.9));
		k_string.alpha.addBehavior(new Change(600,200,-.9));
//		k_string.x.addBehavior(new Change(0,4000,100));



		//clerk - line 9 ***************************************************************
		new_seq = new Sequence("line9",line9_start,line9_dur);
		seq.addCastMember(new_seq);
		new_seq.addTimeFilter(new Speed(.7));
		new_seq.setPosition(100,140);
		
		//Ahh
		sub = new Sequence("Ahh",1,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Ahh");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(-20,0);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.y.addBehavior(new Hop(0,1000,.7,.7,-20));
		k_string.alpha.addBehavior(new Change(700,300,-0.9));
                left = k_string.getAdvance(g2,true)-20;
		//yes
		sub = new Sequence("yes",0,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("yes");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.y.addBehavior(new Hop(0,1000,.7,.7,-20));
		k_string.alpha.addBehavior(new Change(300,200,0.9));
		k_string.alpha.addBehavior(new Change(700,300,-0.9));


		left = 40;
		//You
		sub = new Sequence("You",1000,300);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("You");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);
     	
		//want
		sub = new Sequence("want",1200,200);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("want");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);


		//room
		sub = new Sequence("room",1400,300);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("room");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);

		//12
		sub = new Sequence("12",1700,700);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("12");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(500,200,-0.9));
                left += k_string.getAdvance(g2,false);
		//A
		sub = new Sequence("A",2000,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("A");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(200,200,-0.9));
		
		left = 50;
		y_pos = 50;
		//just
		sub = new Sequence("just",2500,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("just");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(800,200,-0.9));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);
     	
		//along
		sub = new Sequence("along",2700,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("along");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(600,200,-0.9));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);
     	
		//the
		sub = new Sequence("the",2900,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("the");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(400,200,-0.9));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);
     	
		//corridor
		sub = new Sequence("corridor",3100,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("corridor");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(200,200,-0.9));

		//customer - line 10 ***************************************************************
		new_seq = new Sequence("line10",line10_start,line10_dur);
		new_seq.addTimeFilter(new Speed(1));

		seq.addCastMember(new_seq);
		new_seq.setPosition(300,240);

	   	left = 65;

                //oh
		sub = new Sequence("Oh",1,800);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Oh");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,0.9);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);

		k_string.x.addBehavior(new Hop(0,600,.7,.7,-80));
		k_string.alpha.addBehavior(new Change(400,200,-.9));
	   	
	   	y_pos = 0;
		in_time = 100;
		double out_time = 300;
	   	
		sub = new Sequence("Thank",500,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Thank");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,in_time,.9));
		k_string.alpha.addBehavior(new Change(in_time,out_time,-.9));

	   	k_string.initBounds(g2);
         	left += k_string.getAdvance(g2,true);

		sub = new Sequence("you",700,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("you");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,in_time,.9));
		k_string.alpha.addBehavior(new Change(in_time,out_time,-.9));

	   	k_string.initBounds(g2);
         	left += k_string.getAdvance(g2,true);

		sub = new Sequence("very",900,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("very");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,in_time,.9));
		k_string.alpha.addBehavior(new Change(in_time,out_time,-.9));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);

		sub = new Sequence("much",1100,500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("much");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,in_time,.9));
		k_string.alpha.addBehavior(new Change(in_time,out_time,-.9));
		
		//Sorry
		sub = new Sequence("Sorry",2000,800);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Sorry");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(350,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);
		k_string.x.addBehavior(new Hop(0,800,.7,.7,-200));
		k_string.alpha.addBehavior(new Change(200,10,.9));

		//clerk - line 11 ***************************************************************
		new_seq = new Sequence("line11",line11_start,line11_dur);
		seq.addCastMember(new_seq);
		new_seq.addTimeFilter(new Speed(.8));
		new_seq.setPosition(200,140);

		new_seq.x.addBehavior(new Change(0,1000,-100));
		
		left = 10;
		y_pos = 0;
		//not
		sub = new Sequence("Not",0,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Not");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,0.9));
		k_string.alpha.addBehavior(new Change(600,400,-0.9));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);
     	
		//at
		sub = new Sequence("at",200,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("at");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,0.9));
		k_string.alpha.addBehavior(new Change(400,400,-0.9));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);
     	
		//all
		sub = new Sequence("all",400,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("all");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,0);
		k_string.setFont(clerk_font, Font.BOLD,30);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(0,200,0.9));
		k_string.alpha.addBehavior(new Change(200,400,-0.9));
		
		//customer - line 12 ***************************************************************
		new_seq = new Sequence("line12",line12_start,line12_dur);
		new_seq.addTimeFilter(new Speed(1));

		seq.addCastMember(new_seq);
		new_seq.setPosition(650,240);

		left = 0;
		//Thank
		sub = new Sequence("Thank",1,1500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Thank");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);

		k_string.x.addBehavior(new Hop(0,1000,.7,.7,-250));
		k_string.alpha.addBehavior(new Change(100,100,.9));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);


		//you
		sub = new Sequence("You",1,1500);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("you");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,0);
		k_string.setColor(0,0,0,0);
		k_string.setFont(customer_font, Font.PLAIN,35);
		k_string.setRotation(0);

		k_string.x.addBehavior(new Hop(0,1000,.7,.7,-250));
		k_string.alpha.addBehavior(new Change(450,100,.9));

		//clerk - line 13 ***************************************************************
		new_seq = new Sequence("line13",line13_start,line13_dur);
		seq.addCastMember(new_seq);
		new_seq.addTimeFilter(new Speed(1));
		new_seq.setPosition(150,350);
		
		left = 0;
		y_pos = 0;
		
		//Stupid
		sub = new Sequence("Stupid",1,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("Stupid");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,25);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(500,300,-0.9));

	   	k_string.initBounds(g2);
                left += k_string.getAdvance(g2,true);
     	
		//git
		sub = new Sequence("git",300,1000);
		new_seq.addCastMember(sub);
		k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString("git.");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setColor(0,0,0,.9);
		k_string.setFont(clerk_font, Font.BOLD,25);
		k_string.setRotation(0);
		k_string.alpha.addBehavior(new Change(200,300,-0.9));
	}
}