/*
 * WordSegmenter.java
 *
 * Created on August 31, 2002, 2:00 PM
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

 */

package com.vorator.kinetic.engine.util;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import javax.swing.text.StyledDocument;
/** Segments text using the standard string tokenizer, (white space, tabs, etc).
 * @author Johnny Lee
 */
public class WordSegmenter extends Segmenter{
    protected StringTokenizer tokenizer;
    protected double BaseDuration = 0; //ms
    protected double UnitDelay = 170; //ms
    protected double EndCharacterDelay = 1250; //ms
    protected double CommaDelay = 750; //ms
    protected int currentOffset = -1;
    
    protected double smallTimeShifterAmount = 250;
    protected double bigTimeShifterAmount = 500;

    public WordSegmenter(){
        super();
    }
    public WordSegmenter(String t) {
        super(t);
    }
    
    public WordSegmenter(Segment seg) {
        super(seg);
    }
    
    public void segmentText(String t){
        text = t;
        currentOffset = -1;
        tokenizer = new StringTokenizer(t);
        moreSegmentsAvailable = tokenizer.hasMoreTokens();
    }
     
    public Segment getNextSegment() throws NoSuchElementException{
        if(tokenizer.hasMoreTokens()){
            String word = tokenizer.nextToken();
            int word_length = word.length();
            currentOffset = text.indexOf(word,currentOffset+1);
            char first_char = word.charAt(0);
            char last_char = word.charAt(word_length - 1);

            if((last_char == '"')&&(word_length >= 2))
                    last_char = word.charAt(word_length - 2);
            double punctuationDelay = 0;
            switch(last_char) {
                    case '.':
                            punctuationDelay = EndCharacterDelay;
                            break;
                    case ',':
                            punctuationDelay = CommaDelay;
                            break;
                    case ':':
                            punctuationDelay = EndCharacterDelay;
                            break;
                    case ';':
                            punctuationDelay = EndCharacterDelay;
                            break;
                    case '!':
                            punctuationDelay = EndCharacterDelay;
                            break;
                    case '?':
                            punctuationDelay = EndCharacterDelay;
                            break;
            }
           
            double dur = UnitDelay*Math.sqrt(word_length)+ BaseDuration;
            
            if((word_length == 1)&&(last_char == ',')){
                dur = 0;
                punctuationDelay = smallTimeShifterAmount;
                word = " ";
            }

            if((word_length == 1)&&(last_char == '`')){
                dur = 0;
                punctuationDelay = -smallTimeShifterAmount;
                word = " ";
            }

            moreSegmentsAvailable = tokenizer.hasMoreTokens();
           if(document != null)
              return new Segment(0,dur,punctuationDelay,word,document,documentStart + currentOffset);
           else
              return new Segment(0,dur,punctuationDelay,word);
        }
        else {
            moreSegmentsAvailable = false;
            throw new NoSuchElementException();
        }
    }
}
