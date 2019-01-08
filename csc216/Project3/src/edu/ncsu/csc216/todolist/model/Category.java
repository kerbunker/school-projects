package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Observable;

/**
 * The class for the Categories for the TaskList System
 * 
 * @author Kbunker
 *
 */
public class Category extends Observable implements Serializable, Comparable<Category> {

	/** Field for the ID for the seializable object */
	private static final long serialVersionUID = 459188L;
	/** The Category's name */
	private String name;
	/** The Category's description */
	private String description;
	/** The Category's id */
	private String categoryID;

	/**
	 * Creates a new Category object and sets the name, id, and description
	 * 
	 * @param id
	 *            the Category's id
	 * @param name
	 *            the Category's name
	 * @param description
	 *            the Category's description
	 */
	public Category(String id, String name, String description) {
		setName(name);
		setDescription(description);
		setCategoryID(id);

	}

	/**
	 * Gets the name of the Category
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Category's name after ensuring it is not null or an empty
	 * string. If either of those cases are true, an IllegalArgumentException is
	 * thrown
	 * 
	 * @param name
	 *            the name to set for the Category
	 */
	public void setName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		setChanged();
		notifyObservers(this);

	}

	/**
	 * Gets the description of the Category
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the Category's description
	 * 
	 * @param description
	 *            the category's description
	 */
	public void setDescription(String description) {
		this.description = description;
		setChanged();
		notifyObservers(this);

	}

	/**
	 * Gets the Category's ID
	 * 
	 * @return the id
	 */
	public String getCategoryID() {
		return categoryID;
	}

	/**
	 * Sets the Category's id. Ensures that it is not null or an dempty string,
	 * which would throw an IllegalArgumentException
	 * 
	 * @param categoryID
	 *            the ID to set
	 */
	private void setCategoryID(String categoryID) {
		if (categoryID == null || categoryID.equals("")) {
			throw new IllegalArgumentException();
		}
		this.categoryID = categoryID;
		setChanged();
		notifyObservers();
	}

	/**
	 * Determines whether 2 objects are equal based on their IDs
	 * 
	 * @param obj
	 *            the object to compare this instance to
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryID == null) {
			if (other.categoryID != null)
				return false;
		} else if (!categoryID.equals(other.categoryID))
			return false;
		return true;
	}

	/**
	 * Compares 2 Categories based on their IDs
	 * 
	 * @param c
	 *            the category to compare this instance to
	 * @return the int value of which Category is higher or lower
	 */
	public int compareTo(Category c) {
		return categoryID.compareTo(c.getCategoryID());
	}

	/**
	 * Hashes the code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryID == null) ? 0 : categoryID.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Returns the name of the Category as the string
	 * 
	 * @return the toString
	 */
	public String toString() {
		return name;
	}

}
