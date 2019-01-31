package edu.ncsu.csc216.simulation.actor;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

/**
 * Tests the configs class
 * 
 * @author kbunker
 *
 */
public class ConfigsTest {

	/**
	 * tests the initial configs method
	 */
	@Test
	public void testInitConfigs() {
		Color[] colors = {Color.RED, Color.BLUE, Color.CYAN};
		int[] starve = {1, 3, 5};
		int[] breed = {2, 4, 6};
		Configs.initConfigs(colors, starve, breed);
		
		assertEquals(Color.RED, Configs.getPreyColor());
		assertEquals(Color.BLUE, Configs.getMiddleColor());
		assertEquals(Color.CYAN, Configs.getPredatorColor());
		assertEquals(1, Configs.getPreyStarveTime());
		assertEquals(3, Configs.getMiddleStarveTime());
		assertEquals(5, Configs.getPredatorStarveTime());
		assertEquals(2, Configs.getPreyBreedTime());
		assertEquals(4, Configs.getMiddleBreedTime());
		assertEquals(6, Configs.getPredatorBreedTime());
	}

	/**
	 * tests the method to set the configs file to the default values
	 */
	@Test
	public void testSetToDefaults() {
		Configs.setToDefaults();
		assertEquals(Color.GREEN, Configs.getPreyColor());
		assertEquals(Color.ORANGE, Configs.getMiddleColor());
		assertEquals(Color.RED, Configs.getPredatorColor());
		assertEquals(10, Configs.getPreyStarveTime());
		assertEquals(6, Configs.getMiddleStarveTime());
		assertEquals(5, Configs.getPredatorStarveTime());
		assertEquals(1, Configs.getPreyBreedTime());
		assertEquals(7, Configs.getMiddleBreedTime());
		assertEquals(15, Configs.getPredatorBreedTime());
	}







	/**
	 * tests the method to get the food chain rank of the prey animals
	 */
	@Test
	public void testGetPreyFoodChainRank() {
		Configs.setToDefaults();
		assertEquals(0, Configs.getPreyFoodChainRank());
	}

	/**
	 * tests the get middle food chain rank
	 */
	@Test
	public void testGetMiddleFoodChainRank() {
		Configs.setToDefaults();
		assertEquals(10, Configs.getMiddleFoodChainRank());
	}

	/**
	 * tests the method to get the predator food chain rank
	 */
	@Test
	public void testGetPredatorFoodChainRank() {
		Configs.setToDefaults();
		assertEquals(20, Configs.getPredatorFoodChainRank());
	}




}
