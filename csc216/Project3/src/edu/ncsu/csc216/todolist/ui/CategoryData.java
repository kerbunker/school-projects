package edu.ncsu.csc216.todolist.ui;

/**
 * Simple class for transferring data within the GUI via events.
 * 
 * @author David R. Wright
 * @author Sarah Heckman
 */
public class CategoryData {

	/** Category's id */
	private String categoryID;
	/** Category's name */
	private String categoryName;
	/** Category's description */
	private String categoryDesc;
	
	/**
	 * An object that holds a Category's data for use in the GUI.
	 * @param id category's id
	 * @param name category's name
	 * @param desc category's description
	 */
	public CategoryData(String id, String name, String desc) {
		categoryID = id;
		categoryName = name;
		categoryDesc = desc;
	}
	
	/**
	 * Returns the Category's id.
	 * @return the Category's id
	 */
	public String getCategoryID() {
		return categoryID;
	}

	/**
	 * Returns the Category's name.
	 * @return the Category's name
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Returns the Category's description.
	 * @return the Category's description
	 */
	public String getCategoryDesc() {
		return categoryDesc;
	}

	/**
	 * Returns a string representation of the Category's data.
	 * @return string representation of the Category's data.
	 */
	public String toString() {
		return "ID: " + categoryID + "  Name: " + categoryName + "  Desc: " + categoryDesc;
	}

}