package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Tests the pureprey class
 * @author Kbunker
 *
 */
public class PurePreyTest {

	/**
	 * Tests the getColor() method
	 */
	@Test
	public void testGetColor() {
		Animal a = new PurePrey('A');
		Configs.setToDefaults();
		assertEquals(Color.GREEN, a.getColor());

	}

	/**
	 * Tests the act() method
	 */
	@Test
	public void testAct() {
		Animal a1 = new PurePrey('A');
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
		a1.act(lMove, ec);
		a1.enable();
		
		assertEquals('A', ec.getItemAt(lMove).getSymbol());
		assertEquals('A', ec.getItemAt(ec.dueWest(lMove)).getSymbol());
		
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
		
		for (int i = 0; i < 8; i++) {
			a1.act(l1, ec);
			a1.enable();
		}
		
		assertFalse(a1.isAlive());

	}

}
