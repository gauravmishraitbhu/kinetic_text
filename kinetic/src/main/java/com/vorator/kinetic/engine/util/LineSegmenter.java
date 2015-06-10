/*
 * LineSegmenter.java
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
/** Segments text base on sentence on end line delimiters (\n\r\f)
 * @author Johnny Lee
 */
public class LineSegmenter extends Segmenter{
    /** string tokenizer
     *
     */    
    protected StringTokenizer tokenizer;
    /** base duration for a segment
     *
     */    
    protected double BaseDuration = 0; //ms
    /** duration per "unit" used when calculating a the full delay based on square root of the number of characters
     */    
    protected double UnitDelay = 170; //ms
    /** delays inserted for sentence end punctuation characters
     */    
    protected double EndCharacterDelay = 1250; //ms
    /** delay inserted for commas
     */    
    protected double CommaDelay = 750; //ms
    /** current time offset
     *
     */    
    protected int currentOffset = -1;

    /** creates a new intance of the line segmenter
     */    
    public LineSegmenter(){
        super();
    }
    /** creates a new instance of the line segmenter from a string of text
     * @param t text string
     */    
    public LineSegmenter(String t) {
        super(t);
    }
    
    /** creates a new instance of the segmenter with an optionally decorated segment object
     * @param seg segment object
     */    
    public LineSegmenter(Segment seg) {
        super(seg);
    }
    /** sets the text string that will be segmented
     * @param t text string
     */    
    public void segmentText(String t){
        text = t;
        currentOffset = -1;
        tokenizer = new StringTokenizer(t,"\n\r\f");
        moreSegmentsAvailable = tokenizer.hasMoreTokens();
    }
     
    /** gets the next segment from the text
     * @return the next segment
     * @throws NoSuchElementException thorws this if no more segments are available
     */    
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
            
            if(!(tokenizer.hasMoreTokens()))
                moreSegmentsAvailable = false;
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
