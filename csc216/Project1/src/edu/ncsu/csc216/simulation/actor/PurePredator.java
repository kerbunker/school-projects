/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Class for the highest ranking Animal in the food chain
 * 
 * @author kbunker
 */
public class PurePredator extends Animal {

	/**
	 * Constructor for the PurePredator Animal that calls to the super class
	 * 
	 * @param symbol
	 *            the symbol for the Animal
	 */
	public PurePredator(char symbol) {
		super(symbol);
	}

	/**
	 * Gets the color for the symbol on the grid
	 */
	@Override
	public Color getColor() {
		return Configs.getPredatorColor();
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
		boolean acted = false;
		while (canAct()) {
			if (eat(location, grid)) {
				incrementTimeSinceLastBreed();
				acted = true;
			} else if (breed(location, grid) && !acted) {
				incrementTimeSinceLastMeal();
				acted = true;
			} else if (!acted) {
				incrementTimeSinceLastBreed();
				incrementTimeSinceLastMeal();
				move(location, grid);
				acted = true;
			}
			
			if (getTimeSinceLastMeal() >= Configs.getPredatorStarveTime()) {
				die();
			}
			disable();
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
		int allowedTime = Configs.getPredatorBreedTime();
		return time >= allowedTime;

	}

	/**
	 * Makes a new baby at the given location
	 * 
	 * @return the new Animal for the grid
	 */
	@Override
	protected Animal makeNewBaby() {
		return new PurePredator(getSymbol());
	}

	/**
	 * Gets the Animal's food chain rank
	 * 
	 * @return the int representaion of the food chain rank
	 */
	@Override
	protected int getFoodChainRank() {
		return Configs.getPredatorFoodChainRank();
	}

}
