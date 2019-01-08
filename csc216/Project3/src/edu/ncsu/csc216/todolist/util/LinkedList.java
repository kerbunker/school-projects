package edu.ncsu.csc216.todolist.util;

import java.io.Serializable;

/**
 * The LinkedList class
 * @author Kbunker
 *
 */
public class LinkedList implements List, Serializable {

	/** ID for the serializable object */
	private static final long serialVersionUID = 349987L;
	/** The front node of the list */
	private Node head;

	/**
	 * Constructor of the LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * Gets the size of the list
	 */
	@Override
	public int size() {
		return size(head);
	}

	/**
	 * Gets the size of the list
	 * 
	 * @param node
	 *            the node to count
	 * @return the number of nodes counted so far
	 */
	private int size(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + size(node.next);
		}
	}

	/**
	 * Checks whether the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Checks if the object is contained int he list
	 */
	@Override
	public boolean contains(Object o) {
		if (head == null) {
			return false;
		}
		Node p = head;
		while (p != null) {
			if (p.value.equals(o)) {
				return true;
			}
			p = p.next;
		}
		return false;
	}

	/**
	 * Adds an object to the end of the list
	 * 
	 * @param o
	 *            the object to add to the list
	 * @return true if the object is added
	 */
	@Override
	public boolean add(Object o) {
		add(size(), o);
		return true;
	}

	/**
	 * gets the object at the given index
	 * 
	 * @param index
	 *            the index to get the object at
	 * @return the object at the given index
	 */
	@Override
	public Object get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return head.value;
		}
		Node p = head;
		for (int i = 0; i < index - 1; i++) {
			p = p.next;
		}
		return p.next.value;
	}

	/**
	 * Adds the object at the given index
	 * 
	 * @param index
	 *            the index to add the object to
	 * @param element
	 *            the object to add to the index
	 */
	@Override
	public void add(int index, Object element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (size() == 0) {
			head = new Node(element, null);
		} else if (index == 0) {
			head = new Node(element, head);
		} else if (index == size()) {
			Node p = head;
			for (int i = 0; i < index - 1; i++) {
				p = p.next;
			}
			p.next = new Node(element, null);
		} else {
			Node p = head;
			for (int i = 0; i < index - 1; i++) {
				p = p.next;
			}
			p.next = new Node(element, p.next);
		}

	}

	/**
	 * private Node insertAt(int index, Object item, Node node) { return null; }
	 */

	/**
	 * removes the node at the given index
	 * 
	 * @param index
	 *            the index of the object to remove
	 * @return the object at the node to remove
	 */
	@Override
	public Object remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node p = head;
		if (index == 0) {
			head = p.next;
		} else {
			for (int i = 0; i < index - 1; i++) {
				p = p.next;
			}
			Node r = p.next;
			p.next = r.next;
			p = r;
		}
		return p.value;
	}

	/**
	 * private Node remove(int index, Node node) { return null; }
	 */

	/**
	 * Finds the index of the object
	 * 
	 * @param o
	 *            the object to find the index of
	 * @return the value of the index of the object
	 */
	@Override
	public int indexOf(Object o) {
		if (isEmpty() || o == null) {
			return -1;
		}
		return indexOf(o, head, 0);
	}

	/**
	 * Finds the index of the object
	 * 
	 * @param item
	 *            the item to find
	 * @param node
	 *            the node to look for the object in
	 * @param index
	 *            the index of the node
	 * @return the index of the node
	 */
	private int indexOf(Object item, Node node, int index) {
		if (node == null) {
			return -1;
		}
		if (item.equals(node.value)) {
			return index;
		} else {
			return indexOf(item, node.next, index + 1);
		}
	}

	/**
	 * The nested class for the Nodes of the linkedLists
	 * @author Katelyn
	 *
	 */
	public class Node implements Serializable {
		/** ID for the seriablizable object */
		private static final long serialVersionUID = 484909840L;
		/** the value stored at the node */
		private Object value;
		/** The next node in the list */
		Node next;

		/**
		 * Constructs the node
		 * 
		 * @param value
		 *            the object to store at the node
		 * @param next
		 *            the next node in the list
		 */
		public Node(Object value, Node next) {
			this.value = value;
			this.next = next;
		}

	}

}
