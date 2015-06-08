package com.vorator.kinetic.KT_Test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import com.vorator.kinetic.kinetic.*;

public class ActionSet {

	static public void yell(Sequence target, String word, double start_time, double x, double y, String font_name) {
		yell(target, word, start_time,x,y,800, font_name);
		}	
	static public void yell(Sequence target, String word, double start_time, double x, double y, double dur, String font_name) {
		double in_time = 200;
		double grow_size = 130;
		double shrink = -40;
		Sequence sub = new Sequence(word,start_time,dur);
		target.addCastMember(sub);
		KineticString k_string  = new KineticString();
		sub.addCastMember(k_string);
		k_string.setString(word);
        k_string.setOrientation(KineticObject.MIDDLE_CENTER);
        k_string.setPosition(x,y);
		k_string.setColor(0,0,0,0);
		k_string.setFont(font_name, Font.BOLD,1);
		k_string.alpha.addBehavior(new Change(0,10,1));
		k_string.font_size.addBehavior(new Curve(0,in_time,0,1,grow_size));
		k_string.font_size.addBehavior(new Change(in_time,100,shrink));
		k_string.red.addBehavior( new Change(in_time+100,100,0.8));
		k_string.green.addBehavior( new Change(in_time+100,100,0.8));
		k_string.blue.addBehavior( new Change(in_time+100,100,0.8));
		k_string.alpha.addBehavior(new Change(dur-500,500,-1));
		k_string.x.addBehavior(new Jitter(0,4000,5,0));
		k_string.y.addBehavior(new Jitter(0,4000,5,0));
		k_string.rotation.addBehavior(new Jitter(0,4000,5,0));
	}
	
	static public void sentenceFade(Sequence seq, String text, Graphics2D graphics_handle, double delay, double in, double out, double hold, String font_name, double size, double space, boolean wait_done, double end_hold) {
		StringTokenizer st = new StringTokenizer(text);
		double left = 0;
		int i = 0;
		int count = st.countTokens() - 1;
		Sequence new_seq;
	    while (st.hasMoreTokens()) {
         	String word = st.nextToken();
         	if(wait_done)
				new_seq = new Sequence(word,i*delay,in+out+hold + delay*(count - i)+end_hold);
			else
	         	new_seq = new Sequence(word,i*delay,in+out+hold);

			seq.addCastMember(new_seq);
			KineticString k_string = new KineticString();
			new_seq.addCastMember(k_string);
			k_string.setString(word);
            k_string.setPosition(left,0);
            k_string.setOrientation(KineticObject.BOTTOM_LEFT);
            k_string.setFont(font_name,Font.PLAIN, size);
			k_string.setColor(0,0,0,0);
			k_string.alpha.addBehavior(new Change(0,in,.99));
			if(wait_done)
				k_string.alpha.addBehavior(new Change(in+hold + delay*(count - i) +end_hold,out,-.99));
			else
				k_string.alpha.addBehavior(new Change(in+hold,out,-.99));

			k_string.initBounds(graphics_handle);
			left += k_string.getWidth() + space;

			i++;
     	}	
	}
	
	static public KineticString pulse_word(Sequence seq, String text, String font_name, double start, double dur, double in, double out, double x, double y, double size) {
		Sequence sub = new Sequence(text,start,dur);
		seq.addCastMember(sub);
		KineticString kString = new KineticString();
		kString.setString(text);
		kString.setFont(font_name,Font.PLAIN, size);
		kString.setOrientation(KineticString.BOTTOM_LEFT);
		kString.setColor(0,0,0,0);
		sub.addCastMember(kString);
		sub.setPosition(x,y);
		kString.alpha.addBehavior( new Change(0,in,.99));
		kString.alpha.addBehavior( new Change(dur - out,out,-.99));
	
		return kString;
	}
	
