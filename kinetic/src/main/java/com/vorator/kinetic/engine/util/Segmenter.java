/*
 * Segmenter.java
 *
 * Created on August 31, 2002, 1:33 PM
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
import javax.swing.text.StyledDocument;
import java.util.NoSuchElementException;

/** abtract base class used to create custom segmenters that can hook into the framework for creating custom reusable animations
 * @author Johnny Lee
 */
public abstract class Segmenter implements java.io.Serializable{    
    /** text that will be segmented
     */    
    protected String text;
    /** styled document of the text to be segmented
     */    
    protected StyledDocument document = null;
    /** start position of the text in the styled docuemnt
     */    
    protected int documentStart = 0;
    /** length of the text in the styled document
     */    
    protected int documentLength = 0;
    boolean moreSegmentsAvailable;
    
    /** Creates a new instance of Segmenter */
    public Segmenter(){
        moreSegmentsAvailable = false;
    }
    /** creates a new instance of the segmenter purely from a plain string
     * @param t text to be segmented
     */    
    public Segmenter(String t) {
        segmentText(t);
    }
    
    /** creates a new instance of the segmeter from a styled document that may contain attribute sets.
     * @param seg segment to tbe segmented
     */    
    public Segmenter(Segment seg) {
        segmentSegment(seg);
    }
    /** sets the string to be segmented by this segmenter
     * @param t text to be segmented
     */    
    public abstract void segmentText(String t);
    /** set sthe segment that will be segmented by this segmenter
     * @param seg segment to be segmented
     */    
    public void segmentSegment(Segment seg) {
        document = seg.getDocument();
        documentStart = seg.getDocumentStart();
        documentLength = seg.getDocumentLength();
        segmentText(seg.getText());
    }
    /** return the next segment available from the segmenter
     * @return the next available segment
     * @throws NoSuchElementException thrown if no more segments are available
     */    
    public abstract Segment getNextSegment() throws NoSuchElementException;
    
    /** checks to see if more segments are available
     * @return true if more segments are available, otherwise false
     */    
    public boolean hasMoreSegements(){
        return moreSegmentsAvailable;
    }
}
