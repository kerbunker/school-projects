package edu.ncsu.csc216.todolist.ui;

import java.util.Date;

import edu.ncsu.csc216.todolist.model.Category;

/**
 * Simple class for transferring data within the GUI via events.
 * 
 * @author David R. Wright
 * @author Sarah Heckman
 */
public class TaskData {

	/** Task's id */
	private String taskID;
	/** Task's title */
	private String title;
	/** Task's category */
	private Category category;
	/** Task's startDateTime */
	private Date startDateTime;
	/** Task's dueDateTime */
	private Date dueDateTime;
	/** Task's completedDateTime */
	private Date completedDateTime;
	/** Task's completed flag */
	private boolean completed;
	/** Task's details  */
	private String details;
	
	/**
	 * Creates an empty TaskData object.
	 */
	public TaskData() {
		this("", "", null, null, null, null, false, "");
	}
	
	/**
	 * Creates a TaskData object from all fields.
	 * @param taskID task's id
	 * @param title task's title
	 * @param category task's category
	 * @param startDateTime task's start date and time
	 * @param dueDateTime task's end date and time
	 * @param completedDateTime task's completed date and time
	 * @param completed is the task completed
	 * @param details task's details
	 */
	public TaskData(String taskID, String title, Category category, Date startDateTime, 
			Date dueDateTime, Date completedDateTime, boolean completed, String details) {
		this.taskID = taskID;
		this.title = title;
		this.category = category;
		this.startDateTime = startDateTime;
		this.dueDateTime = dueDateTime; 
		this.completedDateTime = completedDateTime;
		this.completed = completed;
		this.details = details;
	}
	
	
	
	/**
	 * Returns the task id
	 * @return the taskID
	 */
	public String getTaskID() {
		return taskID;
	}

	/**
	 * Returns the task title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the task category
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Returns the task start date and time
	 * @return the startDateTime
	 */
	public Date getStartDateTime() {
		return startDateTime;
	}

	/**
	 * Returns the task due date and time
	 * @return the dueDateTime
	 */
	public Date getDueDateTime() {
		return dueDateTime;
	}

	/**
	 * Returns the task completed date and time
	 * @return the completedDateTime
	 */
	public Date getCompletedDateTime() {
		return completedDateTime;
	}

	/**
	 * Returns true if the task is completed
	 * @return true if completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * Returns the task details
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Returns the TaskData as an Object array for use in the GUI.
	 * @return the TaskData as an Object array.
	 */
	public Object[] getDataArray() {
		Object[] data = new Object[8];
		data[0] = taskID;
		data[1] = title;
		data[2] = category;
		data[3] = startDateTime;
		data[4] = dueDateTime;
		data[5] = completedDateTime;
		data[6] = completed;
		data[7] = details;
		return data;
	}
}
