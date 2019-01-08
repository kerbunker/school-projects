package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * tests the category class
 * @author Kbunker
 *
 */
public class CategoryTest {
	
	/** The categories to test */
	private Category c1;
	private Category c2;
	private Category c3;

	/**
	 * Sets up the Categories to test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		c1 = new Category("C1", "Category 1 name", "Category 1 description");
		c2 = new Category("C1", "Category 2 name", "Category 2 description");
		c3 = new Category("C3", "Category 3 name", "Category 3 description");
	}

	/**
	 * Tests the hashCode method
	 */
	@Test
	public void testHashCode() {
		assertEquals(c1.hashCode(), c1.hashCode());
		assertNotEquals(c1.hashCode(), c2.hashCode());
		assertNotEquals(c1.hashCode(), c3.hashCode());
	}

	/**
	 * Tests creating a category
	 */
	@Test
	public void testCategory() {
		Category c4 = null;
		try {
			c4 = new Category(null, "Category 1 name", "Category 1 description");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c4);
		}
		try {
			c4 = new Category("", "Category 1 name", "Category 1 description");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c4);
		}
		
		assertEquals("Category 1 name", c1.getName());
		assertEquals("C1", c1.getCategoryID());
		assertEquals("Category 1 description", c1.getDescription());
		
	}


	/**
	 * Tests setting the name
	 */
	@Test
	public void testSetName() {
		try {
			c1.setName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Category 1 name", c1.getName());
		}
		try {
			c1.setName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Category 1 name", c1.getName());
		}
		c1.setName("Homework");
		assertEquals("Homework", c1.getName());
	}

	/**
	 * Tests determining equality
	 */
	@Test
	public void testEqualsObject() {
		Category c4 = null;
		assertFalse(c1.equals(c4));
		assertTrue(c1.equals(c1));
		assertTrue(c1.equals(c2));
		assertFalse(c1.equals(c3));
	}

	/**
	 * Tests comparing two objects
	 */
	@Test
	public void testCompareToCategory() {
		assertEquals(0, c1.compareTo(c1));
		assertEquals(0, c1.compareTo(c2));
		assertTrue(c1.compareTo(c3) < 0);
		assertTrue(c3.compareTo(c1) > 0);
	}

	/**
	 * tests the toString method
	 */
	@Test
	public void testToString() {
		assertEquals("Category 1 name", c1.toString());
	}


}
