package edu.ncsu.csc216.todolist;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

/**
 * Tests the toDoList
 * @author Kbunker
 *
 */
public class ToDoListTest {
	
	/** the todolist to test */
	private ToDoList list;

	/**
	 * Sets up the list for each test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ToDoList();
	}

	/**
	 * Tests setting the list as changed
	 */
	@Test
	public void testSetChanged() {
		assertFalse(list.isChanged());
		list.setChanged(true);
		assertTrue(list.isChanged());
		list.setChanged(false);
		assertFalse(list.isChanged());
		
	}

	/**
	 * Tests creating a todolist
	 */
	@Test
	public void testToDoList() {
		assertEquals(1, list.getNumTaskLists());
		assertFalse(list.isChanged());
		assertEquals("Categories", list.getCategoryList().getName());
		assertEquals("New List", list.getTaskList(0).getName());
		
	}


	/**
	 *Tests setting the filenames
	 */
	@Test
	public void testSetFileName() {
		try {
			list.setFilename("");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(list.getFilename());
		}
		try {
			list.setFilename(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(list.getFilename());
		}
		list.setFilename("file.txt");
		assertEquals("file.txt", list.getFilename());
	}


	/**
	 * Tests getting the tasklists
	 */
	@Test
	public void testGetTaskList() {
		try {
			list.getTaskList(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			//not sure what to check for
		}
		try {
			list.getTaskList(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			//not sure what to check for
		}
		
		assertEquals("New List", list.getTaskList(0).getName());
		
	}


	/**
	 * Tests adding a task list
	 */
	@Test
	public void testAddTaskList() {
		int i = list.addTaskList();
		assertEquals(1, i);
		list.getTaskList(i).setName("Homework");
		assertEquals("Homework", list.getTaskList(i).getName());
		assertEquals("TL1", list.getTaskList(0).getTaskListID());
		assertEquals("TL2", list.getTaskList(i).getTaskListID());
		
		int i2 = list.addTaskList();
		assertEquals(2, i2);
		assertEquals("New List", list.getTaskList(i2).getName());
		assertEquals("TL3", list.getTaskList(i2).getTaskListID());
		
		int i3 = list.addTaskList();
		assertEquals(3, i3);
		assertEquals("TL4", list.getTaskList(i3).getTaskListID());
	}

	/**
	 * Tests removing a task list
	 */
	@Test
	public void testRemoveTaskList() {
		try {
			list.removeTaskList(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.getNumTaskLists());
		}
		try {
			list.removeTaskList(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.getNumTaskLists());
		}
		assertEquals(1, list.getNumTaskLists());
		int i = list.addTaskList();
		assertEquals(1, i);
		list.getTaskList(i).setName("Homework");
		assertEquals("Homework", list.getTaskList(i).getName());
		assertEquals("TL1", list.getTaskList(0).getTaskListID());
		assertEquals("TL2", list.getTaskList(i).getTaskListID());
		
		int i2 = list.addTaskList();
		assertEquals(2, i2);
		assertEquals("New List", list.getTaskList(i2).getName());
		assertEquals("TL3", list.getTaskList(i2).getTaskListID());
		
		int i3 = list.addTaskList();
		assertEquals(3, i3);
		assertEquals("TL4", list.getTaskList(i3).getTaskListID());
		assertEquals(4, list.getNumTaskLists());
		
		assertEquals("TL1", list.getTaskList(0).getTaskListID());
		assertEquals("TL2", list.getTaskList(1).getTaskListID());
		assertEquals("TL3", list.getTaskList(2).getTaskListID());
		assertEquals("TL4", list.getTaskList(3).getTaskListID());
		
		list.removeTaskList(2);
		assertEquals(3, list.getNumTaskLists());
		assertEquals("TL1", list.getTaskList(0).getTaskListID());
		assertEquals("TL2", list.getTaskList(1).getTaskListID());
		assertEquals("TL4", list.getTaskList(2).getTaskListID());
		
		
		
	}

	/**
	 * Tests saving the data
	 */
	@Test
	public void testSaveDataFile() {
		list.saveDataFile("test-files/test_save_data.tdl");
		
		assertEquals(1, list.getNumTaskLists());
		int i = list.addTaskList();
		assertEquals(1, i);
		list.getTaskList(i).setName("Homework");
		assertEquals("Homework", list.getTaskList(i).getName());
		assertEquals("TL1", list.getTaskList(0).getTaskListID());
		assertEquals("TL2", list.getTaskList(i).getTaskListID());
		
		int i2 = list.addTaskList();
		assertEquals(2, i2);
		assertEquals("New List", list.getTaskList(i2).getName());
		assertEquals("TL3", list.getTaskList(i2).getTaskListID());
		
		int i3 = list.addTaskList();
		assertEquals(3, i3);
		assertEquals("TL4", list.getTaskList(i3).getTaskListID());
		assertEquals(4, list.getNumTaskLists());
		
		assertEquals("TL1", list.getTaskList(0).getTaskListID());
		assertEquals("TL2", list.getTaskList(1).getTaskListID());
		assertEquals("TL3", list.getTaskList(2).getTaskListID());
		assertEquals("TL4", list.getTaskList(3).getTaskListID());
		
		list.removeTaskList(2);
		assertEquals(3, list.getNumTaskLists());
		assertEquals("TL1", list.getTaskList(0).getTaskListID());
		assertEquals("TL2", list.getTaskList(1).getTaskListID());
		assertEquals("TL4", list.getTaskList(2).getTaskListID());
		
		list.saveDataFile("test-files/test_save_data2.tdl");
		
		
		
	}

	/**
	 * Tests opening a file
	 */
	@Test
	public void testOpenDataFile() {
		assertEquals(1, list.getNumTaskLists());
		list.openDataFile("test-files/test_save_data2.tdl");
		assertEquals(3, list.getNumTaskLists());
		assertEquals("TL1", list.getTaskList(0).getTaskListID());
		assertEquals("TL2", list.getTaskList(1).getTaskListID());
		assertEquals("TL4", list.getTaskList(2).getTaskListID());
		
		list.setFilename("test-files/test_save_data2.tdl");
		list.setChanged(true);
		list.openDataFile("test-files/test_save_data.tdl");
		
	}


}
