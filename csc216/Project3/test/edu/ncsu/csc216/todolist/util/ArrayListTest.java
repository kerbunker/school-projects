package edu.ncsu.csc216.todolist.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ArrayList class
 * @author Kbunker
 *
 */
public class ArrayListTest {

	/**
	 * tests the constructor
	 */
	@Test
	public void testArrayList() {
		ArrayList list = new ArrayList();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests the constructor with the size parameter
	 */
	@Test
	public void testArrayListInt() {
		ArrayList list = null;
		try {
			list = new ArrayList(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(list);
		}
		list = new ArrayList(20);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests getting the size of the list
	 */
	@Test
	public void testSize() {
		ArrayList list = new ArrayList();
		assertEquals(0, list.size());
		
		list.add("apple");
		assertEquals(1, list.size());
	}

	/**
	 * tests getting whether the list is empty
	 */
	@Test
	public void testIsEmpty() {
		ArrayList list = new ArrayList();
		assertTrue(list.isEmpty());
		list.add("apple");
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests determining if the item is contained in the list
	 */
	@Test
	public void testContains() {
		ArrayList list = new ArrayList();
		assertTrue(list.isEmpty());
		list.add("apple");
		assertFalse(list.isEmpty());
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("banana"));
		
	}

	/**
	 * tests adding the object
	 */
	@Test
	public void testAddObject() {
		ArrayList list = new ArrayList();
		list.add("apple");
		list.add("banana");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(2, list.size());
		}
	}

	/**
	 * Tests getting the object
	 */
	@Test
	public void testGet() {
		ArrayList list = new ArrayList();
		try {
			list.get(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
	}

	/**
	 * Tests adding an object at a specific index
	 */
	@Test
	public void testAddIntObject() {
		ArrayList list = new ArrayList(2);
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(0, list.size());
		}
		try {
			list.add(2, "apple");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		try {
			list.add(-1, "apple");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		list.add(0, "apple");
		list.add(0, "banana");
		list.add(2, "dragonfruit");
		list.add(2, "peach");
		assertEquals(4, list.size());
		assertEquals("banana", list.get(0));
		assertEquals("apple", list.get(1));
		assertEquals("peach", list.get(2));
		assertEquals("dragonfruit", list.get(3));
	}

	/**
	 * tests removing an object
	 */
	@Test
	public void testRemove() {
		ArrayList list = new ArrayList(2);
		try {
			list.remove(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		list.add(0, "apple");
		list.add(0, "banana");
		list.add(2, "dragonfruit");
		list.add(2, "peach");
		assertEquals(4, list.size());
		assertEquals("banana", list.remove(0));
		assertEquals(3, list.size());
		assertEquals("dragonfruit", list.remove(2));
		
	}

	/**
	 * tests finding the index of an object
	 */
	@Test
	public void testIndexOf() {
		ArrayList list = new ArrayList(2);
		
		list.add(0, "apple");
		list.add(0, "banana");
		list.add(2, "dragonfruit");
		list.add(2, "peach");
		assertEquals(4, list.size());
		
		assertEquals(-1, list.indexOf("pear"));
		assertEquals(0, list.indexOf("banana"));
		assertEquals(1, list.indexOf("apple"));
		assertEquals(2, list.indexOf("peach"));
		assertEquals(3, list.indexOf("dragonfruit"));
	}

}