	static public double ConstructSentence(Sequence seq, String text, String font_name,int font_style,double size,double duration, Graphics2D graphics_handle) {
		StringTokenizer st = new StringTokenizer(text);
		double left = 0;
		int count = st.countTokens() - 1;
		double in = 1000;
		double out = 500;
		double space = size/4.0;
		
		double delay = 50;
		double x_offset;
		double y_offset;
		double size_offset = 70;

		Sequence new_seq;
		String word;
		KineticString k_string;
		int i  =0;
	    while (st.hasMoreTokens()) {
			x_offset = 200 * (Math.random() - 0.5);
			y_offset = 200 * (Math.random() - 0.5);

         	word = st.nextToken();
         	new_seq = new Sequence(word,i*delay,duration);

			if(seq.getDuration() < i*delay + duration)
				seq.setDuration(i*delay + duration);
				
			seq.addCastMember(new_seq);
			k_string = new KineticString();
			new_seq.addCastMember(k_string);
			k_string.setString(word);
            k_string.setPosition(left+ x_offset,y_offset);
            k_string.setOrientation(KineticObject.BOTTOM_LEFT);
            k_string.setFont(font_name,font_style, size);
			k_string.setColor(0,0,0,0);
						
			k_string.x.addBehavior(new Curve(0,in,1,0,-x_offset));
			k_string.y.addBehavior(new Curve(0,in,1,0,-y_offset));
			k_string.font_size.addBehavior(new Change(0,0,size_offset));
			k_string.font_size.addBehavior(new Curve(0,in,1,0,-size_offset));
			k_string.alpha.addBehavior(new Change(0,in,.99));
			k_string.alpha.addBehavior(new Change(duration - out,out,-.99));

			k_string.initBounds(graphics_handle);
			left += k_string.getWidth()*1.07 + space;

			i++;
    	}
    	return count*delay;
	}


	static public double ConstructSentenceToneofVoice(Sequence seq, String text, String font_name,int font_style,double size,double duration, Graphics2D graphics_handle) {
		StringTokenizer st = new StringTokenizer(text);
		double left = 0;
		int count = st.countTokens() - 1;
		double in = 1000;
		double out = 500;
		double space = size/4.0;
		
		double delay = 50;
		double x_offset;
		double y_offset;
		double size_offset = 70;

		Sequence new_seq;
		String word;
		KineticString k_string;
		int i  =0;
	    while (st.hasMoreTokens()) {
			x_offset = 200 * (Math.random() - 0.5);
			y_offset = 200 * (Math.random() - 0.5);

         	word = st.nextToken();
         	
         	new_seq = new Sequence(word,i*delay,duration);

			if(seq.getDuration() < i*delay + duration)
				seq.setDuration(i*delay + duration);
				
			seq.addCastMember(new_seq);
			k_string = new KineticString();
			new_seq.addCastMember(k_string);
			k_string.setString(word);
            k_string.setPosition(left+ x_offset,y_offset);
            k_string.setOrientation(KineticObject.BOTTOM_LEFT);
            k_string.setFont(font_name,font_style, size);
			k_string.setColor(0,0,0,0);
						
			k_string.x.addBehavior(new Curve(0,in,1,0,-x_offset));
			k_string.y.addBehavior(new Curve(0,in,1,0,-y_offset));
			k_string.font_size.addBehavior(new Change(0,0,size_offset));
			k_string.font_size.addBehavior(new Curve(0,in,1,0,-size_offset));
			k_string.alpha.addBehavior(new Change(0,in,.99));
			k_string.alpha.addBehavior(new Change(duration - out,out,-.99));

			if(i == count) {
				k_string.setOrientation(KineticObject.MIDDLE_CENTER);
	            k_string.setPosition(left+ x_offset + 35,y_offset-10);
	            
				Jitter j = new Jitter(in,600,0,0);
				j.amount.addBehavior(new Hop(0,400,1,1,10));
				k_string.x.addBehavior(j);

				j = new Jitter(in,1000,0,0);
				j.amount.addBehavior(new Hop(0,400,1,1,10));
				k_string.y.addBehavior(j);

				k_string.font_size.addBehavior(new Hop(in,400,.7,.7,20));
			}


			k_string.initBounds(graphics_handle);
			left += k_string.getWidth()*1.1 + space;
			i++;
    	}
    	return count*delay;
	}
	
