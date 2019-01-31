package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the TaskList class
 * @author Kbunker
 *
 */
public class TaskListTest {
	
	/** List for the TaskList tests */
	private TaskList list;

	/**
	 * Sets up the TaskList before each test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new TaskList("List 1", "TL1");
	}

	/**
	 * Tests the tasklist constructor
	 */
	@Test
	public void testTaskList() {
		assertEquals("List 1", list.getName());
		assertEquals("TL1", list.getTaskListID());
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		try {
			list = new TaskList("List 1", "");
		} catch (IllegalArgumentException e) {
			assertEquals("TL1", list.getTaskListID());
		}
		try {
			list = new TaskList("List 1", null);
		} catch (IllegalArgumentException e) {
			assertEquals("TL1", list.getTaskListID());
		}
	}


	/**
	 * tests setting the name
	 */
	@Test
	public void testSetName() {
		try {
			list.setName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("List 1", list.getName());
		}
		try {
			list.setName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("List 1", list.getName());
		}
		
	}


	/**
	 * Tests adding tasks
	 */
	@Test
	public void testAddTask() {
		assertFalse(list.addTask(null, null, null, null, null));
		
		GregorianCalendar cal1 = new GregorianCalendar(2016, 10, 20, 14, 45);
		GregorianCalendar cal2 = new GregorianCalendar(2016, 11, 2, 21, 0);
		GregorianCalendar cal3 = new GregorianCalendar(2016, 11, 9, 17, 0);
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();
		Date date3 = cal3.getTime();
		Category c = new Category("C1", "Homework", "Category for Homework tasks");
		
		assertTrue(list.addTask("Project 3", "Project 3 code and bbt", date1, date2, c));
		assertTrue(list.addTask("Final Exam", "Study for final exam", date2, date3, c));
		assertTrue(list.addTask("Task 3 title", "Task 3 description", date1, date2, c));
		
		assertEquals("TL1-T1", ((Task) list.getTaskAt(0)).getTaskID());
		assertEquals("TL1-T3", ((Task) list.getTaskAt(1)).getTaskID());
		assertEquals("TL1-T2", ((Task) list.getTaskAt(2)).getTaskID());
		assertEquals("Project 3", ((Task) list.getTaskAt(0)).getTitle());
		assertEquals("Task 3 title", ((Task) list.getTaskAt(1)).getTitle());
		assertEquals("Final Exam", ((Task) list.getTaskAt(2)).getTitle());
		assertEquals("Project 3 code and bbt", ((Task) list.getTaskAt(0)).getDetails());
		assertEquals("Task 3 description", ((Task) list.getTaskAt(1)).getDetails());
		assertEquals("Study for final exam", ((Task) list.getTaskAt(2)).getDetails());
	}

	/**
	 * Tests finding the index of an ID
	 */
	@Test
	public void testIndexOf() {
		assertEquals(-1, list.indexOf(null));
		assertEquals(-1, list.indexOf("TL1-T1"));
		
		GregorianCalendar cal1 = new GregorianCalendar(2016, 10, 20, 14, 45);
		GregorianCalendar cal2 = new GregorianCalendar(2016, 11, 2, 21, 0);
		GregorianCalendar cal3 = new GregorianCalendar(2016, 11, 9, 17, 0);
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();
		Date date3 = cal3.getTime();
		Category c = new Category("C1", "Homework", "Category for Homework tasks");
		
		assertTrue(list.addTask("Project 3", "Project 3 code and bbt", date1, date2, c));
		assertTrue(list.addTask("Final Exam", "Study for final exam", date2, date3, c));
		assertTrue(list.addTask("Task 3 title", "Task 3 description", date1, date2, c));
		
		assertEquals(0, list.indexOf("TL1-T1"));
		assertEquals(1, list.indexOf("TL1-T3"));
		assertEquals(2, list.indexOf("TL1-T2"));
		assertEquals(-1, list.indexOf("TL2-T1"));
	}


