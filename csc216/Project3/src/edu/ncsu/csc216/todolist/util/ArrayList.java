package edu.ncsu.csc216.todolist.util;

import java.io.Serializable;

/**
 * A list of objects kept track in an array
 * 
 * @author Kbunker
 *
 */
public class ArrayList implements List, Serializable {

	/** The UID for the serializable implementation */
	private static final long serialVersionUID = 28592L;
	/** Amount to add to the array to expand it */
	private static final int RESIZE = 10;
	/** The size of the list */
	private int size;
	/** The array of list objects */
	private Object[] list;

	/**
	 * Constructor for the ArrayList without a size parameter. Calls to the
	 * constructor with the size parameter
	 */
	public ArrayList() {
		this(RESIZE);

	}

	/**
	 * Constructor for the arraylist with a size parameter. Creates a new array
	 * with the number of items given as the parameter. Also sets the size field
	 * to 0.
	 * 
	 * @param size the size of the ArrayList to create
	 */
	public ArrayList(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		list = new Object[size];
		this.size = 0;
	}

	/**
	 * Returns the number of elements in the list
	 * 
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		if (size > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return size;
	}

	/**
	 * Returns true if there are no items in the list
	 * 
	 * @return true if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Determines if the object passed in as the parameter is contained in the
	 * list
	 * 
	 * @param o
	 *            the object to find if it is in the list
	 * @return true if the object is in the list
	 */
	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < size; i++) {
			if (list[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the object as the parameter to the end of the list
	 * 
	 * @param o
	 *            the object to add to the end of the list
	 * @return true if the object was added to the list
	 */
	@Override
	public boolean add(Object o) {
		add(size, o);
		return true;
	}

	/**
	 * Returns the object at the index given. If the index given is outside the
	 * allowable bounds an IndexOutOfBoundsException is thrown.
	 * 
	 * @param index
	 *            the index to return the item
	 * @return the Object at the given index
	 */
	@Override
	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Adds the item passed in as parameter to the index given, If the element
	 * is null an NullPointerException is thrown. If the index given is out of
	 * the allowable bounds an IndexOutOfBoundsException is thrown.
	 * 
	 * @param index
	 *            the index to add the element to
	 * @param element
	 *            the element to add at the given index
	 */
	@Override
	public void add(int index, Object element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (size == list.length) {
			Object[] list2 = new Object[size + RESIZE];
			for (int i = 0; i < size; i++) {
				list2[i] = list[i];
			}
			list = list2;
		}
		if (index == size) {
			list[index] = element;
		} else {
			for (int i = size - 1; i >= index; i--) {
				list[i + 1] = list[i];
			}
			list[index] = element;
		}
		size++;

	}

	/**
	 * Removes and returns the object at the given index
	 * 
	 * @param index
	 *            the index of the item to remove
	 * @return the object that was removed from the list
	 */
	@Override
	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Object o = list[index];
		if (index == size - 1) {
			list[index] = null;
		} else {
			for (int i = index; i < size; i++) {
				list[i] = list[i + 1];
			}
		}
		size--;

		return o;
	}

	/**
	 * Finds the index of the object given by the parameter
	 * 
	 * @param o
	 *            the object to find the index of
	 * @return the index of the object given or -1 if the object is not in the
	 *         list
	 */
	@Override
	public int indexOf(Object o) {
		if (size == 0) {
			return -1;
		} else {
			for (int i = 0; i < size; i++) {
				if (list[i].equals(o)) {
					return i;
				}
			}
		}
		return -1;
	}

}
