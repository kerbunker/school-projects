package edu.ncsu.csc216.todolist.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Category List Class
 * @author Kbunker
 *
 */
public class CategoryListTest {
	/** Keeps track of the CategoryList throughout the tests */
	private CategoryList list;

	/**
	 * Sets up a new CategoryList for each test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new CategoryList();
	}

	/**
	 * Tests that a CategoryList has been constructed properly
	 */
	@Test
	public void testCategoryList() {
		assertEquals("Categories", list.getName());
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}


	/**
	 * Tests that a Category can be added to the list properly
	 */
	@Test
	public void testAddCategory() {
		assertTrue(list.addCategory("Homework", "Category for homework tasks"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		assertFalse(list.addCategory(null, null));
		assertEquals(1, list.size());
		
		assertTrue(list.addCategory("Work", "Tasks needed to be accomplished for work"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(2, list.size());
	}

	/**
	 * Tests getting a Category at a specific index
	 */
	@Test
	public void testGetCategoryAt() {
		assertTrue(list.isEmpty());
		try {
			list.getCategoryAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		try {
			list.getCategoryAt(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		assertTrue(list.addCategory("Homework", "Category for homework tasks"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		Category c = list.getCategoryAt(0);
		assertEquals(c.getName(), "Homework");
		assertEquals(c.getCategoryID(), "C1");
		
		
	}

	/**
	 * Tests getting the index of a particular Category
	 */
	@Test
	public void testIndexOf() {
		assertEquals(-1, list.indexOf(null));
		assertEquals(-1, list.indexOf("C1"));
		
		assertTrue(list.addCategory("Homework", "Category for homework tasks"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(0, list.indexOf("C1"));
		
		assertTrue(list.addCategory("Work", "Tasks needed to be accomplished for work"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(2, list.size());
		
		assertEquals(1, list.indexOf("C2"));
		
		
	}

	/**
	 * Tests getting the index of a Category with a specific name
	 */
	@Test
	public void testIndexOfName() {
		
		assertEquals(-1, list.indexOfName(null));
		assertEquals(-1, list.indexOfName("Homework"));
		
		assertTrue(list.addCategory("Homework", "Category for homework tasks"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(0, list.indexOfName("Homework"));
		
		assertTrue(list.addCategory("Work", "Tasks needed to be accomplished for work"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(2, list.size());
		
		assertEquals(1, list.indexOfName("Work"));
		
	}

	/**
	 * Tests removing a category at a specified index
	 */
	@Test
	public void testRemoveCategoryAt() {
		try {
			list.removeCategoryAt(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		try {
			list.removeCategoryAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		assertTrue(list.addCategory("Homework", "Category for homework tasks"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(0, list.indexOf("C1"));
		
		assertTrue(list.addCategory("Work", "Tasks needed to be accomplished for work"));
		assertEquals("Work", list.getCategoryAt(1).getName());
		assertEquals("C2", list.getCategoryAt(1).getCategoryID());
		assertEquals("Tasks needed to be accomplished for work", list.getCategoryAt(1).getDescription());
		assertEquals(2, list.size());
		
		assertEquals(1, list.indexOf("C2"));
		
		Category c = list.removeCategoryAt(1);
		assertEquals("C2", c.getCategoryID());
		assertEquals("Work", c.getName());
		assertEquals("Tasks needed to be accomplished for work", c.getDescription());
		assertEquals(1, list.size());
		
		Category c2 = list.removeCategoryAt(0);
		assertEquals("C1", c2.getCategoryID());
		assertEquals("Homework", c2.getName());
		assertEquals("Category for homework tasks", c2.getDescription());
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
	}

	/**
	 * Tests removing a category with a specific id
	 */
	@Test
	public void testRemoveCategory() {
		assertFalse(list.removeCategory(null));
		assertFalse(list.removeCategory("C1"));
		
		assertTrue(list.addCategory("Homework", "Category for homework tasks"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(0, list.indexOf("C1"));
		
		assertTrue(list.addCategory("Work", "Tasks needed to be accomplished for work"));
		assertEquals("Work", list.getCategoryAt(1).getName());
		assertEquals("C2", list.getCategoryAt(1).getCategoryID());
		assertEquals("Tasks needed to be accomplished for work", list.getCategoryAt(1).getDescription());
		assertEquals(2, list.size());
		
		assertEquals(1, list.indexOf("C2"));
		
		assertTrue(list.removeCategory("C2"));
		assertEquals(1, list.size());
		
		assertTrue(list.removeCategory("C1"));
		assertEquals(0, list.size());
		
		
	}

	/**
	 * Tests getting the 2d array of the Category list
	 */
	@Test
	public void testGet2DArray() {
		Object[][] array = list.get2DArray();
		assertEquals(0, array.length);
		
		assertTrue(list.addCategory("Homework", "Category for homework tasks"));
		assertEquals("Homework", list.getCategoryAt(0).getName());
		assertEquals("C1", list.getCategoryAt(0).getCategoryID());
		assertEquals("Category for homework tasks", list.getCategoryAt(0).getDescription());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(0, list.indexOf("C1"));
		
		assertTrue(list.addCategory("Work", "Tasks needed to be accomplished for work"));
		assertEquals("Work", list.getCategoryAt(1).getName());
		assertEquals("C2", list.getCategoryAt(1).getCategoryID());
		assertEquals("Tasks needed to be accomplished for work", list.getCategoryAt(1).getDescription());
		assertEquals(2, list.size());
		
		assertEquals(1, list.indexOf("C2"));
		
		array = list.get2DArray();
		
		assertEquals(2, array.length);
		assertEquals(3, array[0].length);
		assertEquals(3, array[1].length);
		assertEquals("C1", array[0][0]);
		assertEquals("Homework", array[0][1]);
		assertEquals("Category for homework tasks", array[0][2]);
		assertEquals("C2", array[1][0]);
		assertEquals("Work", array[1][1]);
		assertEquals("Tasks needed to be accomplished for work", array[1][2]);
		
	}


}
