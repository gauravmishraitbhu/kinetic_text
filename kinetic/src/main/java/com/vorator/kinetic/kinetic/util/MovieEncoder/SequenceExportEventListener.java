/*
 * SequenceExportEventListener.java
 *
 * Created on September 13, 2002, 5:02 AM
 *
 * * 
 * Kinetic Typography Engine - java library for animating expressive text
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
 * 
 */

package com.vorator.kinetic.kinetic.util.MovieEncoder;

/** Allow you to create a class that can respond to the exporter's events (start, stop, and new frame)
 * @author Johnny Lee
 */
public interface SequenceExportEventListener {
    public void onExportBegin();
    
    public void onExportNewFrame(double time);
    
    public void onExportFinish();
}