	static public double ConstructSentenceAnalogousMotion(Sequence seq, String text, String font_name,int font_style,double size,double duration, Graphics2D graphics_handle) {
		StringTokenizer st = new StringTokenizer(text);
		double left = 0;
		int count = st.countTokens() - 1;
		double in = 1000;
		double out = 500;
		double space = size/4.0;
		
		double delay = 50;
		double x_offset;
		double y_offset;
		double size_offset = 70;

		Sequence new_seq;
		String word;
		KineticString k_string;
		int i  =0;
	    while (st.hasMoreTokens()) {
			x_offset = 200 * (Math.random() - 0.5);
			y_offset = 200 * (Math.random() - 0.5);

         	word = st.nextToken();
         	
         	new_seq = new Sequence(word,i*delay,duration);

			if(seq.getDuration() < i*delay + duration)
				seq.setDuration(i*delay + duration);
				
			seq.addCastMember(new_seq);
			k_string = new KineticString();
			new_seq.addCastMember(k_string);
			k_string.setString(word);
            k_string.setPosition(left+ x_offset,y_offset);
            k_string.setOrientation(KineticObject.BOTTOM_LEFT);
            k_string.setFont(font_name,font_style, size);
			k_string.setColor(0,0,0,0);
						
			k_string.x.addBehavior(new Curve(0,in,1,0,-x_offset));
			k_string.y.addBehavior(new Curve(0,in,1,0,-y_offset));
			k_string.font_size.addBehavior(new Change(0,0,size_offset));
			k_string.font_size.addBehavior(new Curve(0,in,1,0,-size_offset));
			k_string.alpha.addBehavior(new Change(0,in,.99));
			k_string.alpha.addBehavior(new Change(duration - out,out,-.99));

			if(i == count) {
				k_string.setOrientation(KineticObject.MIDDLE_CENTER);
	            k_string.setPosition(left+ x_offset + 40,y_offset-10);
				k_string.font_size.addBehavior(new Curve(in + 500, 1500,1,0,30));
				k_string.alpha.addBehavior(new Change(in, 0,-.6));
				k_string.alpha.addBehavior(new Change(in + 500, 1500,-.4));

				k_string = new KineticString();
				new_seq.addCastMember(k_string);
				k_string.setString(word);
	            k_string.setPosition(left + 40,-10);
            	k_string.setOrientation(KineticObject.MIDDLE_CENTER);
            	k_string.setFont(font_name,font_style, size);
				k_string.setColor(0,0,0,0);
				k_string.alpha.addBehavior(new Change(in,0,.6));
//				k_string.alpha.addBehavior(new Change(in + 500,500,-.79));

			}
			
			k_string.initBounds(graphics_handle);
			left += k_string.getWidth()*1.1 + space;
			i++;
    	}
    	return count*delay;
	}

	static public void greyTitleText(Sequence seq, String text, String font_name,double x, double y, double size, double delay) {
		double in = 1000;
		double out = 1000;
		
		KineticString k_string;
		Sequence new_seq = new Sequence(text,delay, 100000000);
		new_seq.setPosition(x,y);
		seq.addCastMember(new_seq);
		k_string = new KineticString();
		new_seq.addCastMember(k_string);
		k_string.setString(text);
        k_string.setPosition(0,0);
        k_string.setOrientation(KineticObject.BOTTOM_LEFT);
        k_string.setFont(font_name,Font.PLAIN, size);
		k_string.setColor(0,0,0,0);
					
		k_string.alpha.addBehavior(new Change(0,in,.1));
	}
	
	
	static public double HopIn(Sequence seq, String text, double delay){
		StringTokenizer st = new StringTokenizer(text);
		double WordDuration = 1400;
		double WordUnitDelay = 170;
		double WordHopHeightVariation = 100;
		double WordHopWidthVariation = 320;
		double WordBaseX = 320;
		double WordBaseY = 170;
		double WordFadeOutTime = 0.5;

		double ySquish_amount = 0.3;
		double xSquish_amount = -0.05;
		double Offscreen_height = 500;
		int FontSize = 40;


		Sequence sub = new Sequence("Hop", delay,0);
		seq.addCastMember(sub);
		
      	double x1,x2,y;
		x1 = WordBaseX + WordHopWidthVariation * (Math.random() - 0.5);
		x2 = WordBaseX + WordHopWidthVariation * (Math.random() - 0.5);
		y = WordBaseY + WordHopHeightVariation * (Math.random() - 0.5);
		double start_time = 0;

	    while (st.hasMoreTokens()) {
         	String word = st.nextToken();

			WordHopHeightVariation = 100;
			WordHopWidthVariation = 320;
			WordBaseX = 320;
			WordBaseY = 170;
         	
         	
         	
         	if(word.compareTo("*") == 0) {
         		start_time += 500;
         		continue;
         		}
         	
         	
         				//next word starts where this word lands.
			x1 = x2;
			x2 = WordBaseX + WordHopWidthVariation * (Math.random() - 0.5);
			y = WordBaseY + WordHopHeightVariation * (Math.random() - 0.5);
         		
         	int word_length = word.length();
         	
         	char first_char = word.charAt(0);

			while (Math.abs(x2 - x1) < 20)
				x2 = WordBaseX + WordHopWidthVariation * (Math.random() - 0.5);


			if(first_char == '!') {
				WordHopWidthVariation = 600;
				while (Math.abs(x2 - x1) < 200)
					x2 = WordBaseX + WordHopWidthVariation * (Math.random() - 0.5);
				
				y += 50;
				
				word = word.substring(1,word_length);
				word_length = word.length();
				}
         	
			char last_char = word.charAt(word_length - 1);
			if((last_char == '"')&&(word_length >= 2))
				last_char = word.charAt(word_length - 2);

         	switch(last_char) {
         		case '.':
         			word_length += 50;
         			break;
         		case ',':
         			word_length += 25;
         			break;
         		case ':':
         			word_length += 50;
         			break;
         		case ';':
         			word_length += 50;
         			break;
         		case '!':
         			word_length += 50;
         			break;
         		case '?':
         			word_length += 50;
         			break;
         	}




			
         	Sequence new_seq = new Sequence("HopIn",start_time,WordDuration);
         	
         	if(start_time + WordDuration > sub.getDuration())
         		sub.setDuration(start_time + WordDuration);
			start_time += WordUnitDelay*Math.sqrt(word_length);
			sub.addCastMember(new_seq);
			KineticString k_string = new KineticString();
			new_seq.addCastMember(k_string);
			k_string.setString(word);
            k_string.setPosition(x1,Offscreen_height);
            k_string.setOrientation(KineticObject.BOTTOM_CENTER);
            k_string.setFont("Arial Black",Font.PLAIN, FontSize);
			k_string.setColor(0,0,0,.99);
			k_string.x.addBehavior(new Change(0,new_seq.getDuration(),x2 - x1));
			k_string.y.addBehavior(new Hop(0,new_seq.getDuration(),0.7,0.7,-y));
			k_string.yScale.addBehavior(new HopSecondary(0,new_seq.getDuration(),0.7,0.7,ySquish_amount));
			k_string.xScale.addBehavior(new HopSecondary(0,new_seq.getDuration(),0.7,0.7,xSquish_amount));
			k_string.alpha.addBehavior(new Change(new_seq.getDuration()*(1.0 - WordFadeOutTime),new_seq.getDuration()*WordFadeOutTime,-0.99));
     	}
     	return sub.getDuration();
	}
	
