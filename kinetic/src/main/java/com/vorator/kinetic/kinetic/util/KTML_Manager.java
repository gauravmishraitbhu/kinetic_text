/*
 * KTML_Manager.java
 *
 * Created on November 3, 2002, 2:21 PM
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
import java.util.ArrayList;
import java.lang.reflect.Field;

/** Experimental and under development.  Do not use.
 * @author Johnny Lee
 */
public class KTML_Manager {
    
    private Sequence seq = new Sequence();
    private KineticString ks = new KineticString();
    /** Creates a new instance of KTML_Manager */
    public KTML_Manager() {
    }
    
    public void generateKTML(KineticObject o){
        Class c = o.getClass();
        out(c.getName());
        Field[] fields = c.getFields();
        for (int i = 0; i < fields.length; i++) {
         String fieldName = fields[i].getName();
         Class typeClass = fields[i].getType();
         String fieldType = typeClass.getName();
         out(fieldName);
        }
        /*
        ArrayList al = o.getPropertyList();
        for(int i = 0; i < al.size(); i++){
            generateKTML((KineticProperty)al.get(i));
        }
        al = o.getTimeFilterList();
        for(int i = 0; i < al.size(); i++){
            generateKTML((TimeFilter)al.get(i));
        }*/
        if(c.isInstance(seq)){
            ArrayList al = ((Sequence)o).cast;
            for(int i = 0; i < al.size(); i++){
                generateKTML((KineticObject)al.get(i));
            }
        }
    }
    public void generateKTML(KineticProperty p){
        out(p.getClass().getName());
        //do manual variables

        Field[] fields = p.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
         String fieldName = fields[i].getName();
         Class typeClass = fields[i].getType();
         String fieldType = typeClass.getName();
         System.out.println("Name: " + fieldName + ", Type: " + fieldType);
        }

        ArrayList al = p.getBehaviorList();
        for(int i = 0; i < al.size(); i++){
            generateKTML((Behavior)al.get(i));
        }
    }
    public void generateKTML(Behavior b){
        out(b.getClass().getName());
        ArrayList al = b.getPropertyList();
        for(int i = 0; i < al.size(); i++){
            generateKTML((KineticProperty)al.get(i));
        }
        al = b.getTimeFilterList();
        for(int i = 0; i < al.size(); i++){
            generateKTML((TimeFilter)al.get(i));
        }
    }
    public void generateKTML(TimeFilter tf){
        out(tf.getClass().getName());
        ArrayList al = tf.getPropertyList();
        for(int i = 0; i < al.size(); i++){
            generateKTML((KineticProperty)al.get(i));
        }
    }
        
    public void readKTML(String s){
        
    }   
    
    public void out(String s){
        System.out.println(s);
    }
}
