package edu.ncsu.csc216.simulation.environment;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.actor.PredatorPrey;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Tests the Ecosystem class
 * 
 * @author kbunker
 *
 */
public class EcosystemTest {

	/**
	 * Tests the constructor for the ecosystem
	 */
	@Test
	public void testEcosystem() {
		Ecosystem ec = new Ecosystem(3, 3);
		
		Animal[][] map = ec.getMap();
		assertNull(map[0][0]);
		assertNull(map[0][1]);
		assertNull(map[0][2]);
		assertNull(map[1][0]);
		assertNull(map[1][1]);
		assertNull(map[1][2]);
		assertNull(map[2][0]);
		assertNull(map[2][1]);
		assertNull(map[2][2]);
		
		//checks that the map is the correct size
		Animal a = null;
		try {
			a = map[3][0];
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(a);
		}
		try {
			a = map[0][3];
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(a);
		}
	}

	/**
	 * tests the method to get whether the grid square is empty
	 */
	@Test
	public void testIsEmpty() {
		Animal a = new PredatorPrey('S');
		Ecosystem ec = new Ecosystem(3, 3);
		//test empty location
		Location l = new Location(0, 0);
		assertTrue(ec.isEmpty(l));
		
		//add animal to location and test again
		ec.add(a, l);
		assertFalse(ec.isEmpty(l));
	}

	/**
	 * tests the method to get the item at the given square
	 */
	@Test
	public void testGetItemAt() {
		Animal a = new PredatorPrey('S');
		Ecosystem ec = new Ecosystem(3, 3);
		Location l = new Location(0, 0);
		
		//Test null item
		assertNull(ec.getItemAt(l));
		
		//Test animal at location
		ec.add(a, l);
		Animal aTest = ec.getItemAt(l);
		assertEquals('S', aTest.getSymbol());
	}

	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		Animal a = new PredatorPrey('S');
		Ecosystem ec = new Ecosystem(3, 3);
		Location l = new Location(0, 0);
		
		//Test null item
		assertNull(ec.getItemAt(l));
		ec.remove(l);
		assertNull(ec.getItemAt(l));
		
