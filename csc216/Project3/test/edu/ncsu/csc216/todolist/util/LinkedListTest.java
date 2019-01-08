package edu.ncsu.csc216.todolist.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests the Linkedlist class
 * @author Kbunker
 *
 */
public class LinkedListTest {

	/**
	 * tests the constructor
	 */
	@Test
	public void testLinkedList() {
		LinkedList list = new LinkedList();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests finding the size
	 */
	@Test
	public void testSize() {
		LinkedList list = new LinkedList();
		assertEquals(0, list.size());
		
		list.add("apple");
		assertEquals(1, list.size());
	}

	/**
	 * tests finding if the list is empty
	 */
	@Test
	public void testIsEmpty() {
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		list.add("apple");
		assertFalse(list.isEmpty());
	}

	/**
	 * tests determining if the item is in the list
	 */
	@Test
	public void testContains() {
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		list.add("apple");
		assertFalse(list.isEmpty());
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("banana"));
	}

	/**
	 * tests adding an object
	 */
	@Test
	public void testAddObject() {
		LinkedList list = new LinkedList();
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
	 * Tests getting an object
	 */
	@Test
	public void testGet() {
		LinkedList list = new LinkedList();
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
		LinkedList list = new LinkedList();
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
	 * tests removing the object
	 */
	@Test
	public void testRemove() {
		LinkedList list = new LinkedList();
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
		LinkedList list = new LinkedList();
		
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