	/**
	 * Tests removing a Task from an index
	 */
	@Test
	public void testRemoveTaskAt() {
		try {
			list.removeTaskAt(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		try {
			list.removeTaskAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		GregorianCalendar cal1 = new GregorianCalendar(2016, 10, 20, 14, 45);
		GregorianCalendar cal2 = new GregorianCalendar(2016, 11, 2, 21, 0);
		GregorianCalendar cal3 = new GregorianCalendar(2016, 11, 9, 17, 0);
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();
		Date date3 = cal3.getTime();
		Category c = new Category("C1", "Homework", "Category for Homework tasks");
		
		assertTrue(list.addTask("Project 3", "Project 3 code and bbt", date1, date2, c));
		assertTrue(list.addTask("Final Exam", "Study for final exam", date2, date3, c));
		assertTrue(list.addTask("Task 3 title", "Task 3 description", date1, date2, c));
		
		assertEquals(0, list.indexOf("TL1-T1"));
		assertEquals(1, list.indexOf("TL1-T3"));
		assertEquals(2, list.indexOf("TL1-T2"));
		assertEquals(-1, list.indexOf("TL2-T1"));
		
		Task t = list.removeTaskAt(0);
		assertEquals("TL1-T1", t.getTaskID());
		assertEquals("Project 3", t.getTitle());
		
		t = list.removeTaskAt(1);
		assertEquals("TL1-T2", t.getTaskID());
		
		t = list.removeTaskAt(0);
		assertEquals("TL1-T3", t.getTaskID());
		
		assertTrue(list.isEmpty());
		assertEquals(0, list.size());
		
	}

	/**
	 * Tests removing a task by ID
	 */
	@Test
	public void testRemoveTask() {
		assertFalse(list.removeTask(null));
		assertFalse(list.removeTask("TL1-T1"));
		
		GregorianCalendar cal1 = new GregorianCalendar(2016, 10, 20, 14, 45);
		GregorianCalendar cal2 = new GregorianCalendar(2016, 11, 2, 21, 0);
		GregorianCalendar cal3 = new GregorianCalendar(2016, 11, 9, 17, 0);
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();
		Date date3 = cal3.getTime();
		Category c = new Category("C1", "Homework", "Category for Homework tasks");
		
		assertTrue(list.addTask("Project 3", "Project 3 code and bbt", date1, date2, c));
		assertTrue(list.addTask("Final Exam", "Study for final exam", date2, date3, c));
		assertTrue(list.addTask("Task 3 title", "Task 3 description", date1, date2, c));
		
		
		assertEquals(3, list.size());
		assertTrue(list.removeTask("TL1-T3"));
		assertEquals(2, list.size());
		assertTrue(list.removeTask("TL1-T2"));
		assertEquals(1, list.size());
		assertTrue(list.removeTask("TL1-T1"));
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
	}

	/**
	 * Tests the 2d array generation
	 */
	@Test
	public void testGet2DArray() {
		
		Object[][] array = list.get2DArray();
		assertEquals(0, array.length);
		
		GregorianCalendar cal1 = new GregorianCalendar(2016, 10, 20, 14, 45);
		GregorianCalendar cal2 = new GregorianCalendar(2016, 11, 2, 21, 0);
		GregorianCalendar cal3 = new GregorianCalendar(2016, 11, 9, 17, 0);
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();
		Date date3 = cal3.getTime();
		Category c = new Category("C1", "Homework", "Category for Homework tasks");
		
		assertTrue(list.addTask("Project 3", "Project 3 code and bbt", date1, date2, c));
		assertTrue(list.addTask("Final Exam", "Study for final exam", date2, date3, c));
		assertTrue(list.addTask("Task 3 title", "Task 3 description", date1, date2, c));
		
		array = list.get2DArray();
		assertEquals(3, array.length);
		assertEquals(8, array[0].length);
		
		assertEquals("TL1-T1", array[0][0]);
		assertEquals("Project 3", array[0][1]);
		assertEquals(c, array[0][2]);
		assertEquals(date1, array[0][3]);
		assertEquals(date2, array[0][4]);
		assertEquals(null, array[0][5]);
		assertEquals(false, array[0][6]);
		assertEquals("Project 3 code and bbt", array[0][7]);
		
	}


	/**
	 * Tests getting the task at an index
	 */
	@Test
	public void testGetTaskAt() {
		try {
			list.getTaskAt(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		try {
			list.getTaskAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
	}

}