		//Test animal at location
		ec.add(a, l);
		Animal aTest = ec.getItemAt(l);
		assertEquals('S', aTest.getSymbol());
		ec.remove(l);
		assertNull(ec.getItemAt(l));
	}

	/**
	 * tests the add method
	 */
	@Test
	public void testAdd() {
		Animal a = new PredatorPrey('S');
		Ecosystem ec = new Ecosystem(3, 3);
		Location l = new Location(0, 0);
		
		//adds animal
		ec.add(a, l);
		assertEquals('S', ec.getItemAt(l).getSymbol());
		
		//tests a second location
		Location l3 = new Location(1, 1);
		ec.add(a, l3);
		assertEquals('S', ec.getItemAt(l3).getSymbol());
	}

	/**
	 * Tests the find the first empty neighbor method
	 */
	@Test
	public void testFindFirstEmptyNeighbor() {
		Animal a1 = new PredatorPrey('S');
		Animal a2 = new PredatorPrey('M');
		Animal a3 = new PredatorPrey('A');
		Animal a4 = new PredatorPrey('B');
		Ecosystem ec = new Ecosystem(3, 3);
		Location l = new Location(0, 0);
		Location l2 = new Location(0, 1);
		Location l3 = new Location(2, 1);
		Location l4 = new Location(0, 2);
		Location l5 = new Location(1, 1);
		
		//Tests with all empty neighbors
		Location lTest = ec.findFirstEmptyNeighbor(l, 0);
		assertEquals(0, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l, 1);
		assertEquals(2, lTest.getRow());
		assertEquals(0, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l, 2);
		assertEquals(0, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l, 3);
		assertEquals(1, lTest.getRow());
		assertEquals(0, lTest.getCol());
		
		//Tests with 3 empty neighbors
		ec.add(a1, l);
		lTest = ec.findFirstEmptyNeighbor(l2, 0);
		assertEquals(2, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 1); 
		assertEquals(2, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 2);
		assertEquals(0, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 3);
		assertEquals(1, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		
		//Tests with 2 empty neighbors
		ec.add(a2, l3);
		lTest = ec.findFirstEmptyNeighbor(l2, 0);
		assertEquals(0, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 1); 
		assertEquals(0, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 2);
		assertEquals(0, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 3);
		assertEquals(1, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		//Tests with 1 empty neighbor
		ec.add(a3, l4);
		lTest = ec.findFirstEmptyNeighbor(l2, 0);
		assertEquals(1, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 1); 
		assertEquals(1, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 2);
		assertEquals(1, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.findFirstEmptyNeighbor(l2, 3);
		assertEquals(1, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		//Tests with no empty neighbors
		ec.add(a4, l5);
		lTest = ec.findFirstEmptyNeighbor(l2, 0);
		assertNull(lTest);
		
		lTest = ec.findFirstEmptyNeighbor(l2, 1);
		assertNull(lTest);
		
		lTest = ec.findFirstEmptyNeighbor(l2, 2);
		assertNull(lTest);
		
		lTest = ec.findFirstEmptyNeighbor(l2, 3);
		assertNull(lTest);
		
		
		
		
		
		
	}

	/**
	 * tests the method to get the square due north of the current square
	 */
	@Test
	public void testDueNorth() {
		Ecosystem ec = new Ecosystem(3, 3);
		Location l1 = new Location(0, 0);
		Location l2 = new Location(1, 1);
		Location l3 = new Location(2, 2);
		
		Location lTest = ec.dueNorth(l1);
		assertEquals(2, lTest.getRow());
		assertEquals(0, lTest.getCol());
		
		lTest = ec.dueNorth(l2);
		assertEquals(0, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.dueNorth(l3);
		assertEquals(1, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		//tests null location
		try {
			lTest = ec.dueNorth(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, lTest.getRow());
			assertEquals(2, lTest.getCol());
		}

	}

	/**
	 * Tests the method to get the square due south of the given square
	 */
	@Test
	public void testDueSouth() {
		Ecosystem ec = new Ecosystem(3, 3);
		Location l1 = new Location(0, 0);
		Location l2 = new Location(1, 1);
		Location l3 = new Location(2, 2);
		
		Location lTest = ec.dueSouth(l1);
		assertEquals(1, lTest.getRow());
		assertEquals(0, lTest.getCol());
		
		lTest = ec.dueSouth(l2);
		assertEquals(2, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.dueSouth(l3);
		assertEquals(0, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		//tests null location
		try {
			lTest = ec.dueSouth(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, lTest.getRow());
			assertEquals(2, lTest.getCol());
		}
	}

	/**
	 * tests the method to get the square due west of the given square
	 */
	@Test
	public void testDueWest() {
		Ecosystem ec = new Ecosystem(3, 3);
		Location l1 = new Location(0, 0);
		Location l2 = new Location(1, 1);
		Location l3 = new Location(2, 2);
		
		Location lTest = ec.dueWest(l1);
		assertEquals(0, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		lTest = ec.dueWest(l2);
		assertEquals(1, lTest.getRow());
		assertEquals(0, lTest.getCol());
		
		lTest = ec.dueWest(l3);
		assertEquals(2, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		//tests null location
		try {
			lTest = ec.dueWest(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, lTest.getRow());
			assertEquals(1, lTest.getCol());
		}
		
	}

	/**
	 * tests the method due east of the current square
	 */
	@Test
	public void testDueEast() {
		Ecosystem ec = new Ecosystem(3, 3);
		Location l1 = new Location(0, 0);
		Location l2 = new Location(1, 1);
		Location l3 = new Location(2, 2);
		
		Location lTest = ec.dueEast(l1);
		assertEquals(0, lTest.getRow());
		assertEquals(1, lTest.getCol());
		
		lTest = ec.dueEast(l2);
		assertEquals(1, lTest.getRow());
		assertEquals(2, lTest.getCol());
		
		lTest = ec.dueEast(l3);
		assertEquals(2, lTest.getRow());
		assertEquals(0, lTest.getCol());
		
		//tests null location
		try {
			lTest = ec.dueEast(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(2, lTest.getRow());
			assertEquals(0, lTest.getCol());
		}
	}

	/**
	 * tests the getMap method
	 */
	@Test
	public void testGetMap() {
		Ecosystem ec = new Ecosystem(3, 3);
		Animal a1 = new PredatorPrey('S');
		Animal a2 = new PredatorPrey('M');
		Animal a3 = new PredatorPrey('A');
		Animal a4 = new PredatorPrey('B');
		
		Location l1 = new Location (0, 0);
		Location l2 = new Location (2, 0);
		Location l3 = new Location (1, 1);
		Location l4 = new Location (2, 2);
		
		//Gets empty map
		Animal[][] map = ec.getMap();
		try {
			map[0][0].getSymbol();
			fail();
		} catch (NullPointerException e) {
			ec.isEmpty(new Location(0, 0));
		}
		
		//adds animals to map
		ec.add(a1, l1);
		ec.add(a2, l2);
		ec.add(a3, l3);
		ec.add(a4, l4);
		
		assertEquals('S', map[0][0].getSymbol());
		assertEquals('M', map[2][0].getSymbol());
		assertEquals('A', map[1][1].getSymbol());
		assertEquals('B', map[2][2].getSymbol());
		
		
	}

	/**
	 * tests the enable the living method
	 */
	@Test
	public void testEnableTheLiving() {
		Ecosystem ec = new Ecosystem(3, 3);
		Configs.setToDefaults();
		Animal a1 = new PredatorPrey('S');
		Animal a2 = new PredatorPrey('M');
		Animal a3 = new PredatorPrey('A');
		Animal a4 = new PredatorPrey('B');
		
		Location l1 = new Location (0, 0);
		Location l2 = new Location (2, 0);
		Location l3 = new Location (1, 1);
		Location l4 = new Location (2, 2);
		
		//adds animals to map
		ec.add(a1, l1);
		ec.add(a2, l2);
		ec.add(a3, l3);
		ec.add(a4, l4);
		a1.enable();
		a2.enable();
		a3.enable();
		a4.enable();
		
		a1.disable();
		a3.disable();
		
		a1.act(l1, ec);
		a2.act(l2, ec);
		a3.act(l3, ec);
		a4.act(l4, ec);
		
		assertEquals('S', ec.getItemAt(l1).getSymbol());
		assertEquals('A', ec.getItemAt(l3).getSymbol());
		assertNull(ec.getItemAt(l2));
		assertNull(ec.getItemAt(l4));
		
		ec.enableTheLiving();
		
		a1.act(l1, ec);
		a3.act(l3, ec);
		
		assertNull(ec.getItemAt(l1));
		assertNull(ec.getItemAt(l3));
		
		
	}

	/**
	 * tests the bury the dead method
	 */
	@Test
	public void testBuryTheDead() {

		Ecosystem ec = new Ecosystem(2, 2);
		Configs.setToDefaults();
		Animal a1 = new PredatorPrey('S');
		Animal a2 = new PredatorPrey('M');
		Animal a3 = new PredatorPrey('A');
		Animal a4 = new PredatorPrey('B');
		
		Location l1 = new Location (0, 0);
		Location l2 = new Location (0, 1);
		Location l3 = new Location (1, 0);
		Location l4 = new Location (1, 1);
		
		//adds animals to map
		ec.add(a1, l1);
		ec.add(a2, l2);
		ec.add(a3, l3);
		ec.add(a4, l4);
		
		for (int i = 0; i < 7; i++) {
			a1.act(l1, ec);
			a2.act(l2, ec);
			a3.act(l3, ec);
			a4.act(l4, ec);
			a1.enable();
			a2.enable();
			a3.enable();
			a4.enable();
		}

		
		assertFalse(a1.isAlive());
		assertFalse(a2.isAlive());
		assertFalse(a3.isAlive());
		assertFalse(a4.isAlive());
		
		ec.buryTheDead();
		
		assertNull(ec.getItemAt(l1));
		assertNull(ec.getItemAt(l2));
		assertNull(ec.getItemAt(l3));
		assertNull(ec.getItemAt(l4));
		
		
		
		
		
		
	}

}
