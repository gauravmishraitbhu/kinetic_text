/*
 * HopInDemo.java
 *
 * Created on October 23, 2002, 1:37 AM
 */

package com.vorator.kinetic.KT_Test;
import com.vorator.kinetic.engine.*;
import com.vorator.kinetic.engine.util.*;

import java.awt.*;


/**
 *
 * @author  Johnny Lee
 */
public class HopInDemo implements SequenceBuilder{
    
    /** Creates a new instance of HopInDemo */
    public HopInDemo() {
    }
    
    public void buildSequence(Sequence seq, Graphics2D g2, Rectangle stageSize) {
        WordSegmenter segmenter = new WordSegmenter("Testing, testing. This composite effect is called Hop-In. Each word uses squash and stretch as it jumps up on the screen. Words fade out when they fall allowing newer words to take the foreground.  The pacing of each word is proportional to the square root of word length. Extra delays are added for punctuation. Each new word appears where the previous word lands. This prepares the reader for the next word by directing their attention , , , , just , , , , like , , , , this.");
        HopInEffect hop = new HopInEffect(segmenter);
        hop.speed.setValue(.9);
        hop.energy.setValue(1.5);
        seq.setDuration(hop.build(seq,segmenter,g2,stageSize,500)+1000);
        seq.setPosition(0,0);
    }    
}
