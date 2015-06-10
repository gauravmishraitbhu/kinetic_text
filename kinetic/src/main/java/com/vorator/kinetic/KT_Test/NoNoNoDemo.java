package com.vorator.kinetic.KT_Test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

import com.vorator.kinetic.engine.*;
import com.vorator.kinetic.engine.util.*;


public class NoNoNoDemo implements SequenceBuilder{

    static public Color color1 = new Color(255,40,40,255);
    static public Color color2 = new Color(80,80,255,255);
    
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {
		Sequence sub = new Sequence("",0,3500);
		seq.addCastMember(sub);
		partA(sub);

		sub = new Sequence("",3000,4000);
		seq.addCastMember(sub);
		partB(sub);
		
		sub = new Sequence("",5300,5000);
		seq.addCastMember(sub);
		partC(sub);
                
                seq.setDuration(10000);
	}
	
	static public void partA(Sequence seq){
		KineticString k_string;
		Sequence sub;
		double orig_size = 100;

		double size = 100;
		double size_inc = 15;
		int i =0;
		double dur = 150;
		double start = 0;
		double rot = 15;
		
		String text = "Charlie, I want to thank you for helping me out today at the butcher shop.";
		ActionSet.RSVP(seq, text,0, 50,400, color1);
                seq.setPosition(-30,0);
                seq.x.addBehavior(new Curve(50,800,1,0,30));
	}


	static public void partB(Sequence seq){
		KineticString k_string;
		Sequence sub;
		String font_name = "Arial Black";
		double start_size = 30;

		sub = new Sequence("",0, 3000);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("O");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(400,450);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color2);
                k_string.alpha.setValue(.1);
                k_string.alpha.addBehavior( new Change(500,500,.89));
		k_string.x.addBehavior( new Change(500,500,-280));
		k_string.y.addBehavior( new Curve(500,300,.7,0,-120));
		k_string.y.addBehavior( new Curve(800,200,0,.7,50));

		k_string.x.addBehavior( new Curve(1000,800,.2,0,400));
		k_string.y.addBehavior( new Curve(1000,800,1,0,-160));
		k_string.font_size.addBehavior( new Hop(1000,800,0,0,50));
		k_string.alpha.addBehavior( new Change(1800,200,-.99));
		
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("h");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(540,220);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color2);
                k_string.alpha.setValue(0);
		k_string.alpha.addBehavior( new Change(1500,300,.99));
		k_string.alpha.addBehavior( new Change(1800,200,-.99));
		
		double start_time = 1700;
		double left = 640;
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("no");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(left,220);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color2);
                k_string.alpha.setValue(0);
		k_string.alpha.addBehavior( new Change(start_time,50,.99));
		k_string.x.addBehavior( new Change(start_time,600,-150));
		k_string.alpha.addBehavior( new Change(start_time+400,100,-.99));

		start_time += 250;
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("no");
                k_string.setColor(color2);
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(left,220);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color2);
                k_string.alpha.setValue(0);
		k_string.alpha.addBehavior( new Change(start_time,50,.99));
		k_string.x.addBehavior( new Change(start_time,600,-150));
		k_string.alpha.addBehavior( new Change(start_time+400,100,-.99));

		start_time += 250;

		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("no");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(left,220);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color2);
                k_string.alpha.setValue(0);
                k_string.alpha.addBehavior( new Change(start_time,50,.99));
		k_string.x.addBehavior( new Change(start_time,600,-150));
		k_string.alpha.addBehavior( new Change(start_time+400,100,-.99));

	}
	
	static public void partC(Sequence seq){
		KineticString k_string;
		Sequence sub;
		String font_name = "Arial Narrow";
		double start_size = 35;

		sub = new Sequence("",0, 6000);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("NO");
                k_string.setColor(color1);
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(540,220);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color1);
                k_string.alpha.setValue(0);
		k_string.yScale.addBehavior( new Hop(0,1000,.7,.7,1));
		k_string.alpha.addBehavior( new Hop(0,1000,.7,.7,.99));
		
		double left = -100;
		double y_pos = 220;
		double y_shift = 30;
		double start_time = 400;
		double delay = 200;
		double dur = 1300;
		double x_shift = 550;
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("You");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color1);
                k_string.alpha.setValue(.9);
		k_string.x.addBehavior( new Curve(start_time,dur,1,0,x_shift));
		k_string.alpha.addBehavior( new Change(start_time + dur + 500,500,-.99));
		y_pos += y_shift;
		start_time += delay;

		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("were");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
  		k_string.setPosition(left,y_pos);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color1);
                k_string.alpha.setValue(.9);
		k_string.x.addBehavior( new Curve(start_time,dur,1,0,x_shift));
		k_string.alpha.addBehavior( new Change(start_time + dur + 500,500,-.99));
		y_pos += y_shift;
		start_time += delay;

		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("really");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color1);
                k_string.alpha.setValue(.9);
		k_string.x.addBehavior( new Curve(start_time,dur,1,0,x_shift));
		k_string.alpha.addBehavior( new Change(start_time + dur + 500,500,-.99));
		y_pos += y_shift;
		start_time += delay;

		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("nice.");
                k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                k_string.setPosition(left,y_pos);
		k_string.setFont(font_name, Font.PLAIN,start_size);
                k_string.setColor(color1);
                k_string.alpha.setValue(.9);
                k_string.x.addBehavior( new Curve(start_time,dur,1,0,x_shift));
		k_string.alpha.addBehavior( new Change(start_time + dur + 500,500,-.99));
                k_string.xScale.addBehavior(new Hop(start_time + dur-500,500,.7,.7,.2));
                k_string.yScale.addBehavior(new Hop(start_time + dur-500,500,.7,.7,.2));
		y_pos += y_shift;
		start_time += delay;
	}
}
