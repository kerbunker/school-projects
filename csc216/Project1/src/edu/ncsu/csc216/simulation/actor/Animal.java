/**
 * 
 */
package edu.ncsu.csc216.simulation.actor;

import java.awt.Color;
import java.util.Random;

import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * Abstract class for the Animal objects
 * 
 * @author kbunker
 *
 */
public abstract class Animal {

	/** Time since the animal last ate */
	private int timeSinceLastMeal;

	/** Time since the animal last bred */
	private int timeSinceLastBreed;

	/** Keeps track of if an Animal can act during this step */
	private boolean canActThisStep;

	/** The symbol for the Animal on the grid */
	private char symbol;

	/** Keeps track of whether the Animal is alive */
	private boolean alive;

	/** Seed for the the randomGenerator */
	private static int seed;

	/** The random number generator for which direction the Animal moves in */
	private static Random randomGenerator;

	/**
	 * Animal constructor. Takes in symbol parameter for the symbol the Animal
	 * has on the map
	 * 
	 * @param symbol
	 *            the symbol for the Animal on the map
	 */
	public Animal(char symbol) {
		this.symbol = symbol;
		seed = 500;
		timeSinceLastMeal = 0;
		timeSinceLastBreed = 0;
		alive = true;
		canActThisStep = false;
		randomGenerator = new Random(seed);
	}

	/**
	 * Sets the random seed for testing purposes
	 * 
	 * @param seed
	 *            the seed to set for tests
	 */
	public static void setRandomSeed(int seed) {
		randomGenerator = new Random(seed);

	}

	/**
	 * Returns the symbol of the Animal for the grid
	 * 
	 * @return the Animal's symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Returns whether the Animal is currently alive
	 * 
	 * @return true if the Animal is alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Enables the Animal to act on this turn
	 */
	public void enable() {
		canActThisStep = true;
	}

	/**
	 * Disabled the Animal from acting
	 */
	public void disable() {
		canActThisStep = false;
	}

	/**
	 * Sets the Animal as dead
	 */
	protected void die() {
		alive = false;
		disable();
	}

	/**
	 * Returns whether the Animal can act during the current turn
	 * 
	 * @return true if the Animal can act
	 */
	protected boolean canAct() {
		return canActThisStep;
	}

	/**
	 * Returns the amount of time since the Animal has last bred.
	 * 
	 * @return the number of steps taken since the Animal has last bred
	 */
	protected int getTimeSinceLastBreed() {
		return timeSinceLastBreed;
	}

	/**
	 * Returns the amount of time since the Animal has last eaten
	 * 
	 * @return the number of steps since the Animal has eaten
	 */
	protected int getTimeSinceLastMeal() {
		return timeSinceLastMeal;
	}

	/**
	 * Adds another step on the number of steps since the animal has last eaten
	 */
	protected void incrementTimeSinceLastMeal() {
		timeSinceLastMeal++;
	}

	/**
	 * Adds to the number of steps since the Animal has last bred
	 */
	protected void incrementTimeSinceLastBreed() {
		timeSinceLastBreed++;
	}

	/**
	 * Breeds into an open location given by the location on the grid
	 * 
	 * @param location
	 *            the location in which the Animal will breed
	 * @param grid
	 *            the grid on which the Animal will breed
	 * @return true if the breeding was successful
	 */
	protected boolean breed(Location location, EcoGrid grid) {
		if (!pastBreedTime(timeSinceLastBreed)) {
			return false;
		}
		Location free = grid.findFirstEmptyNeighbor(location, 0);
		if (free != null) {
			Animal baby = makeNewBaby();
			baby.disable();
			grid.add(baby, free);
			timeSinceLastBreed = 0;
			disable();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves into an adjoining square on the grid, given by the grid and
	 * location parameters
	 * 
	 * @param location
	 *            the location on the grid which the Animal will move into
	 * @param grid
	 *            the grid on which the Animal is moving
	 */
	protected void move(Location location, EcoGrid grid) {
		int startDirection = randomGenerator.nextInt(4);
		Location l = grid.findFirstEmptyNeighbor(location, startDirection);
		if (l != null) {
			grid.add(this, l);
			grid.remove(location);
			disable();
		}
	}

	/**
	 * The Animal tries to eat a lower animal in the adjoning square on the
	 * grid, given by the grid and location parameters
	 * 
	 * @param location
	 *            the location which the Animal is trying to eat in
	 * @param grid
	 *            the grid which this action is being performed on
	 * @return true if the meal was successful
	 */
	protected boolean eat(Location location, EcoGrid grid) {
		int rank = this.getFoodChainRank();
		Location l;
		Animal a;
		int aRank;
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				l = grid.dueWest(location);
			} else if (i == 1) {
				l = grid.dueNorth(location);
			} else if (i == 2) {
				l = grid.dueEast(location);
			} else {
				l = grid.dueSouth(location);
			}
			a = grid.getItemAt(l);
			if (a != null) {
				aRank = a.getFoodChainRank();
				if (aRank < rank) {
					grid.add(this, l);
					grid.remove(location);
					timeSinceLastMeal = 0;
					disable();
					return true;
				}			
			}

			
		}
		return false;
	}

	/**
	 * Gets the color of the square for the grid
	 * 
	 * @return the Color associated with the Animal
	 */
	public abstract Color getColor();

	/**
	 * The Animal acts on a particular location on the grid, given by the
	 * location and grid parameters
	 * 
	 * @param location
	 *            the location on the grid the Animal is acting on
	 * @param grid
	 *            the grid the animal is acting on
	 */
	public abstract void act(Location location, EcoGrid grid);

	/**
	 * Returns whether the number of steps have been taken to be past the
	 * allowed breed time
	 * 
	 * @param time
	 *            the time since the Animal last bred
	 * @return true if it is past breed time
	 */
	protected abstract boolean pastBreedTime(int time);

	/**
	 * Makes a new Animal to be added to the grid
	 * 
	 * @return an Animal that is added to the grid
	 */
	protected abstract Animal makeNewBaby();

	/**
	 * Gets the food chain rank of the Animal
	 * 
	 * @return the int representation of the FoodChainRank
	 */
	protected abstract int getFoodChainRank();

}
