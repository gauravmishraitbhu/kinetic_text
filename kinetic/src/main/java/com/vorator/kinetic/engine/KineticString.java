/**
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

package com.vorator.kinetic.engine;
import java.awt.*;
import java.lang.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.util.StringTokenizer;
/** 
 * string object that contain text and font information for rendering strings on the screen with the engine
 */

public class KineticString extends KineticObject{
        /** text string
         */
	public String s = "";
        /** font name
         */
        protected String font_name;
        /** font style
         */
        protected int font_style;
	/** font size
         */
        public KineticProperty font_size = new KineticProperty(100);
	/** tracking
         */
        public KineticProperty tracking = new KineticProperty(1);
	/** font face
         */
	public KineticDiscreteProperty font_face = new KineticDiscreteProperty(0); //Arial Black
        /** local coordinate bbox
         */
	protected Rectangle2D original_bounds;
	/** global coordinate bbox
         */
        protected GeneralPath transformed_bounds;
        /** glyphvector for text
         */
	protected GlyphVector glyphVector;
	/** font object
         */
        protected Font font;
        /** global coordinate X
         */
	public double absoluteX;
	/** global coordinate Y
         */
        public double absoluteY;
        /** creates a new instance of a KineticString
         */
	public KineticString() {
            super();
            addProperty(font_size);
            addProperty(tracking);
            addProperty(font_face);
            buildFontList();
	}

        /** creates a new instance
        *@param n name
        *@param del delay - ms
        *@param dur duration - ms
        */
        public KineticString(String n, double del, double dur) {
            super(n,del,dur);
            addProperty(font_size);
            addProperty(tracking);
            addProperty(font_face);
            buildFontList();
        }
        /** loads available system fonts
         */
        protected void buildFontList(){
            //init discrete font_faces
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //add fonts by name
            Font fonts[] = ge.getAllFonts() ;
            for(int i = 0; i < fonts.length; i++)
                font_face.addChoice(fonts[i].getName());
            //add fonts by family
            String fontNames[] = ge.getAvailableFontFamilyNames();
            for(int i = 0; i < fontNames.length; i++)
                font_face.addChoice(fontNames[i]);
            setFont("Arial", Font.PLAIN, 40);            
        }
        
        /** main drawing routine with an affine transform - adds a unitiy transforms and calls main routine
         *@param g2 graphics handle
         */
	public void draw(Graphics2D g2) {
		draw(g2, new AffineTransform());
	}
	/** main drawing routing 
         *@param g2 graphics handle
         *@param ax current affine transform
         */
	public void draw(Graphics2D g2, AffineTransform ax) {
            if(!visible)
                    return;

            if((font == null)||(glyphVector == null))
                initBounds(g2); //  <************ big performance hit!!!! regenerating glyphmetrcs.

            if(font_size.hasChanged() || font_face.hasChanged())
                initBounds(g2); //  <************ big performance hit!!!! regenerating glyphmetrcs.
            //have to do this because the font may have changed
            //g2.setFont(font);
            
            //AffineTransform tempTX;
            double current_position = 0;
            for(int i = 0; i < glyphVector.getNumGlyphs(); i++){
                    glyphVector.setGlyphPosition(i,new Point2D.Double(current_position,0));
                    current_position += (glyphVector.getGlyphMetrics(i)).getAdvance()*tracking.getEndValue();
                    //tempTX = glyphVector.getGlyphTransform(i);
                    //tempTX.rotate(30);
                    //glyphVector.setGlyphTransform(i,tempTX);
                    //tempTX = glyphVector.getGlyphTransform(i);
                    }
	    original_bounds = glyphVector.getVisualBounds();
	    
            AffineTransform origTransform = new AffineTransform(ax);
            ax.translate(x.getEndValue(),y.getEndValue());
            ax.rotate(rotation.getEndValue() * Math.PI/180.0);
            ax.shear(xShear.getEndValue(), yShear.getEndValue());
            ax.scale(xScale.getEndValue(), yScale.getEndValue());		
            //center text---------------	    
            double obw = original_bounds.getWidth();
	    double obh = original_bounds.getHeight();
	    double oby = original_bounds.getY();

	    switch(orientation){
			case BOTTOM_LEFT:
				ax.translate(0,0);
				break;
			case BOTTOM_CENTER:
				ax.translate(-obw/2,0);
				break;
			case BOTTOM_RIGHT:
				ax.translate(-obw,0);
				break;
			case MIDDLE_LEFT:
				ax.translate(0,-obh/2 - oby);
				break;
			case MIDDLE_CENTER:
				ax.translate(-obw/2,-obh/2 - oby);
				break;
			case MIDDLE_RIGHT:
				ax.translate(-obw,-obh/2 - oby);
				break;
			case TOP_LEFT:
				ax.translate(0, -oby);
				break;
			case TOP_CENTER:
				ax.translate(-obw/2, -oby);
				break;
			case TOP_RIGHT:
				ax.translate(-obw, -oby);
				break;
			default://middle center
				ax.translate(-obw/2,-obh/2 - oby);
				break;
		}

		GeneralPath gp = new GeneralPath(original_bounds);
		transformed_bounds = (GeneralPath)gp.createTransformedShape(ax);
		
		Rectangle bbox = transformed_bounds.getBounds();
		absoluteX = bbox.getX() + bbox.getWidth()/2.0;
		absoluteY = bbox.getY() + bbox.getHeight()/2.0;

		g2.setTransform(ax);
        	g2.setPaint(new Color(  (float)(red.getEndValue()),
                                        (float)(green.getEndValue()),
                                        (float)(blue.getEndValue()),
                                        (float)(alpha.getEndValue())));

                g2.drawGlyphVector(glyphVector,0,0);
		if(selected && (mode != 1))
			g2.draw(original_bounds);
		
		ax.setTransform(origTransform);
	}
    /** set font face
     *@param name name of the font family
     */
    public void setFontFace(String name){
            font_name = name;
    }
    /** set font using paramters
     *@param name name of the font family
     *@param style type style
     *@param size font size
     */
    public void setFont(String name, int style, double size){

            font_name = name;
            for(int i = 0; i < font_face.getSize(); i++) {
                    if(name.compareTo((String)font_face.getChoice(i)) == 0){
                            font_face.setValue(i);
                            //System.err.println(i);
                            break;
                            }
                    }

            font_style = style;
            font_size.setValue(size);
            //font = new Font(font_name, font_style, (int)font_size.getEndValue());
    }
    /** set font using font object
     *@param f instance of font object
     */
    public void setFont(Font f){
            font = f;
            font_name = f.getName();
            for(int i = 0; i < font_face.getSize(); i++) {
                if((f.getName()).compareTo((String)font_face.getChoice(i)) == 0)
                        font_face.setValue(i);
                }
            font_style = f.getStyle();
            font_size.setValue(f.getSize());
    }

