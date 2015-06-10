/*
 * OhBoyDemo.java
 *
 * Created on October 21, 2002, 3:38 AM
 */

package com.vorator.kinetic.KT_Test;
import com.vorator.kinetic.engine.*;
import com.vorator.kinetic.engine.util.*;

import java.awt.*;
/**
 *
 * @author  Johnny Lee
 */
public class OhBoyDemo  implements SequenceBuilder{
    	static public String sunnyFont = "Comic Sans MS";
    	static public String rainyFont = "Default";

    /** Creates a new instance of OhBoyDemo */
    public OhBoyDemo() {
    }
    
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {
        seq.setDuration(25000);
        Sequence sunny = new Sequence("",10,10000);
        seq.addCastMember(sunny);
        buildSunny(sunny,g2);
        Sequence rainy = new Sequence("",11000,15000);
        rainy.setPosition(0,-32);
        seq.addCastMember(rainy);
        buildRainy(rainy,g2);
    }
    
    public void buildSunny(Sequence seq, Graphics2D graphics_handle) {
		Sequence sub = new Sequence("",0,3500);
		seq.addCastMember(sub);
		sunnyPartA(sub);

		sub = new Sequence("",4000,5000);
		seq.addCastMember(sub);
		sunnyPartB(sub);

	}
	
	static public void sunnyPartA(Sequence seq){
		KineticString k_string;
		Sequence sub;
		double orig_size = 100;

		double size = 100;
		double size_inc = 15;
		int i =0;
		double dur = 150;
		double start = 0;
		double rot = 15;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Oh");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;

		size += i*size_inc;
		i++;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Oh");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(-rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;
		
		
		size += i*size_inc;
		i++;


		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Oh");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;
				
		size += i*size_inc;
		i++;


		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Oh");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(-rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;


		size += i*size_inc;
		i++;


		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Oh");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;
		
		
		size += i*size_inc;
		i++;


		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Oh");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(-rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;

		//---------------------------------------------------

		size = orig_size;
		i = 0;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Oh");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(0);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;
		
		size += i*size_inc;
		i++;
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;

		size += i*size_inc;
		i++;
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(-rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;

		size += i*size_inc;
		i++;
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;

		size += i*size_inc;
		i++;
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(-rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;

		size += i*size_inc;
		i++;
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;

		size += i*size_inc;
		i++;
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(-rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Boy");
		k_string.setRotation(-rot);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,size);
		start += dur;
	}


	static public void sunnyPartB(Sequence seq){
		KineticString k_string;
		Sequence sub;
		double orig_size = 100;

		double size = 100;
		double size_inc = 15;
		int i =0;
		double dur = 150;
		double start = 0;
		double rot = 15;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("is");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("is");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("it");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("it");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		start += dur;

		sub = new Sequence("",start, 10*dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("Nice");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size);
		k_string.x.addBehavior(new Jitter(0,10*dur, 3,dur/5));
		k_string.y.addBehavior(new Jitter(0,10*dur, 3,dur/5));
		start += 10*dur + 2 * dur;

		//NICE ---------------------------------------------------------
		double inc = 150;
		sub = new Sequence("",start, dur/2);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("NICE");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size+inc);
		start += dur;

		sub = new Sequence("",start, dur/2);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("NICE");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size+inc);
		start += dur;

		sub = new Sequence("",start, dur/2);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("NICE");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size+inc);
		start += dur;

		//out----------------------------------------------
		sub = new Sequence("",start, dur/2);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("out");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size+inc);
		start += dur;

		sub = new Sequence("",start, dur/2);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("out");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size+inc);
		start += dur;

		sub = new Sequence("",start, dur/2);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("out");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size+inc);
		start += dur;

		sub = new Sequence("",start, dur/2);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("out");
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(320,240);
		k_string.setFont(sunnyFont, Font.PLAIN,orig_size+inc);
		start += dur;
	}

    	static public void buildRainy(Sequence seq, Graphics2D graphics_handle) {
		Sequence sub = new Sequence("",0,1500);
		seq.addCastMember(sub);
		rainyPartA(sub);

		sub = new Sequence("",1700,9000);
		seq.addCastMember(sub);
		rainyPartB(sub);

	}
	
	static public void rainyPartA(Sequence seq){
		KineticString k_string;
		Sequence sub;
		double orig_size = 80;

		double size = 150;
		double size_inc = 15;
		int i =0;
		double dur = 120;
		double start = 0;
		double hop_amount = 20;
		double jitter_amount = 3;
		double x_pos = 500;
		double y_pos = 440;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("OH");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("BOY");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("OH");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("BOY");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("OH");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("BOY");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("OH");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("BOY");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("OH");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("BOY");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;
		
		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("OH");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;

		sub = new Sequence("",start, dur);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("BOY");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.y.addBehavior( new Hop(0,dur, .7,.7,hop_amount));
		k_string.x.addBehavior( new Jitter(0,dur,jitter_amount,50));
		start += dur;
	}


	static public void rainyPartB(Sequence seq){
		KineticString k_string;
		Sequence sub;
		double orig_size = 100;
		double size_dec = -40;
		double size;
		double hop_amount = 20;
		double jitter_amount = 3;
		double x_pos = 520;
		double y_pos = 460;
		double y_dec;
		
		sub = new Sequence("",0, 3000);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("oh");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,orig_size);
		k_string.font_size.addBehavior( new Curve(1000,1000,0,1,size_dec));
		
		y_dec = 5;
		size = orig_size + size_dec;
		sub = new Sequence("",3000, 1500);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("boy");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,size);
		k_string.y.addBehavior( new Curve(0,1000,0,.3,y_dec));

		y_pos += y_dec;
		size -= 10;
		y_dec = 0;
		sub = new Sequence("",4500, 500);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("is");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,size);
		k_string.y.addBehavior( new Change(0,300,y_dec));

		y_pos += y_dec;
		size -= 10;
		y_dec = 5;
		sub = new Sequence("",5000, 1000);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("it");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,size);
		k_string.y.addBehavior( new Change(0,1000,y_dec));

		size -= 10;
		sub = new Sequence("",6000, 1500);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("nice");
        k_string.setOrientation(KineticObject.BOTTOM_CENTER);
        k_string.setPosition(x_pos, y_pos);
		k_string.setFont(rainyFont, Font.PLAIN,size);

		sub = new Sequence("",7500, 1500);
		seq.addCastMember(sub);
		k_string = new KineticString();
		sub.addCastMember(k_string);		
		k_string.setString("out");
                k_string.setOrientation(KineticObject.BOTTOM_CENTER);
                k_string.setPosition(x_pos, y_pos);
                k_string.setColor(0,0,0,.99);
		k_string.setFont(rainyFont, Font.PLAIN,size);
		k_string.alpha.addBehavior( new Change(1000,500,-.99));
		k_string.y.addBehavior( new Change(500,500,10));

	}

}
