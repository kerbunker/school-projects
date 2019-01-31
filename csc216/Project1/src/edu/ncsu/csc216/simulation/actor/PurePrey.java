/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * The class for the lowest level on the food chain
 * 
 * @author kbunker
 *
 */
public class PurePrey extends Animal {
	
	private int age;

	/**
	 * Constructor for the PurePrey Animal that calls to the super class
	 * 
	 * @param symbol
	 *            the symbol for the Animal
	 */
	public PurePrey(char symbol) {
		super(symbol);
		age = 0;
	}

	/**
	 * Gets the color for the symbol on the grid
	 */
	@Override
	public Color getColor() {
		return Configs.getPreyColor();
	}

	/**
	 * Performs an act on a specific location on the grid
	 * 
	 * @param location
	 *            the location on the grid to perform an action
	 * @param grid
	 *            the grid on which the act is performed
	 */
	@Override
	public void act(Location location, EcoGrid grid) {
		while (canAct()) {
			if (!breed(location, grid)) {
				incrementTimeSinceLastBreed();
				move(location, grid);
			}
			age++;
			disable();
		}
		if (age >= Configs.getPreyStarveTime()) {
			die();
		}

	}

	/**
	 * sets how long it is past the time the Animal has last bred
	 * 
	 * @param time
	 *            the time since the Animal has last bred
	 * @return true if the change is performed
	 */
	@Override
	protected boolean pastBreedTime(int time) {
		int allowedTime = Configs.getPreyBreedTime();
		return time >= allowedTime;
	}

	/**
	 * Makes a new baby at the given location
	 * 
	 * @return the new Animal for the grid
	 */
	@Override
	protected Animal makeNewBaby() {
		return new PurePrey(getSymbol());
	}

	/**
	 * Gets the Animal's food chain rank
	 * 
	 * @return the int representaion of the food chain rank
	 */
	@Override
	protected int getFoodChainRank() {
		return Configs.getPreyFoodChainRank();
	}

}
