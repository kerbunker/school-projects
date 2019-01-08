package edu.ncsu.csc216.tracker.ticket;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrackedTicketTest {

	@Test
	public void testTrackedTicketStringStringString() {
		TrackedTicket tt = new TrackedTicket("Ticket Title", "Ticket Submitter", "Ticket Note");
		assertNull(tt.getFlag());
		assertNull(tt.getFlagString());
		assertNull(tt.getOwner());
		assertEquals("New", tt.getStateName());
		assertEquals("Ticket Submitter", tt.getSubmitter());
		
	}

	@Test
	public void testTrackedTicketTrackedTicket() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncrementCounter() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTicketId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStateName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFlag() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFlagString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOwner() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubmitter() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNotes() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetXMLTicket() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCounter() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNotesArray() {
		fail("Not yet implemented");
	}

}
