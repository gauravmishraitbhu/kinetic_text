package com.vorator.kinetic.app;

/**  interface for wrapping the engine used for kinetic typography.
 * @author gaurav.
 *
 */
public interface  KineticEngine {
	
	public void setupEngine();
	
	public void beginExport();
	
	public void postExportComplete();
	
}
