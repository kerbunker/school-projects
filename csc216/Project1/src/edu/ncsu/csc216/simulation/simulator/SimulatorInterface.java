/**
 * 
 */
package edu.ncsu.csc216.simulation.simulator;

import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * The interface for the simulator
 * 
 * @author kbunker
 *
 */
public interface SimulatorInterface {

	/** Runs through the step of the simulation */
	public void step();

	/**
	 *  Gets the 2d painted location array that represents the grid
	 *  
	 *  @return the 2d array of paintedlocations of the map
	 */
	public PaintedLocation[][] getView();

	/** The string array of names 
	 * 
	 * @return the string array of names
	 */
	public String[] getNames();

}
