package edu.ncsu.csc216.tracker.ticket;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Note class of the Ticket Tracker system
 * @author Kbunker
 *
 */
public class NoteTest {

	/**
	 * Tests the note constructor
	 */
	@Test
	public void testNote() {
		//tests creating a note
		Note n = new Note("Note Author", "Note Text");
		assertEquals("Note Author", n.getNoteAuthor());
		assertEquals("Note Text", n.getNoteText());
		String[] s = n.getNoteArray();
		assertEquals("Note Author", s[0]);
		assertEquals("Note Text", s[1]);
		
		//Tests the invalid NoteAuthor and NoteText inputs
		try {
			n = new Note(null, "Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Note Author", n.getNoteAuthor());
			assertEquals("Note Text", n.getNoteText());
		}
		try {
			n = new Note("", "Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Note Author", n.getNoteAuthor());
			assertEquals("Note Text", n.getNoteText());
		}
		try {
			n = new Note("Author", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Note Author", n.getNoteAuthor());
			assertEquals("Note Text", n.getNoteText());
		}
		try {
			n = new Note("Author", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Note Author", n.getNoteAuthor());
			assertEquals("Note Text", n.getNoteText());
		}
		
		
	}

	/**
	 * Tests the getNoteAutor method
	 */
	@Test
	public void testGetNoteAuthor() {
		Note n = new Note("Note Author", "Note Text");
		assertEquals("Note Author", n.getNoteAuthor());
		assertEquals("Note Text", n.getNoteText());
		
	}

	/**
	 * Tests the getNoteText method
	 */
	@Test
	public void testGetNoteText() {
		Note n = new Note("Note Author", "Note Text");
		assertEquals("Note Author", n.getNoteAuthor());
		assertEquals("Note Text", n.getNoteText());
		
	}

	/**
	 * Tests getting a note array
	 */
	@Test
	public void testGetNoteArray() {
		Note n = new Note("Note Author", "Note Text");
		assertEquals("Note Author", n.getNoteAuthor());
		assertEquals("Note Text", n.getNoteText());
		String[] s = n.getNoteArray();
		assertEquals("Note Author", s[0]);
		assertEquals("Note Text", s[1]);
		
	}

}
