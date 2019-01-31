package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Tests the purepredator class
 * @author Kbunker
 *
 */
public class PurePredatorTest {

	/**
	 * Tests the getColor() method
	 */
	@Test
	public void testGetColor() {
		Animal a = new PurePredator('A');
		Configs.setToDefaults();
		assertEquals(Color.RED, a.getColor());
	}

	/**
	 * Tests the act() method
	 */
	@Test
	public void testAct() {
		
		Animal a1 = new PurePredator('A');
		Ecosystem ec = new Ecosystem(2, 2);
		Configs.setToDefaults();
		Location l1 = new Location(0, 0);
		Location l2 = new Location(0, 1);
		Location l3 = new Location(1, 0);
		Location l4 = new Location(1, 1);
		
		a1.enable();
		
		assertTrue(a1.canAct());
		ec.add(a1, l1);
		assertEquals('A', ec.getItemAt(l1).getSymbol());
		
		a1.act(l1, ec);
		a1.enable();
		
		//animal should have moved
		assertNull(ec.getItemAt(l1));
		//gets the location the animal moved to
		Location lMove;
		if (ec.getItemAt(l2) != null) {
			lMove = l2;
		} else if (ec.getItemAt(l3) != null) {
			lMove = l3;
		} else {
			lMove = l4;
		}
		
		assertEquals('A', ec.getItemAt(lMove).getSymbol());
		
		if (ec.getItemAt(l1) == null) {
			ec.add(a1, l1);
		}
		if (ec.getItemAt(l2) == null) {
			ec.add(a1,  l2);
		}
		if (ec.getItemAt(l3) == null) {
			ec.add(a1, l3);
		}
		if (ec.getItemAt(l4) == null) {
			ec.add(a1, l4);
		}
		
		for (int i = 0; i < 4; i++) {
			a1.act(l1, ec);
			a1.enable();
		}
		
		assertFalse(a1.isAlive());
		
		ec.buryTheDead();
		assertNull(ec.getItemAt(l1));
		assertNull(ec.getItemAt(l2));
		assertNull(ec.getItemAt(l3));
		assertNull(ec.getItemAt(l4));
		
		a1 = new PurePredator('A');
		ec.add(a1, l1);
		Animal a2 = new PredatorPrey('B');
		ec.add(a2, l2);
		a1.enable();
		a2.enable();
		
		a1.act(l1, ec);
		
		assertTrue(a1.isAlive());
		
		assertNull(ec.getItemAt(l1));
		assertEquals('A', ec.getItemAt(l2).getSymbol());
		
		for (int i = 0; i < 3; i++) {
			a1.act(lMove, ec);
			a1.enable();
			//gets the location the animal moved to
			if (ec.getItemAt(l1) != null) {
				lMove = l1;
			} else if (ec.getItemAt(l2) != null) {
				lMove = l2;
			} else if (ec.getItemAt(l3) != null) {
				lMove = l3;
			} else {
				lMove = l4;
			}
		}
		assertTrue(a1.isAlive());
		
		a2 = new PredatorPrey('B');
		ec.add(a2, ec.dueNorth(lMove));
		
		for (int i = 0; i < 3; i++) {
			a1.act(lMove, ec);
			a1.enable();
			//gets the location the animal moved to
			if (ec.getItemAt(l1) != null) {
				lMove = l1;
			} else if (ec.getItemAt(l2) != null) {
				lMove = l2;
			} else if (ec.getItemAt(l3) != null) {
				lMove = l3;
			} else {
				lMove = l4;
			}
		}
		
		assertTrue(a1.isAlive());
		
		
		a2 = new PredatorPrey('B');
		ec.add(a2, ec.dueEast(lMove));
		
		for (int i = 0; i < 3; i++) {
			a1.act(lMove, ec);
			a1.enable();
			//gets the location the animal moved to
			if (ec.getItemAt(l1) != null) {
				lMove = l1;
			} else if (ec.getItemAt(l2) != null) {
				lMove = l2;
			} else if (ec.getItemAt(l3) != null) {
				lMove = l3;
			} else {
				lMove = l4;
			}
		}
		
		assertTrue(a1.isAlive());
		
		a2 = new PredatorPrey('B');
		ec.add(a2, ec.dueSouth(lMove));
		
		for (int i = 0; i < 3; i++) {
			a1.act(lMove, ec);
			a1.enable();
			//gets the location the animal moved to
			if (ec.getItemAt(l1) != null) {
				lMove = l1;
			} else if (ec.getItemAt(l2) != null) {
				lMove = l2;
			} else if (ec.getItemAt(l3) != null) {
				lMove = l3;
			} else {
				lMove = l4;
			}
		}
		
		assertTrue(a1.isAlive());
		
		a2 = new PredatorPrey('B');
		ec.add(a2, ec.dueWest(lMove));
		
		for (int i = 0; i < 3; i++) {
			a1.act(lMove, ec);
			a1.enable();
			//gets the location the animal moved to
			if (ec.getItemAt(l1) != null) {
				lMove = l1;
			} else if (ec.getItemAt(l2) != null) {
				lMove = l2;
			} else if (ec.getItemAt(l3) != null) {
				lMove = l3;
			} else {
				lMove = l4;
			}
		}
		
		assertTrue(a1.isAlive());
		
		
		a2 = new PredatorPrey('B');
		ec.add(a2, ec.dueWest(lMove));
		
		for (int i = 0; i < 3; i++) {
			a1.act(lMove, ec);
			a1.enable();
			//gets the location the animal moved to
			if (ec.getItemAt(l1) != null) {
				lMove = l1;
			} else if (ec.getItemAt(l2) != null) {
				lMove = l2;
			} else if (ec.getItemAt(l3) != null) {
				lMove = l3;
			} else {
				lMove = l4;
			}
		}
		
		assertTrue(a1.isAlive());
		
	}

}
