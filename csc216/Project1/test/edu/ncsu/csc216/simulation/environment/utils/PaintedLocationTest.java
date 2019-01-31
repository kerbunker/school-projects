package edu.ncsu.csc216.simulation.environment.utils;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * Tests the painted location method
 * 
 * @author kbunker
 *
 */
public class PaintedLocationTest {

	/**
	 * tests the painted location constructor
	 */
	@Test
	public void testPaintedLocation() {
		PaintedLocation l = new PaintedLocation(0, 0, Color.BLUE, 'A');
		assertEquals(0, l.getRow());
		assertEquals(0, l.getCol());
		assertEquals(Color.BLUE, l.getColor());
		assertEquals('A', l.getSymbol());
	}

}
