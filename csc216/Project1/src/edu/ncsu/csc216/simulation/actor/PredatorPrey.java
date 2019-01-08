/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Class for the middle Animal on the food chain
 * 
 * @author kbunker
 *
 */
public class PredatorPrey extends Animal {

	/**
	 * Constructor for the Animal with the Symbol for the Animal given in the
	 * parameter
	 * 
	 * @param symbol
	 *            the Animal's symbol on the grid
	 */
	public PredatorPrey(char symbol) {
		super(symbol);
		disable();
	}

	@Override
	public Color getColor() {
		return Configs.getMiddleColor();
	}

	/**
	 * The Animal acts in the location given by the parameters
	 * 
	 * @param location
	 *            the location the Animal acts on
	 * @param grid
	 *            the grid the Animal is acting on
	 */
	@Override
	public void act(Location location, EcoGrid grid) {
		boolean acted = false;
		while (canAct()) {
			if (getTimeSinceLastBreed() >= Configs.getMiddleBreedTime()) {
				if (breed(location, grid)) {
					incrementTimeSinceLastMeal();
					acted = true;
					disable();
				}
			} else if (eat(location, grid) && !acted) {
				incrementTimeSinceLastBreed();
				acted = true;
			} else if (!acted) {
				incrementTimeSinceLastBreed();
				incrementTimeSinceLastMeal();
				move(location, grid);
				acted = true;
			}
			
			if (getTimeSinceLastMeal() >= Configs.getMiddleStarveTime()) {
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
		int allowedTime = Configs.getMiddleBreedTime();
		return time >= allowedTime;

	}

	/**
	 * Makes a new baby at the given location
	 * 
	 * @return the new Animal for the grid
	 */
	@Override
	protected Animal makeNewBaby() {
		Animal a = new PredatorPrey(getSymbol());
				a.disable();
		return a;
	}

	/**
	 * Gets the Animal's food chain rank
	 * 
	 * @return the int representaion of the food chain rank
	 */
	@Override
	protected int getFoodChainRank() {
		return Configs.getMiddleFoodChainRank();
	}

}
