/**
 * 
 */
package edu.ncsu.csc216.simulation.environment.utils;

/**
 * The class to keep track of the location (by row and column) of a certain
 * square on the grid
 * 
 * @author kbunker
 *
 */
public class Location {

	/** the row of the location */
	private int row;

	/** the column of the location */
	private int column;

	/**
	 * Constructor for the location. Sets the row and column of the square
	 * 
	 * @param row
	 *            the row for the location
	 * @param column
	 *            the column for the location
	 */
	public Location(int row, int column) {

		this.row = row;
		this.column = column;
	}

	/**
	 * Returns the row of the location
	 * 
	 * @return the row of the location
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column of the location
	 * 
	 * @return the column of the location
	 */
	public int getCol() {
		return column;
	}

}
