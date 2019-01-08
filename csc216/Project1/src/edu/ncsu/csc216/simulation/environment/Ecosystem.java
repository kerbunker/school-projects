/**
 * 
 */
package edu.ncsu.csc216.simulation.environment;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.environment.utils.Location;

/**
 * The class for the ecosystem the program takes place on
 * 
 * @author kbunker
 *
 */
public class Ecosystem implements EcoGrid {

	/** The max number of rows for the Grid */
	private int maxRows;

	/** The max number of columns for the grid */
	private int maxCols;
	
	/** The 2d array of animals that make up the grid */
	private Animal[][] map;

	/**
	 * Constructor for the Ecosystem
	 * 
	 * @param maxRows
	 *            sets the max number of rows for the grid
	 * @param maxCols
	 *            sets the max number of columns for the frid
	 */
	public Ecosystem(int maxRows, int maxCols) {
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		map = new Animal[maxRows][maxCols];
	}

	/**
	 * Returns whether the grid at a specific point is empty
	 * 
	 * @param location
	 *            the location to find whether it is empty
	 * @return true if the location is empty
	 */
	@Override
	public boolean isEmpty(Location location) {
		return (map[location.getRow()][location.getCol()] == null);
	}

	/**
	 * Returns the Animal at the given location
	 * 
	 * @param location
	 *            location to find the Animal at
	 * @return the Animal at the specified position
	 */
	@Override
	public Animal getItemAt(Location location) {
		return map[location.getRow()][location.getCol()];
	}

	/**
	 * Removes the item at the given location
	 * 
	 * @param place
	 *            the location to remove the item
	 */
	@Override
	public void remove(Location place) {
		map[place.getRow()][place.getCol()] = null;

	}

	/**
	 * Adds a given animal at the given location
	 * 
	 * @param animal
	 *            the animal to add at the location
	 * @param location
	 *            the location at which the animal is to be added
	 */
	@Override
	public void add(Animal animal, Location location) {
		//if (animal == null || location.getRow() >= maxRows || location.getCol() >= maxCols) {
		//	throw new IllegalArgumentException();
		//}
		
		map[location.getRow()][location.getCol()] = animal;

	}

	/**
	 * Looks at the spaces around the location to find the next empty neighbor
	 * to the location given, starting in the direction given by the
	 * startDirection
	 * 
	 * @param position
	 *            the location to look around
	 * @param startDirection
	 *            the first direction to look in to find the first empty
	 *            neighbor
	 * @return the location that is the first empty neighbor
	 */
	@Override
	public Location findFirstEmptyNeighbor(Location position, int startDirection) {
		if (startDirection == 0) {
			if (isEmpty(dueWest(position))) {
				return dueWest(position);
			} else if (isEmpty(dueNorth(position))) {
				return dueNorth(position);
			} else if (isEmpty(dueEast(position))) {
				return dueEast(position);
			} else if (isEmpty(dueSouth(position))) {
				return dueSouth(position);
			} else {
				return null;
			}
		} else if (startDirection == 1) {
			if (isEmpty(dueNorth(position))) {
				return dueNorth(position);
			} else if (isEmpty(dueEast(position))) {
				return dueEast(position);
			} else if (isEmpty(dueSouth(position))) {
				return dueSouth(position);
			} else if (isEmpty(dueWest(position))) {
				return dueWest(position);
			} else {
				return null;
			}
		} else if (startDirection == 2) {
			if (isEmpty(dueEast(position))) {
				return dueEast(position);
			} else if (isEmpty(dueSouth(position))) {
				return dueSouth(position);
			} else if (isEmpty(dueWest(position))) {
				return dueWest(position);
			} else if (isEmpty(dueNorth(position))) {
				return dueNorth(position);
			} else {
				return null;
			}
		} else if (startDirection == 3) {
			if (isEmpty(dueSouth(position))) {
				return dueSouth(position);
			} else if (isEmpty(dueWest(position))) {
				return dueWest(position);
			} else if (isEmpty(dueNorth(position))) {
				return dueNorth(position);
			} else if (isEmpty(dueEast(position))) {
				return dueEast(position);
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * finds the location to the north of the given location
	 * 
	 * @param location
	 *            the location to find the northern location of
	 * @return the location of the northern neighbor to the given location
	 */
	@Override
	public Location dueNorth(Location location) {
		if (location == null) {
			throw new IllegalArgumentException();
		}
		Location l;
		if (location.getRow() == 0) {
			l = new Location(maxRows - 1, location.getCol());
		} else {
			l = new Location(location.getRow() - 1, location.getCol());
		}
		
		return l;
	}

	/**
	 * Finds the location to the South of the location given
	 * 
	 * @param location
	 *            the location to find the southern neighbor of
	 * @return the location to the south of the given location
	 */
	@Override
	public Location dueSouth(Location location) {
		if (location == null) {
			throw new IllegalArgumentException();
		}
		Location l;
		if (location.getRow() == maxRows - 1) {
			l = new Location(0, location.getCol());
		} else {
			l = new Location(location.getRow() + 1, location.getCol());
		}
		
		return l;
	}

	/**
	 * Finds the location to the West of the location given
	 * 
	 * @param location
	 *            the location to find the western neighbor of
	 * @return the location to the west of the given location
	 */
	@Override
	public Location dueWest(Location location) {
		if (location == null) {
			throw new IllegalArgumentException();
		}
		Location l;
		if (location.getCol() == 0) {
			l = new Location(location.getRow(), maxCols - 1);
		} else {
			l = new Location(location.getRow(), location.getCol() - 1);
		}
		
		return l;
	}

	/**
	 * Finds the location to the East of the location given
	 * 
	 * @param location
	 *            the location to find the Eastern neighbor of
	 * @return the location to the East of the given location
	 */
	@Override
	public Location dueEast(Location location) {
		if (location == null) {
			throw new IllegalArgumentException();
		}
		Location l;
		if (location.getCol() == maxCols - 1) {
			l = new Location(location.getRow(), 0);
		} else {
			l = new Location(location.getRow(), location.getCol() + 1);
		}
		
		return l;
	}

	/**
	 * returns the 2d array of the grid
	 * 
	 * @return the 2d array of animals that make up the grid
	 */
	@Override
	public Animal[][] getMap() {
		return map;
	}

	/**
	 * enables all animals that are currently living
	 */
	@Override
	public void enableTheLiving() {
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] != null && map[i][k].isAlive()) {
					map[i][k].enable();
				}
			}
		}

	}

	/**
	 * Removes all the animals that are listed as dead on the map
	 */
	@Override
	public void buryTheDead() {
		for (int i = 0; i < map.length; i++) {
			for (int k = 0; k < map[i].length; k++) {
				if (map[i][k] != null && !map[i][k].isAlive()) {
					map[i][k] = null;
				}
			}
		}

	}

}
