package edu.ncsu.csc216.tracker.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.tracker.command.Command.CommandValue;
import edu.ncsu.csc216.tracker.command.Command.Flag;

/**
 * Tests the Command class along with the CommandValue and Flag inner enum classes
 * @author Katelyn
 *
 */
public class CommandTest {

	/**
	 * Tests the constructor for the Command class, and also tests the getter methods to test the constructor
	 */
	@Test
	public void testCommand() {
		Command c = new Command(CommandValue.POSSESSION, "owner", null, "note Author", "note Text");
		assertEquals(CommandValue.POSSESSION, c.getCommand());
		assertEquals("owner", c.getOwner());
		assertNull(c.getFlag());
		assertEquals("note Author", c.getNoteAuthor());
		assertEquals("note Text", c.getNoteText());
		assertEquals(CommandValue.valueOf("POSSESSION"), c.getCommand());
		try {
			c = new Command(CommandValue.POSSESSION, null, null, "note Author", "note Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CommandValue.POSSESSION, c.getCommand());
			assertEquals("owner", c.getOwner());
			assertNull(c.getFlag());
			assertEquals("note Author", c.getNoteAuthor());
			assertEquals("note Text", c.getNoteText());
			assertEquals(CommandValue.valueOf("POSSESSION"), c.getCommand());
		}
		try {
			c = new Command(CommandValue.POSSESSION, "", null, "note Author", "note Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CommandValue.POSSESSION, c.getCommand());
			assertEquals("owner", c.getOwner());
			assertNull(c.getFlag());
			assertEquals("note Author", c.getNoteAuthor());
			assertEquals("note Text", c.getNoteText());
			assertEquals(CommandValue.valueOf("POSSESSION"), c.getCommand());
		}
		
		c = new Command(CommandValue.ACCEPTED, null, null, "Note Author", "Note Text");
		assertEquals(CommandValue.ACCEPTED, c.getCommand());
		assertNull(c.getOwner());
		assertNull(c.getFlag());
		assertEquals("Note Author", c.getNoteAuthor());
		assertEquals("Note Text", c.getNoteText());
		assertEquals(CommandValue.valueOf("ACCEPTED"), c.getCommand());
		
		c = new Command(CommandValue.PROGRESS, null, null, "Note Author", "Note Text");
		assertEquals(CommandValue.PROGRESS, c.getCommand());
		assertNull(c.getOwner());
		assertNull(c.getFlag());
		assertEquals("Note Author", c.getNoteAuthor());
		assertEquals("Note Text", c.getNoteText());
		assertEquals(CommandValue.valueOf("PROGRESS"), c.getCommand());
		
		c = new Command(CommandValue.FEEDBACK, null, null, "Note Author", "Note Text");
		assertEquals(CommandValue.FEEDBACK, c.getCommand());
		assertNull(c.getOwner());
		assertNull(c.getFlag());
		assertEquals("Note Author", c.getNoteAuthor());
		assertEquals("Note Text", c.getNoteText());
		assertEquals(CommandValue.valueOf("FEEDBACK"), c.getCommand());
		
		c = new Command(CommandValue.CLOSED, null, Flag.DUPLICATE, "Note Author", "Note Text");
		assertEquals(CommandValue.CLOSED, c.getCommand());
		assertNull(c.getOwner());
		assertEquals(Flag.DUPLICATE, c.getFlag());
		assertEquals("Note Author", c.getNoteAuthor());
		assertEquals("Note Text", c.getNoteText());
		assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
		assertEquals(Flag.valueOf("DUPLICATE"), c.getFlag());
		
		c = new Command(CommandValue.CLOSED, null, Flag.INAPPROPRIATE, "Note Author", "Note Text");
		assertEquals(CommandValue.CLOSED, c.getCommand());
		assertNull(c.getOwner());
		assertEquals(Flag.INAPPROPRIATE, c.getFlag());
		assertEquals("Note Author", c.getNoteAuthor());
		assertEquals("Note Text", c.getNoteText());
		assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
		assertEquals(Flag.valueOf("INAPPROPRIATE"), c.getFlag());
		
		c = new Command(CommandValue.CLOSED, null, Flag.RESOLVED, "Note Author", "Note Text");
		assertEquals(CommandValue.CLOSED, c.getCommand());
		assertNull(c.getOwner());
		assertEquals(Flag.RESOLVED, c.getFlag());
		assertEquals("Note Author", c.getNoteAuthor());
		assertEquals("Note Text", c.getNoteText());
		assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
		assertEquals(Flag.valueOf("RESOLVED"), c.getFlag());
		
		try {
			c = new Command(CommandValue.CLOSED, null, null, "Note Author", "Note Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CommandValue.CLOSED, c.getCommand());
			assertNull(c.getOwner());
			assertEquals(Flag.RESOLVED, c.getFlag());
			assertEquals("Note Author", c.getNoteAuthor());
			assertEquals("Note Text", c.getNoteText());
			assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
			assertEquals(Flag.valueOf("RESOLVED"), c.getFlag());
		}
		try {
			c = new Command(null, null, null, "Note Author", "Note Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CommandValue.CLOSED, c.getCommand());
			assertNull(c.getOwner());
			assertEquals(Flag.RESOLVED, c.getFlag());
			assertEquals("Note Author", c.getNoteAuthor());
			assertEquals("Note Text", c.getNoteText());
			assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
			assertEquals(Flag.valueOf("RESOLVED"), c.getFlag());
		}
		try {
			c = new Command(CommandValue.CLOSED, null, Flag.RESOLVED, null, "Note Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CommandValue.CLOSED, c.getCommand());
			assertNull(c.getOwner());
			assertEquals(Flag.RESOLVED, c.getFlag());
			assertEquals("Note Author", c.getNoteAuthor());
			assertEquals("Note Text", c.getNoteText());
			assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
			assertEquals(Flag.valueOf("RESOLVED"), c.getFlag());
		}
		try {
			c = new Command(CommandValue.CLOSED, null, Flag.RESOLVED, "", "Note Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CommandValue.CLOSED, c.getCommand());
			assertNull(c.getOwner());
			assertEquals(Flag.RESOLVED, c.getFlag());
			assertEquals("Note Author", c.getNoteAuthor());
			assertEquals("Note Text", c.getNoteText());
			assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
			assertEquals(Flag.valueOf("RESOLVED"), c.getFlag());
		}
		try {
			c = new Command(CommandValue.CLOSED, null, Flag.RESOLVED, "Note Author", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CommandValue.CLOSED, c.getCommand());
			assertNull(c.getOwner());
			assertEquals(Flag.RESOLVED, c.getFlag());
			assertEquals("Note Author", c.getNoteAuthor());
			assertEquals("Note Text", c.getNoteText());
			assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
			assertEquals(Flag.valueOf("RESOLVED"), c.getFlag());
		}
		try {
			c = new Command(CommandValue.CLOSED, null, Flag.RESOLVED, "Note Author", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CommandValue.CLOSED, c.getCommand());
			assertNull(c.getOwner());
			assertEquals(Flag.RESOLVED, c.getFlag());
			assertEquals("Note Author", c.getNoteAuthor());
			assertEquals("Note Text", c.getNoteText());
			assertEquals(CommandValue.valueOf("CLOSED"), c.getCommand());
			assertEquals(Flag.valueOf("RESOLVED"), c.getFlag());
		}
		
		
	}


}