    /** select from from discrete property list
     *@param i index of font from font list
     */
    public void setFontChoice(int i){
            font_face.setValue(i);
    }
    /** set font size
     *@param size font size
     */
    public void setFontSize(double size){
            font_size.setValue(size);
    }

    /** set text string
     *@param new_string new text string
     */
    public void setString(String new_string) {
            s = new_string;	
            if(name == null)
                    name = s;
            }
/** get global coordinate bbox
 *@return global coordinate bbox
 */
    public Rectangle2D getBounds(){
            return transformed_bounds.getBounds();
            }
/** hit test
 *@param p hit location
 *@return true if contains point
 */
    public boolean contains(Point p) {
            if(transformed_bounds == null)
                    return false;
            else
                    return transformed_bounds.contains(p.getX(),p.getY());
    }
/** initialize bounds, create glyphs, fonts, etc.
 *@param g2 graphics handle
 */
    public void initBounds(Graphics2D g2) {
        font = new Font((String)font_face.getEndChoice(), font_style, (int)(font_size.getEndValue()));
        glyphVector = font.createGlyphVector(g2.getFontRenderContext(), s);
        original_bounds = glyphVector.getVisualBounds();
        transformed_bounds = new GeneralPath(original_bounds);
        Rectangle bbox = transformed_bounds.getBounds();
        absoluteX = bbox.getX() + bbox.getWidth()/2.0;
        absoluteY = bbox.getY() + bbox.getHeight()/2.0;
    }
/** get global coordinate width
 *@return width
 */
    public double getWidth() {
            return transformed_bounds.getBounds().getWidth();
    }
/** get the advance of the entire word plus a space
 *@param g2 graphics handle
 *@param space add the advance of a space
 *@return advance of word and a space in local scale
 */
    public double getAdvance(Graphics2D g2, boolean space){
        if(glyphVector == null)
            initBounds(g2);
        double advance = 0;
        for(int i = 0; i < glyphVector.getNumGlyphs(); i++) {
            advance += (glyphVector.getGlyphMetrics(i)).getAdvance();
        }
        if(space)
            return advance + getAdvance(' ',g2);
        else
            return advance;
    }
    /** get the advance of a character using the world of this string
     *@param c character
     *@param g2 graphics handle
     *@return advance of the character in local world space
     */
    public double getAdvance(char c,Graphics2D g2) {
        GlyphVector gv = font.createGlyphVector(g2.getFontRenderContext(), String.valueOf(c));
        return (gv.getGlyphMetrics(0)).getAdvance();
    }
    /** get decent of this object
     *@return decent
     */
    public double getDescent() {
            return original_bounds.getY() + original_bounds.getHeight();
    }
    /** get height
     *@return height
     */
    public double getHeight() {
            return transformed_bounds.getBounds().getHeight();
    }
    /** get global coordinate X
     *@return global coordinate X
     */
    public double getAbsoluteX() {
            return absoluteX;
    }
    /** get global coordinate Y
     *@return global coordinate y
     */
    public double getAbsoluteY() {
            return absoluteY;
    }
    /** produces a new instance that is an exact copy
     * @return new instanct of a KineticString
     */
    public KineticString copy(){
       KineticString newKs = new KineticString();
       for(int i = 0; i < properties.size(); i++)
           ((KineticProperty)newKs.properties.get(i)).setValue(
           ((KineticProperty)properties.get(i)).getInitialValue()); 

       newKs.setString(s);
       newKs.setFont(font);
       newKs.orientation = orientation;
       return newKs;
    }
    /** produces a new instance that captures the state of the kinetic string at a specified time
     * @param time time when to capture the state - ms
     * @return new instance of the Kinetic String
     *
     */
    public KineticString copy(double time){
       double last = lastUpdateTime;
       update(time);
       KineticString newKs = new KineticString();
       newKs.setString(s);
       newKs.setFont(font);

      for(int i = 0; i < properties.size(); i++)
           ((KineticProperty)newKs.properties.get(i)).setValue(
           ((KineticProperty)properties.get(i)).getEndValue()); 
       newKs.orientation = orientation;
       update(last);
       return newKs;
    }
    
