/**
 * 
 */
package edu.ncsu.csc216.simulation.environment.utils;

import java.awt.Color;

/**
 * Class derived from the location and associates a color, to distinguish the
 * different tiers of animals apart
 * 
 * @author kbunker
 *
 */
public class PaintedLocation extends Location {

	/** the color of the location */
	private Color tint;

	/** the symbol for the location */
	private char symbol;

	/**
	 * Constructor for the painted location
	 * 
	 * @param row
	 *            the row of the location
	 * @param col
	 *            the column of the location
	 * @param color
	 *            the color to set for the location
	 * @param symbol
	 *            the symbol to set for the location
	 */
	public PaintedLocation(int row, int col, Color color, char symbol) {
		super(row, col);
		tint = color;
		this.symbol = symbol;
	}

	/**
	 * Returns the symbol associated with the current location
	 * 
	 * @return the symbol for the current location
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Returns the color for the given location
	 * 
	 * @return the location's color
	 */
	public Color getColor() {
		return tint;
	}

}
