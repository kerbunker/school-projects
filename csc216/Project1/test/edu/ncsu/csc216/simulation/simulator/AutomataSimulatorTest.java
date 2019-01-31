package edu.ncsu.csc216.simulation.simulator;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.simulation.actor.Configs;
import edu.ncsu.csc216.simulation.environment.utils.PaintedLocation;

/**
 * Tests the automata simulator class
 * 
 * @author kbunker
 *
 */
public class AutomataSimulatorTest {
	
	private String initFile = "test-files/ecosystem_test.txt";
	
	private String configFile = "test-files/configs_test.txt";

	/**
	 * tests the constructor with two string parameters
	 */
	@Test
	public void testAutomataSimulatorStringString() {
		AutomataSimulator sim = new AutomataSimulator(initFile, configFile);
		
		String[] names = sim.getNames();
		assertEquals("O: Great Gray Owl", names[0]);
		assertEquals("M: Mouse", names[1]);
		assertEquals("F: Frog", names[2]);
		assertEquals("I: Insect", names[3]);
		
		char[] symbols = sim.getSymbol();
		assertEquals('O', symbols[0]);
		assertEquals('M', symbols[1]);
		assertEquals('F', symbols[2]);
		assertEquals('I', symbols[3]);
		
		assertEquals(Color.RED, Configs.getPreyColor());
		assertEquals(Color.GREEN, Configs.getMiddleColor());
		assertEquals(Color.BLUE, Configs.getPredatorColor());
		
		assertEquals(8, Configs.getPreyStarveTime());
		assertEquals(7, Configs.getMiddleStarveTime());
		assertEquals(4, Configs.getPredatorStarveTime());
		
		assertEquals(2, Configs.getPreyBreedTime());
		assertEquals(6, Configs.getMiddleBreedTime());
		assertEquals(10, Configs.getPredatorBreedTime());
		
		try {
			sim = new AutomataSimulator(" ", " ");
		} catch (IllegalArgumentException e) {
			assertEquals("File required to initialize the ecosystem.", e.getMessage());
		}
	}

	/**
	 * tests the method with one string parameter
	 */
	@Test
	public void testAutomataSimulatorString() {
		AutomataSimulator sim = new AutomataSimulator(initFile);
		
		String[] names = sim.getNames();
		assertEquals("O: Great Gray Owl", names[0]);
		assertEquals("M: Mouse", names[1]);
		assertEquals("F: Frog", names[2]);
		assertEquals("I: Insect", names[3]);
		
		char[] symbols = sim.getSymbol();
		assertEquals('O', symbols[0]);
		assertEquals('M', symbols[1]);
		assertEquals('F', symbols[2]);
		assertEquals('I', symbols[3]);
		
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
	 * tests the get view method
	 */
	@Test
	public void testGetView() {
		AutomataSimulator sim = new AutomataSimulator(initFile);
		
		PaintedLocation[][] map = sim.getView();
		
		assertEquals(20, map.length);
		assertEquals(20, map[0].length);
		
		PaintedLocation pl1 = map[0][2];
		assertEquals('O', pl1.getSymbol());
		assertEquals(Color.RED, pl1.getColor());
		assertEquals(0, pl1.getRow());
		assertEquals(2, pl1.getCol());
		
		pl1 = map[0][0];
		assertEquals(' ', pl1.getSymbol());
		assertEquals(Color.BLACK, pl1.getColor());
		assertEquals(0, pl1.getRow());
		assertEquals(0, pl1.getCol());
		
		pl1 = map[7][9];
		assertEquals('I', pl1.getSymbol());
		assertEquals(Color.GREEN, pl1.getColor());
		assertEquals(7, pl1.getRow());
		assertEquals(9, pl1.getCol());
		
		pl1 = map[map.length - 1][map[0].length - 1];
		assertEquals('M', pl1.getSymbol());
		assertEquals(Color.ORANGE, pl1.getColor());
		assertEquals(19, pl1.getRow());
		assertEquals(19, pl1.getCol());
		
	}

	/**
	 * tests the step method
	 */
	@Test
	public void testStep() {
		AutomataSimulator sim = new AutomataSimulator(initFile);
		
		PaintedLocation[][] map = sim.getView();
		
		assertEquals(20, map.length);
		assertEquals(20, map[0].length);
		
		sim.step();
		map = sim.getView();
		
		assertEquals(' ', map[1][0].getSymbol());
		assertEquals(' ', map[5][19].getSymbol());
	}


}