    /** breaks this KineticString into individual characters in KineticString wrapped into the returned sequence
     *@param delay time delay for the produced sequence
     *@param duration time duration of the produced sequence
     *@param g2 graphics handle
     *@return resulting sequence containing the list of KineticStrings containing each character and positioned in space according to glyph metrics
     */
    public Sequence breakIntoCharacters(double delay, double duration,Graphics2D g2){
            Sequence new_seq = new Sequence(s, delay, duration);
            new_seq.setPosition(x.getEndValue(), y.getEndValue());
            int word_length = s.length();
            initBounds(g2);
            double word_width = getWidth();
            int count = 0;
            double char_spacing = 0;
            double char_descent = 0;
            double position = 0;//-word_width/2.0;
            char letter;
            for(int i = 0; i < word_length; i++){
                    KineticString k_string = new KineticString();
                    new_seq.addCastMember(k_string);
                    k_string.setString(s.substring(i, i+1));
                    k_string.setFont(font);

                    k_string.initBounds(g2);
                    k_string.setPosition(position , 0);
                    position += (glyphVector.getGlyphMetrics(i)).getAdvance();

                    //trying to do other positionings produce pixel size inaccruacies
                    //when rendering text. 
                    k_string.setOrientation(BOTTOM_LEFT); 
                    k_string.setColor(red.getEndValue(),green.getEndValue(),blue.getEndValue(),alpha.getEndValue());			
                    count++;
                    }
            return new_seq;
    }

    
    /** Breaks the KineticString into space delimited words while preserving the spatial layout of the text
     * @return new instance of a Sequence object containing a list of each KineticString resulting from the split
     * @param delay the start time - ms
     * @param duration duration of the sequence - ms
     * @param g2 graphic context for accessing font metrics
     */    
    public Sequence breakIntoWords(double delay, double duration,Graphics2D g2){
            StringTokenizer tokenizer = new StringTokenizer(s);
            Sequence new_seq = new Sequence(s, delay, duration);
            new_seq.setPosition(x.getEndValue(), y.getEndValue());
            initBounds(g2);
            double position = 0;
            char letter;
            while(tokenizer.hasMoreTokens()){
                    KineticString k_string = new KineticString();
                    new_seq.addCastMember(k_string);
                    k_string.setString(tokenizer.nextToken());
                    k_string.setFont(font);

                    k_string.initBounds(g2);
                    k_string.setPosition(position , 0);
                    position += k_string.getAdvance(g2,true);

                    //trying to do other positionings produce pixel size inaccruacies
                    //when rendering text. 
                    k_string.setOrientation(BOTTOM_LEFT); 
                    k_string.setColor(red.getEndValue(),green.getEndValue(),blue.getEndValue(),alpha.getEndValue());			
            }
            return new_seq;
    }
    /** support interactivity
     *@param e mouse event
     *@param button_event true if generated by a button
     *@param time timestamp
     *@param g2 graphics handle
     */
    public void handleMouseEvent(java.awt.event.MouseEvent e, boolean button_event, double time, Graphics2D g2){
            if(mode == 0){
                    if(button_event && contains(new Point(e.getX(),e.getY())))
                            selected = !selected;
                    }
    }    
}
