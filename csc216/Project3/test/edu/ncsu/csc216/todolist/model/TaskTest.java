package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Task class
 * @author Kbunker
 *
 */
public class TaskTest {
	
	/** The first task to test */
	private Task t1;
	/** The second task to test */
	private Task t2;
	/** The first startDate */
	private Date startDate1;
	/** The second startDate */
	private Date startDate2;
	/** The first dueDate */
	private Date dueDate1;
	/** The second dueDate */
	private Date dueDate2;
	/** The category */
	Category c;

	/**
	 * Sets up the Tasks before each test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		GregorianCalendar cal1 = new GregorianCalendar(2016, 10, 29, 13, 30);
		GregorianCalendar cal2 = new GregorianCalendar(2016, 11, 2, 17, 0);
		GregorianCalendar cal3 = new GregorianCalendar(2016, 10, 22, 2, 45);
		GregorianCalendar cal4 = new GregorianCalendar(2016, 11, 7, 20, 22);
		startDate1 = cal1.getTime();
		dueDate1 = cal2.getTime();
		startDate2 = cal3.getTime();
		dueDate2 = cal4.getTime();
		c = new Category("Category title", "Category details", "C1");
		
		t1 = new Task("Task title", "Task details", startDate1, dueDate1, c, "TL1-T1");
		t2 = new Task("Task 2 title", "Task 2 details", startDate2, dueDate2, c, "TL1-T2");
	}

	/**
	 * Tests the hashCode method
	 */
	@Test
	public void testHashCode() {
		assertEquals(t1.hashCode(), t1.hashCode());
		assertNotEquals(t1.hashCode(), t2.hashCode());
	}

	/**
	 * Tests the Task constructor
	 */
	@Test
	public void testTask() {
		assertEquals("Task title", t1.getTitle());
		assertEquals("Task details", t1.getDetails());
		assertEquals(startDate1, t1.getStartDateTime());
		assertEquals(dueDate1, t1.getDueDateTime());
		assertEquals(c, t1.getCategory());
		assertEquals("TL1-T1", t1.getTaskID());
		
		Task t3 = null;
		try {
			t3 = new Task("Task title", "Task details", startDate1, dueDate1, c, "");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(t3);
		}
		try {
			t3 = new Task("Task title", "Task details", startDate1, dueDate1, c, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(t3);
		}
		
		
	}

	/**
	 * Tests setting the title
	 */
	@Test
	public void testSetTitle() {
		assertEquals("Task title", t1.getTitle());
		try {
			t1.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Task title", t1.getTitle());
		}
		try {
			t1.setTitle("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Task title", t1.getTitle());
		}
		
		t1.setTitle("Project 3");
		assertEquals("Project 3", t1.getTitle());
	}


	/**
	 * tests setting the details
	 */
	@Test
	public void testSetDetails() {
		assertEquals("Task details", t1.getDetails());
		t1.setDetails(null);
		assertNull(t1.getDetails());
		t1.setDetails("Project 3 code and Black Box tests");
		assertEquals("Project 3 code and Black Box tests", t1.getDetails());
	}


	/**
	 * Tests setting the startDate
	 */
	@Test
	public void testSetStartDateTime() {
		assertEquals(startDate1, t1.getStartDateTime());
		try {
			t1.setStartDateTime(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(startDate1, t1.getStartDateTime());
		}
		
	}


	/**
	 * Tests setting the dueDate
	 */
	@Test
	public void testSetDueDateTime() {
		assertEquals(dueDate1, t1.getDueDateTime());
		try {
			t1.setDueDateTime(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(dueDate1, t1.getDueDateTime());
		}
	}

	/**
	 * Tests setting the completed time
	 */
	@Test
	public void testGetCompletedDateTime() {
		assertNull(t1.getCompletedDateTime());
		GregorianCalendar cal = new GregorianCalendar(2016, 11, 2, 14, 0);
		Date completedDate = cal.getTime();
		t1.setCompletedDateTime(completedDate);
		t1.setCompleted(false);
		assertEquals(completedDate, t1.getCompletedDateTime());
	}


	/**
	 * Tests setting whether the Task is cvomplete
	 */
	@Test
	public void testIsCompleted() {
		assertFalse(t1.isCompleted());
		t1.setCompleted(true);
		assertTrue(t1.isCompleted());
	}
	


	/**
	 * Tests setting the Category
	 */
	@Test
	public void testSetCategory() {
		assertEquals(c, t1.getCategory());
		try {
			t1.setCategory(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(c, t1.getCategory());
		}
		
	}


	/**
	 * Tests testing the objects equality
	 */
	@Test
	public void testEqualsObject() {
		Task t3 = null;
		assertTrue(t1.equals(t1));
		assertFalse(t1.equals(t2));
		assertFalse(t1.equals(t3));
	}

	/**
	 * Tests comparing the 2 objects
	 */
	@Test
	public void testCompareToTask() {
		assertEquals(0, t1.compareTo(t1));
		assertTrue(t1.compareTo(t2) < 0);
		assertTrue(t2.compareTo(t1) > 0);
	}

}
