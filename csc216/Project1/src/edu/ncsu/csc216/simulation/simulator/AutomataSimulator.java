package edu.ncsu.csc216.simulation.simulator;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.simulation.actor.Animal;
import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.actor.PredatorPrey;
import edu.ncsu.csc216.simulation.actor.PurePredator;
import edu.ncsu.csc216.simulation.actor.PurePrey;
import edu.ncsu.csc216.simulation.environment.EcoGrid;
import edu.ncsu.csc216.simulation.environment.Ecosystem;
import edu.ncsu.csc216.simulation.environment.utils.Location;
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * Class for the simulator for the ecosystem grid
 * 
 * @author kbunker
 *
 */
public class AutomataSimulator implements SimulatorInterface {
	/** The size of the simulator */
	private static final int SIZE = 20;

	/** The threshold of the simulator */
	private static final int THRESHOLD = 2;

	/** The error message for the size */
	private static final String SIZE_ERROR_MESSAGE = "File does not follow size requirements";

	/** The error message for the threshold */
	private static final String THRESHOLD_ERROR_MESSAGE = "There must be at least 2 animals in the file";

	/** The array of names */
	private String[] names;

	/** The number of names in the array */
	private int numberOfNames;

	/** The array of symbols */
	private char[] symbols;

	/** The char for the empty space */
	private static final char EMPTY = ' ';
	
	private EcoGrid simpleSystem;

	/**
	 * The constructor for the simulator
	 * 
	 * @param init
	 *            the initial population file name
	 * @param conf
	 *            the name of the configuration file
	 */
	public AutomataSimulator(String init, String conf) {
		simpleSystem = new Ecosystem(SIZE, SIZE);
		readInitFile(init);
		readConfigFile(conf);
		
	}

	/**
	 * The constructor for the simulator if there is no config file and the
	 * default data is to be used
	 * 
	 * @param init
	 *            the initial population file
	 */
	public AutomataSimulator(String init) {
		this(init, null);
	}

	/**
	 * Gets the names of the animals for the simulation
	 * 
	 * @return the array of animal names
	 */
	@Override
	public String[] getNames() {
		String[] nameArray = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			nameArray[i] = "" + symbols[i] + ": " + names[i];
		}
		
