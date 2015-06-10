package com.vorator.kinetic.app;

import java.io.File;

import com.vorator.kinetic.KT_Test.NoNoNoDemo;
import com.vorator.kinetic.engine.Sequence;
import com.vorator.kinetic.engine.util.KTEngine;
import com.vorator.kinetic.engine.util.MovieEncoder.SequenceExportEventListener;
import com.vorator.kinetic.engine.util.MovieEncoder.SequenceExporter;

/** wrapper around the cmu's kinetic engine.
 * @author gaurav.
 *
 */
public class VoratorKineticEngine implements KineticEngine,SequenceExportEventListener{


	public KTEngine engine;

	protected int previewMotionBlur = 0;  //real time
	protected int exportMotionBlur = 16; //quicktime export
	protected boolean antialiasing = true;
	protected float exportQuality = 0.8f; //QT compression quality 1.0 = lossless

	protected double previewShutterAngle = 180; //real time
	protected double exportShutterAngle = 180; //quicktime export
	protected double keyIncrement = 5;
	private double seqDurationTotal;

	private String outputFile = "/Users/apple/test.mov";


	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////KineticEngine Methods//////////////////////////////////////////////////////
	
	public void setupEngine() {
		engine = new KTEngine();
		engine.antialiasing = antialiasing;
		engine.setMotionBlur(previewMotionBlur,previewShutterAngle,0.0);
	}
	
	public void setupSequence() {
		engine.setSequenceBuilder(new NoNoNoDemo());
    	engine.buildAnimation();
	}

	public void beginExport() {

		File oFile = new File(outputFile);

		Sequence seq = engine.getSequence();
		System.out.println("Export started");
		if(seq == null){
			System.out.println("Install a seq first then export");
		}else{

			// get the sequence duration, change default to 0, and compute seconds
			long seqDur = (long)seq.getDuration();
			if (seqDur == Long.MAX_VALUE) seqDur = 0;
			
			seqDurationTotal = seqDur;
			int durSec = (int)Math.ceil(seqDur/1000.0);

			float fps = 30.0f; //xx later let the user pick

			SequenceExporter exporter =
					new SequenceExporter(null, seq, durSec, fps);

			exporter.setMotionBlur( exportMotionBlur, exportShutterAngle, 0.0);
			exporter.setEventListener( this );
			double startTime = System.currentTimeMillis();
			exporter.export("file:" + oFile.getPath(), exportQuality);
			double endTime = System.currentTimeMillis();
			System.err.println("Time Taken to render movie: " + (endTime-startTime)/1000.0 + " secs");
		}

	}

	public void postExportComplete() {
		System.out.println("Export Complete");
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////SequenceExportEventListener Methods///////////////////////////////////////


	public void onExportBegin() {
		// TODO Auto-generated method stub

	}

	public void onExportNewFrame(double time) {
		
		if(time % 2000 == 0){
			System.out.println("Percent Complete == "+(time / seqDurationTotal)*100);
		}

	}

	public void onExportFinish() {
		postExportComplete();

	}


	public static void main(String args[]){
		VoratorKineticEngine engine = new VoratorKineticEngine();
		engine.setupEngine();
		engine.setupSequence();
		engine.beginExport();
	}


}