	static public void RSVP(Sequence seq, String text, double delay, double x, double y, Color c){
		StringTokenizer st = new StringTokenizer(text);
		int WordUnitDelay = 120;
		int WordOverLap = 50;
		double WordAttackTime = 0.2;  //0.0 to 1.0
		double WordReleaseTime = 0.3; //0.0 to 1.0
		double WordX = x;
		double WordY = y;
		double start_time = delay;
	    while (st.hasMoreTokens()) {
         	String word = st.nextToken();
         	int word_length = word.length();
			char last_char = word.charAt(word_length - 1);
			if((last_char == '"')&&(word_length >= 2))
				last_char = word.charAt(word_length - 2);

         	switch(last_char) {
         		case '.':
         			word_length += 30;
         			break;
         		case ',':
         			word_length += 30;
         			break;
         		case ':':
         			word_length += 30;
         			break;
         		case ';':
         			word_length += 30;
         			break;
         		case '!':
         			word_length += 30;
         			break;
         		case '?':
         			word_length += 30;
         			break;
         	}
         	Sequence new_seq = new Sequence("RSVP",start_time,Math.sqrt(word_length)*WordUnitDelay + WordOverLap);
         	start_time += Math.sqrt(word_length)*WordUnitDelay;
         	if(start_time > seq.getDuration())
         		seq.setDuration(start_time);
			seq.addCastMember(new_seq);
			KineticString k_string = new KineticString();
			new_seq.addCastMember(k_string);
			k_string.setString(word);
                        k_string.setPosition(WordX,WordY);
                        k_string.setOrientation(KineticObject.BOTTOM_LEFT);
                        k_string.setFont("Arial Narrow",Font.PLAIN, 35);
                        k_string.setColor(c);
                        k_string.alpha.setValue(0);

			k_string.alpha.addBehavior(new Change(0,new_seq.getDuration()*WordAttackTime,.99));
			k_string.alpha.addBehavior(new Change(new_seq.getDuration()*(1.0 - WordReleaseTime),new_seq.getDuration()*WordReleaseTime,-.99));
     	}
	}	
}