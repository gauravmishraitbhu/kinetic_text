/*
 * Segment.java
 *
 * Created on August 31, 2002, 1:44 PM
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

package com.vorator.kinetic.kinetic.util;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleContext;
import java.awt.Font;
import java.awt.Color;
import java.util.Enumeration;
import javax.swing.text.StyledDocument;
//import KTEditor.TagDescriptor; //dammnabit!!!
/** A segment of text bundled with timing infromation and optional attributes which composite effects can respond to.
 * @author Johnny Lee
 */
public class Segment implements java.io.Serializable{
    /** a styled document containing decorated text
     */    
        protected StyledDocument document = null;
        /** set of attributes for this segment of text
         */        
        protected AttributeSet attributes = null;
        /** starting position of this segment of text in the style document
         */        
        protected int documentStart;
        /** length of this segment of text in the styled document
         */        
        protected int documentLength;
        /** lead in time for this segment of text
         */        
        public double leadIn;
        /** lead out time for this segment of text
         */        
        public double leadOut;
        /** run time or sustain of this segment of text
         */      
        public double runTime;
        /** content of the segment in string format
         */        
        public String text;
        /** creates a new isntance of a segment
         */        
        public Segment(){
            text="";
            leadIn= 0.0;
            leadOut = 0.0;
        }
        
        /** creates new instance of a segment with the given timing and text information
         * @param in attack/lead in time in ms
         * @param run sustain time in ms
         * @param out decay/lead out time in ms
         * @param s text in the segment
         */        
        public Segment(double in, double run, double out, String s){
            text = s;
            leadIn = in;
            leadOut = out;
            runTime = run;
            attributes = null;
        }
        /** creates new instance of a segment with the given timing and text information and adopts the attributes fro the specifed position the styled document
         * @param in attack/lead in time in ms
         * @param run sustain time in ms
         * @param out decay/lead out time in ms
         * @param s text in the segment
         * @param d styled document contained attribute information
         * @param start position in the styled document for attributes
         */        
        
        public Segment(double in, double run, double out, String s,StyledDocument d, int start){
            this(in,run,out,s);
            document = d;
            documentStart = start;
            documentLength = s.length();
            attributes = document.getCharacterElement(documentStart).getAttributes();
        }
      
        /** creates new instance of a segment with the specifed text the styled document
         * @param d styled document contained attribute information
         * @param start position in the styled document for attributes
         * @param length of the text in the styled document
         */        
        public Segment(StyledDocument d, int start,int length){
            leadIn = 0;
            leadOut = 0;
            runTime = 0;            
            try{
                text = d.getText(start,length);
            } catch(Exception x){System.err.println(x);}
            
            document = d;
            documentStart = start;
            documentLength = length;
            attributes = document.getCharacterElement(documentStart).getAttributes();
        }

        /** get the text of the segment in string format
         * @return text of the segment
         */
        public String getText(){
            return text;
        }
        /** gets the full duration of the segment
         * @return duration of the segment (ms)
         */
        public double getDuration(){
            return leadIn + leadOut + runTime;
        }
        /** gets the run/sustain time of the segment
         * @return run time of the segment (ms)
         */
        public double getRunTime(){
            return runTime;
        }
        /** gets the lead in or attack time of the segment
         * @return lead in time of the segment (ms)
         */

        public double getLeadIn(){
            return leadIn;
        }
        /** gets the lead out or decay time of the segment
         * @return lead out time of the segment (ms)
         */
        public double getLeadOut(){
            return leadOut;
        }
        /** gets the font associated with the segment if available
         * @return null if no font is associated with segment
         */
        
        public Font getFont(){
            if(attributes == null)
                return null;
            else
                return StyleContext.getDefaultStyleContext().getFont(attributes);
        }
        /** gets the color associated with the segment if available
         * @return Color.BLACK if no color is associated with segment
         */
        
        public Color getForground(){
            if(attributes == null)
                return Color.black;
            else
                return StyleContext.getDefaultStyleContext().getForeground(attributes);            
        }
        
        /** sets the styled document associated with this segment
         * @param d styled document
         */
        public void setDocument(StyledDocument d){
            document = d;
        }
        /** gets the styled document associated with this segment
         * @return styled document
         */
        public StyledDocument getDocument(){
            return document;
        }
        /** gets the start position in the styled document associated with this segment
         * @return start position
         */
       
        public int getDocumentStart(){
            return documentStart;
        }
        /** gets the length of the segment in the styled document
         * @return length
         */

        public int getDocumentLength(){
            return documentLength;
        }
        /** queries if this segment has a particular attribute associated with it
         * @param tagName name of the attribute tage to look for
         * @return true if the tag exists, false otherwise
         */
        
        public boolean hasTag(String tagName){
            if(attributes == null)
                return false;
            
            for (Enumeration en = attributes.getAttributeNames(); en.hasMoreElements(); ){
                  Object nm = en.nextElement();
                  /*  this is hack code used to repair a bad design decision
                  if (nm instanceof TagDescriptor) 
                  {
                    Object v = attributes.getAttribute(nm);
                    if (v != null && v instanceof TagDescriptor) 
                    {
                      TagDescriptor td = (TagDescriptor)v;
                      if(tagName.compareTo(td.getName())== 0)
                        return true;
                    }
                  }*/
                }
            return false;
        }
    }

