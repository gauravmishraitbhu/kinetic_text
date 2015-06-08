/*
 * SequenceBuilder.java
 *
 * Created on September 13, 2002, 1:19 AM
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
import com.vorator.kinetic.kinetic.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/** utility class to package a custom animation written in code
 * @author Johnny Lee
 */
public interface SequenceBuilder {
    /** constructs the animation using the given parameters
     * @param seq the sequence that will contain the resulting animation
     * @param stageSize dimensions of the canvas
     * @param g2 graphics handle
     */
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize);
}
