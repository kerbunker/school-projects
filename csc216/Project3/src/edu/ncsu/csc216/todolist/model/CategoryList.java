/**
 * 
 */
package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.todolist.util.ArrayList;

/**
 * The class for the Catefory lists. Keeps track of the Categories in the System
 * in an array list
 * 
 * @author Kbunker
 *
 */
public class CategoryList extends Observable implements Tabular, Serializable, Observer {

	/** The field for the id for the serializable object */
	private static final long serialVersionUID = 984509L;
	/** The list to keep track of the Categories */
	private ArrayList list;
	/** The name of the Categories list */
	private String name;
	/** The next num for the Category id */
	private int nextCategoryNum;

	/**
	 * Constructor for the Category List. Sets the name of the list and
	 * constructs a new ArrayList object and sets the nextCategoryNum to 1.
	 */
	public CategoryList() {
		name = "Categories";
		list = new ArrayList();
		nextCategoryNum = 1;
	}

	/**
	 * Gets the list's name
	 * 
	 * @return the name of the list
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds a category to the list. If the category cannot be made or added to
	 * the list, false is returned. oterwise the category is made and added to
	 * the list in the appropriate order determined by the CategoryID.
	 * 
	 * @param name
	 *            the name of the Category
	 * @param description
	 *            the Description of the Category
	 * @return true if the Category is successfully added to the list
	 */
	public boolean addCategory(String name, String description) {
		Category c = null;
		try {
			c = new Category("C" + getNextCategoryNum(), name, description);
		} catch (IllegalArgumentException e) {
			return false;
		}

		if (list.size() == 0) {
			list.add(0, c);
			incNextCategoryNum();
			c.addObserver(this);
			setChanged();
			notifyObservers(this);
			return true;
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (c.compareTo((Category) list.get(i)) < 0) {
					list.add(i, c);
					incNextCategoryNum();
					c.addObserver(this);
					setChanged();
					notifyObservers(this);
					return true;
				} else if (c.compareTo((Category) list.get(i)) == 0) {
					return false;
				}
			}
			list.add(list.size(), c);
			incNextCategoryNum();
			c.addObserver(this);
			setChanged();
			notifyObservers(this);
			return true;
		}
	}

	/**
	 * Returns the Category at the given index. If the index is out of the
	 * allowable bounds, an IndexOutOfBoundsException is thrown.
	 * 
	 * @param index
	 *            the index of the Category to return
	 * @return the Category at the given index
	 */
	public Category getCategoryAt(int index) {
		if (index < 0 || index >= list.size()) {
			throw new IndexOutOfBoundsException();
		}

		return (Category) list.get(index);
	}

	/**
	 * Finds the index of the Category with the given string. if the Category
	 * does not exist in the list -1 is returned
	 * 
	 * @param id
	 *            the id of the Category to find the index of
	 * @return the index of the Category with the given id, or -1 if the id was
	 *         not found
	 */
	public int indexOf(String id) {
		if (id == null) {
			return -1;
		}
		for (int i = 0; i < list.size(); i++) {
			if (id.equals(((Category) list.get(i)).getCategoryID())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Finds the index of the Category with the given name. Returns -1 if the
	 * category could not be found in the list
	 * 
	 * @param name
	 *            the name of the Category to find the index of
	 * @return the index of the Category, or -1 if the Ctaegory is not found
	 */
	public int indexOfName(String name) {
		if (name == null) {
			return -1;
		}
		for (int i = 0; i < list.size(); i++) {
			if (name.equals(((Category) list.get(i)).getName())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Determines whether the list is empty
	 * @return true if the list is empty, false if it is not
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Removes and returns the Category at the given index
	 * @param index the index of the Category to return
	 * @return the category removed from the list
	 */
	public Category removeCategoryAt(int index) {
		if (index < 0 || index >= list.size()) {
			throw new IndexOutOfBoundsException();
		}
		Category c = (Category) list.remove(index);
		setChanged();
		notifyObservers(this);
		c.deleteObserver(this);
		return c;
	}

	/**
	 * Removes the catgeory with the given id
	 * @param id the id of the category to remove
	 * @return true if the category is removed
	 */
	public boolean removeCategory(String id) {
		if (id == null) {
			return false;
		}
		for (int i = 0; i < list.size(); i++) {
			if (id.equals(((Category) list.get(i)).getCategoryID())) {
				Category c = (Category) list.remove(i);
				setChanged();
				notifyObservers(this);
				c.deleteObserver(this);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the number for the next Category ID
	 * @return the int for the next Category id
	 */
	private int getNextCategoryNum() {
		return nextCategoryNum;
	}

	/**
	 * Increments the NextCategoryNum for the next Category id
	 */
	private void incNextCategoryNum() {
		nextCategoryNum++;
	}

	/**
	 * Returns the 2d Object array of the data in the list
	 * 
	 * @return the 2d object array
	 */
	public Object[][] get2DArray() {
		Object[][] array = new Object[list.size()][3];
		if (list.size() == 0) {
			return array;
		}
		for (int i = 0; i < list.size(); i++) {
			array[i][0] = ((Category) list.get(i)).getCategoryID();
			array[i][1] = ((Category) list.get(i)).getName();
			array[i][2] = ((Category) list.get(i)).getDescription();
		}
		return array;
	}

	/**
	 * Updates the observable
	 * 
	 * @param o the observable to update
	 * @param arg the object to notify the observers of
	 */
	public void update(Observable o, Object arg) {

		if (list.contains(o)) {
			setChanged();
			notifyObservers(arg);
		}
	}

}