		return nameArray;
	}

	/**
	 * gets the grid of the painted location squares
	 * 
	 * @return the 2d painted array that represents the ecosystem
	 */
	@Override
	public PaintedLocation[][] getView() {
		PaintedLocation[][] map = new PaintedLocation[SIZE][SIZE];
		Animal a = null;
		for (int i = 0; i < SIZE; i++) {
			for (int k = 0; k < SIZE; k++) {
				a = simpleSystem.getItemAt(new Location(i, k));
				if (a == null) {
					map[i][k] = new PaintedLocation(i, k, Color.BLACK, EMPTY);
				} else if (a.getSymbol() == symbols[0]) {
					map[i][k] = new PaintedLocation(i, k, Configs.getPredatorColor(), a.getSymbol());
				} else if (a.getSymbol() == symbols[numberOfNames - 1]) {
					map[i][k] = new PaintedLocation(i, k, Configs.getPreyColor(), a.getSymbol());
				} else {
					map[i][k] = new PaintedLocation(i, k, Configs.getMiddleColor(), a.getSymbol());
				}
			}
		}
		
		return map;
	}

	/**
	 * Runs the simulation through a full step
	 */
	@Override
	public void step() {
		simpleSystem.enableTheLiving();
		Animal[][] map = simpleSystem.getMap();
		
		for(int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[i].length; k++) {
				if (map[i][k] != null) {
					map[i][k].act(new Location(i, k), simpleSystem);
				}
			}
		}
		simpleSystem.buryTheDead();

	}

	/**
	 * returns the char array of the symbols for the animals
	 * 
	 * @return the char array of the symbols
	 */
	public char[] getSymbol() {
		return symbols;
	}
	
	private void readInitFile(String fileName) {
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File required to initialize the ecosystem.");
		}
		if (!fileScanner.hasNextInt()) {
			throw new IllegalArgumentException();
		}
		if (fileScanner.hasNextLine()) {

			numberOfNames = fileScanner.nextInt();
			//throw out rest of line
			fileScanner.nextLine();
			names = new String[numberOfNames];
			symbols = new char[numberOfNames];
			if (numberOfNames < THRESHOLD) {
				throw new IllegalArgumentException(THRESHOLD_ERROR_MESSAGE);
			}
			String nameLine;
			for (int i = 0; i < numberOfNames; i++) {
				nameLine = fileScanner.nextLine();
				try {
					readNamesLine(nameLine, i);
				} catch (NoSuchElementException e) {
					throw new IllegalArgumentException();
				}
			}
		}
		for (int l = 0; l < SIZE; l++) {
			if (!fileScanner.hasNext()) {
				throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
			}
			String mapLine = fileScanner.nextLine();
			if (mapLine.length() != simpleSystem.getMap().length) {
				throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
			}
			try {
				for (int i = 0; i < SIZE; i++) {
					char c = mapLine.charAt(i);
					if (c != '.') {
						boolean valid = false;
						for (int k = 0; k < symbols.length; k++) {
							if (c == symbols[k]) {
								valid = true;
								if (c == symbols[0]) {
									simpleSystem.add(new PurePredator(c), new Location(l, i));
								} else if (c == symbols[numberOfNames - 1]) {
									simpleSystem.add(new PurePrey(c), new Location(l, i));
								} else {
									simpleSystem.add(new PredatorPrey(c), new Location(l, i));
								}
							}
						}
						if (!valid) {
							throw new IllegalArgumentException();
						}

					}
				
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IllegalArgumentException();
			}
			

			
			
		}
		if(fileScanner.hasNext()) {
			throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
		}
		if (fileScanner != null) {
			fileScanner.close();
		}

		
		
		
	}
	
	private void readNamesLine(String line, int i) {
		Scanner lineScanner = new Scanner(line);
		if (line == null) {
			lineScanner.close();
			throw new IllegalArgumentException();
		}
		while (lineScanner.hasNext()) {
			String s = lineScanner.next();
			symbols[i] = s.charAt(0);
			names[i] = lineScanner.nextLine().trim();
			
		}
		lineScanner.close();
	}
	
	
	private void readConfigFile(String fileName) {
		Scanner fileScanner = null;
		if (fileName == null) {
			Configs.setToDefaults();
		} else {
		
			try {
				fileScanner = new Scanner(new File(fileName));
			} catch (FileNotFoundException e) {
				//file is ignored and default values used
				Configs.setToDefaults();
			}
		
			if (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				Color[] colors = readColorLine(line);
				int[] starveTimes = readStarveTimeLine(fileScanner.nextLine());
				int[] breedTimes = readBreedTimeLine(fileScanner.nextLine());
				Configs.initConfigs(colors, starveTimes, breedTimes);
			}
		}
		if (fileScanner != null) {
			fileScanner.close();
		}
	}
	
	
	private Color[] readColorLine(String line) {
		if (line == null) {
			throw new IllegalArgumentException();
		}
		Color[] colors = new Color[3];
		Scanner lineScanner = new Scanner(line);
		if (lineScanner.hasNext()) {
			colors[0] = Color.decode("0x" + lineScanner.next());
			colors[1] = Color.decode("0x" + lineScanner.next());
			colors[2] = Color.decode("0x" + lineScanner.next());
			
		}
		lineScanner.close();
		return colors;
	}
	
	private int[] readStarveTimeLine(String line) {
		if (line == null) {
			throw new IllegalArgumentException();
		}
		int[] starveTimes = new int[3];
		Scanner lineScanner = new Scanner(line);
		if (lineScanner.hasNextInt()) {
			starveTimes[0] = lineScanner.nextInt();
			starveTimes[1] = lineScanner.nextInt();
			starveTimes[2] = lineScanner.nextInt();
		}
		lineScanner.close();
		return starveTimes;
	}
	
	private int[] readBreedTimeLine(String line) {
		if (line == null) {
			throw new IllegalArgumentException();
		}
		int[] breedTimes = new int[3];
		Scanner lineScanner = new Scanner(line);
		if (lineScanner.hasNextInt()) {
			breedTimes[0] = lineScanner.nextInt();
			breedTimes[1] = lineScanner.nextInt();
			breedTimes[2] = lineScanner.nextInt();
		}
		
		lineScanner.close();
		return breedTimes;
	}

}
