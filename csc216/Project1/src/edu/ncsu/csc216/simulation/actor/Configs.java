/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

/**
 * Configures all the data for the animals on the grid to utilize in their
 * movements
 * 
 * @author kbunker
 *
 */
public class Configs {

	/** Default food chain ranks */
	private static final int[] DEFAULT_FOOD_CHAIN_RANK = { 0, 10, 20 };

	/** Default colors for grid animals */
	private static final Color[] DEFAULT_COLORS = { Color.GREEN, Color.ORANGE, Color.RED };

	/** Player assigned animal grid colors */
	private static Color[] PLAYER_COLORS;

	/** Default steps until an animal starves */
	private static final int[] DEFAULT_STARVE_TIME = { 10, 6, 5 };

	/** Player assigned values for starve time */
	private static int[] STARVE_TIME;

	/** The default steps until an animal can try to breed */
	private static final int[] DEFAULT_BREED_TIME = { 1, 7, 15 };

	/** Player assigned steps until an animal can breed */
	private static int[] BREED_TIME;


	/**
	 * Sets the initial configurations
	 * 
	 * @param colors
	 *            the colors for the animal icons on the grid
	 * @param starveTime
	 *            the number of steps an animal can take until it starves
	 * @param breedTime
	 *            the number of steps the animal must take before it can try to
	 *            breed
	 */
	public static void initConfigs(Color[] colors, int[] starveTime, int[] breedTime) {
		PLAYER_COLORS = colors;
		STARVE_TIME = starveTime;
		BREED_TIME = breedTime;

	}

	/**
	 * Sets the configs to default if no player given data is supplied
	 */
	public static void setToDefaults() {

		PLAYER_COLORS = DEFAULT_COLORS;
		STARVE_TIME = DEFAULT_STARVE_TIME;
		BREED_TIME = DEFAULT_BREED_TIME;

	}

	/**
	 * Gets the color for the lowest level animal
	 * 
	 * @return the color for the prey animals
	 */
	public static Color getPreyColor() {
		return PLAYER_COLORS[0];
	}

	/**
	 * Gets the color assigned to the animals in the middle of the food chain
	 * 
	 * @return the middle animal's grid color
	 */
	public static Color getMiddleColor() {
		return PLAYER_COLORS[1];
	}

	/**
	 * Gets the color of the Top level animal on the food chain
	 * 
	 * @return the predator's grid color
	 */
	public static Color getPredatorColor() {
		return PLAYER_COLORS[2];
	}

	/**
	 * Gets the rank of the lowest level on the food chain
	 * 
	 * @return the prey's food chain rank
	 */
	public static int getPreyFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[0];
	}

	/**
	 * Gets the rank of the middle animal on the food chain's rank
	 * 
	 * @return the middle animal's rank
	 */
	public static int getMiddleFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[1];
	}

	/**
	 * Gets the rank of the animal highest on the food chain
	 * 
	 * @return the predator's rank
	 */
	public static int getPredatorFoodChainRank() {
		return DEFAULT_FOOD_CHAIN_RANK[2];
	}

	/**
	 * Gets the number of steps a low level animal can take before it starves
	 * and dies
	 * 
	 * @return the prey's starve steps
	 */
	public static int getPreyStarveTime() {
		return STARVE_TIME[0];
	}

	/**
	 * Gets the number of steps a middle animal can take after it has eaten
	 * before it starves and dies
	 * 
	 * @return the middle animal's starve time
	 */
	public static int getMiddleStarveTime() {
		return STARVE_TIME[1];
	}

	/**
	 * Gets the number of steps a high level animal can take after it has eaten
	 * before it starves and dies
	 * 
	 * @return the predator's starve steps
	 */
	public static int getPredatorStarveTime() {
		return STARVE_TIME[2];
	}

	/**
	 * Gets the number of steps prey must take before they can breed
	 * 
	 * @return the prey breed steps
	 */
	public static int getPreyBreedTime() {
		return BREED_TIME[0];
	}

	/**
	 * Gets the number of steps a middle rank animal must take before it can try
	 * to breed
	 * 
	 * @return the number of middle breed steps
	 */
	public static int getMiddleBreedTime() {
		return BREED_TIME[1];
	}

	/**
	 * Gets the number of steps a Predator must take before it can try to breed
	 * 
	 * @return the predator's breed steps
	 */
	public static int getPredatorBreedTime() {
		return BREED_TIME[2];
	}

}
