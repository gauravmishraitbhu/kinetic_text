/* Adpated from
 * @(#)JpegImagesToMovie.java	1.3 01/03/13
 *
 * Copyright (c) 1999-2001 Sun Microsystems, Inc. All Rights Reserved.
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

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

import com.sun.image.codec.jpeg.*;

import javax.media.*;
import javax.media.control.*;
import javax.media.protocol.*;
import javax.media.protocol.DataSource;
import javax.media.datasink.*;
import javax.media.format.VideoFormat;

/**
 * This code takes a BufferedImageSource, and turns it into a JPEG compressed Quicktime file
 */
public class BufferedImageToQTEncoder implements ControllerListener, DataSinkListener {

    protected Object waitSync = new Object();
    protected Object waitFileSync = new Object();
    protected boolean stateTransitionOK = true;
    protected boolean fileDone = false;
    protected boolean fileSuccess = true;
    protected Processor proc;
    protected ImageDataSource ids;
    protected DataSink ds;
    protected float quality;

    /** wrappes up a lot of JMF stuff to turn a BufferedImage source into a JPEG compressed Quicktime file
     * @param outputURL destination file
     * @param source a buffered image source that can provide new image frames
     * @param q image quality for compression (1.0f = lossless, 0.0f=lossy but smaller files)
     */    
	public BufferedImageToQTEncoder(String outputURL, BufferedImageSource source, float q){
                quality = q;
		// Check for output file extension.
		if (!outputURL.endsWith(".mov") && !outputURL.endsWith(".MOV")) {
		    System.err.println("The output file extension should end with a .mov extension");
		}

		// Generate the output media locators.
		MediaLocator oml;

		if ((oml = createMediaLocator(outputURL)) == null) {
		    System.err.println("Cannot build media locator from: " + outputURL);
		    System.exit(0);
		}

		initialize(source, oml);
	}

	public boolean run(){
		//System.err.println("start processing...");

		// OK, we can now start the actual transcoding.
		try {
		    proc.start();
		    ds.start();
		} catch (IOException e) {
		    System.err.println("IO error during processing");
		    return false;
		}

		// Wait for EndOfStream event.
		waitForFileDone();

		// Cleanup.
		try {
		    ds.close();
		} catch (Exception e) {}
		proc.removeControllerListener(this);

		//System.err.println("...done processing.");

		return true;
	}

    public boolean initialize(BufferedImageSource source, MediaLocator outML) {
		ids = new ImageDataSource(source,quality);

		try {
		    //System.err.println("- create processor for the image datasource ...");
		    proc = Manager.createProcessor(ids);
		} catch (Exception e) {
		    System.err.println("Yikes!  Cannot create a processor from the data source.");
	    	return false;
		}

		proc.addControllerListener(this);

		// Put the Processor into configured state so we can set
		// some processing options on the processor.
		proc.configure();
		if (!waitForState(proc, proc.Configured)) {
		    System.err.println("Failed to configure the processor.");
		    return false;
		}

		// Set the output content descriptor to QuickTime. 
		proc.setContentDescriptor(new ContentDescriptor(FileTypeDescriptor.QUICKTIME));

		// Query for the processor for supported formats.
		// Then set it on the processor.
		TrackControl tcs[] = proc.getTrackControls();
		Format f[] = tcs[0].getSupportedFormats();
		if (f == null || f.length <= 0) {
		    System.err.println("The mux does not support the input format: " + tcs[0].getFormat());
		    return false;
		}

		//for(int i = 0; i < f.length; i++)
		//    System.err.println("Supported formats: "+ i + " - "  + f[i]);

		tcs[0].setFormat(f[0]); //JPEG
		//System.err.println("Setting the track format to: " + f[7]);

		// We are done with programming the processor.  Let's just
		// realize it.
		proc.realize();
		if (!waitForState(proc, proc.Realized)) {
		    System.err.println("Failed to realize the processor.");
		    return false;
		}

		// Now, we'll need to create a DataSink.
		if ((ds = createDataSink(proc, outML)) == null) {
		    System.err.println("Failed to create a DataSink for the given output MediaLocator: " + outML);
		    return false;
		}

		ds.addDataSinkListener(this);
		fileDone = false;
		return true;
	}


    /**
     * Create the DataSink.***************************************************************
     */
    DataSink createDataSink(Processor p, MediaLocator outML) {
		DataSource ds;
		if ((ds = p.getDataOutput()) == null) {
		    System.err.println("Something is really wrong: the processor does not have an output DataSource");
		    return null;
		}

		DataSink dsink;
		try {
		   // System.err.println("- create DataSink for: " + outML);
		    dsink = Manager.createDataSink(ds, outML);
		    dsink.open();
		} catch (Exception e) {
		    System.err.println("Cannot create the DataSink: " + e);
		    return null;
		}

		return dsink;
    }



    /**
     * Block until the processor has transitioned to the given state.
     * Return false if the transition failed.
     */
    boolean waitForState(Processor p, int state) {
		synchronized (waitSync) {
		    try {
				while (p.getState() < state && stateTransitionOK)
			    waitSync.wait();
			    } catch (Exception e) {}
			}
		return stateTransitionOK;
    }


    /**
     * Controller Listener.
     */
    public void controllerUpdate(ControllerEvent evt) {
    	
			if (evt instanceof ConfigureCompleteEvent ||
			    evt instanceof RealizeCompleteEvent ||
			    evt instanceof PrefetchCompleteEvent) {
			    synchronized (waitSync) {
				stateTransitionOK = true;
				waitSync.notifyAll();
			    }
			} else if (evt instanceof ResourceUnavailableEvent) {
			    synchronized (waitSync) {
				stateTransitionOK = false;
				waitSync.notifyAll();
			    }
			} else if (evt instanceof EndOfMediaEvent) {
				evt.getSourceController().stop();
			    evt.getSourceController().close();
			}
    }




    /**
     * Block until file writing is done. 
     */
    boolean waitForFileDone() {
		synchronized (waitFileSync) {
		    try {
			while (!fileDone)
			    waitFileSync.wait();
		    } catch (Exception e) {}
		}
		return fileSuccess;
    }


    /**
     * Event handler for the file writer.
     */
    public void dataSinkUpdate(DataSinkEvent evt) {
		if (evt instanceof EndOfStreamEvent) {
		    synchronized (waitFileSync) {
			fileDone = true;
			waitFileSync.notifyAll();
		    }
		} else if (evt instanceof DataSinkErrorEvent) {
		    synchronized (waitFileSync) {
			fileDone = true;
			fileSuccess = false;
			waitFileSync.notifyAll();
		    }
		}
    }

    /**
     * Create a media locator from the given string.
     */
    static MediaLocator createMediaLocator(String url) {
		MediaLocator ml;
		if (url.indexOf(":") > 0 && (ml = new MediaLocator(url)) != null)
		    return ml;

		if (url.startsWith(File.separator)) {
		    if ((ml = new MediaLocator("file:" + url)) != null)
			return ml;
		} else {
		    String file = "file:" + System.getProperty("user.dir") + File.separator + url;
		    if ((ml = new MediaLocator(file)) != null)
			return ml;
		}
		return null;
    }


    ///////////////////////////////////////////////
    //
    // Inner classes.
    ///////////////////////////////////////////////


    /**
     * A DataSource to read from a list of JPEG image files and
     * turn that into a stream of JMF buffers.
     * The DataSource is not seekable or positionable.
     */
    class ImageDataSource extends PullBufferDataSource {

		ImageSourceStream streams[];

		ImageDataSource(BufferedImageSource source, float q) {
		    streams = new ImageSourceStream[1];
		    streams[0] = new ImageSourceStream(source,q);
		}

		public void setLocator(MediaLocator source) {
		}

		public MediaLocator getLocator() {
		    return null;
		}

		/**
		 * Content type is of RAW since we are sending buffers of video
		 * frames without a container format.
		 */
		public String getContentType() {
		    return ContentDescriptor.RAW;
		}

		public void connect() {
		}

		public void disconnect() {
		}

		public void start() {
		}

		public void stop() {
		}

		/**
		 * Return the ImageSourceStreams.
		 */
		public PullBufferStream[] getStreams() {
		    return streams;
		}

		/**
		 * We could have derived the duration from the number of
		 * frames and frame rate.  But for the purpose of this program,
		 * it's not necessary.
		 */
		public Time getDuration() {
		    return DURATION_UNKNOWN;
		}

		public Object[] getControls() {
		    return new Object[0];
		}

		public Object getControl(String type) {
		    return null;
		}
    }


    /**
     * The source stream to go along with ImageDataSource.
     */
    class ImageSourceStream implements PullBufferStream {

                public float quality = 1.0f;
		public BufferedImageSource source;
		public int width, height;
		public VideoFormat format;
		public BufferedImage buffered_img;
		public Graphics2D graphics_handle;
		public long frameNumber = 0;

		public boolean ended = false;

		public ImageSourceStream(BufferedImageSource source,float q) {
		    this.width = source.getWidth();
		    this.height = source.getHeight();
		    this.source = source;
                    quality = q;
		    format = new VideoFormat(VideoFormat.JPEG,
					new Dimension(width, height),
					Format.NOT_SPECIFIED,
					Format.byteArray,
					source.getFrameRate());
		}

		/**
		 * We should never need to block assuming data are read from files.
		 */
		public boolean willReadBlock() {
		    return false;
		}

		/**
		 * This is called from the Processor to read a frame worth
		 * of video data.
		 */
	 	public void read(Buffer buf) throws IOException {

		    // Check if we've finished all the frames.
		    if (source.endOfMedia()) {
				// We are done.  Set EndOfMedia.
				//System.err.println("Done reading images.");
				buf.setEOM(true);
				buf.setOffset(0);
				buf.setLength(0);
				ended = true;
				return;
		    }

		    //System.err.println("reading image " + frameNumber);
		    
			buffered_img = source.read(frameNumber);
			frameNumber++;
			byte data[] = JPEGencodeBufferedImage(buffered_img);
			buf.setData(data);
		    buf.setOffset(0);
		    buf.setLength((int)data.length);
		    buf.setFormat(format);
		    buf.setFlags(buf.getFlags() | buf.FLAG_KEY_FRAME);
		}


	    private byte[] JPEGencodeBufferedImage(BufferedImage bufferedImage) {
	    	byte data[] = null;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
                param.setQuality(quality, false);
                encoder.setJPEGEncodeParam(param);
                try{
                    encoder.encode(bufferedImage);
                    data = out.toByteArray();
                    out.close();
                }
        	catch (Exception x) { x.printStackTrace(); }
        	return data;
	    }


		/**
		 * Return the format of each video frame.
		 */
		public Format getFormat() {
		    return format;
		}

		public ContentDescriptor getContentDescriptor() {
		    return new ContentDescriptor(ContentDescriptor.RAW);
		}

		public long getContentLength() {
		    return 0;
		}

		public boolean endOfStream() {
		    return ended;
		}

		public Object[] getControls() {
		    return new Object[0];
		}

		public Object getControl(String type) {
		    return null;
		}
    }
}
