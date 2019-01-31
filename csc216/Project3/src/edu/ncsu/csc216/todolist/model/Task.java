/**
 * 
 */
package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;

/**
 * The Task class for the TaskList system
 * 
 * @author Kbunker
 *
 */
public class Task extends Observable implements Serializable, Comparable<Task> {
	/** The ID for the serializable object */
	private static final long serialVersionUID = 7459L;
	/** The task's title */
	private String title;
	/** The tasks details */
	private String details;
	/** The task's start date */
	private Date startDateTime;
	/** The tasks dueDate */
	private Date dueDateTime;
	/** The date the Task was completed if completed */
	private Date completedDateTime;
	/** Keeps track of if the Task is complete */
	private boolean completed;
	/** The task's id */
	public String taskID;
	/** The category the task is in */
	private Category category;

	/**
	 * Constructor for the Task
	 * 
	 * @param title
	 *            the task's title
	 * @param details
	 *            the task's details
	 * @param startDateTime
	 *            the tasks startDate
	 * @param endDateTime
	 *            the task's dueDate
	 * @param c
	 *            the category the task is in
	 * @param id
	 *            the Task's id
	 */
	public Task(String title, String details, Date startDateTime, Date endDateTime, Category c, String id) {
		setTitle(title);
		setDetails(details);
		setStartDateTime(startDateTime);
		setDueDateTime(endDateTime);
		setCategory(c);
		setTaskID(id);
		completed = false;
		completedDateTime = null;
	}

	/**
	 * Gets the Taks's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title after determining is an IllegalArgumentException needs to
	 * be thrown if the title is null or an empty string
	 * 
	 * @param title
	 *            the title to set for the task
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the Details of the Task
	 * 
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Sets the details for the Task
	 * 
	 * @param details
	 *            the task's details
	 */
	public void setDetails(String details) {
		this.details = details;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the startDateTime
	 * 
	 * @return the startDateTime
	 */
	public Date getStartDateTime() {
		return startDateTime;
	}

	/**
	 * Sets the startDateTime after ensuring it is not null
	 * 
	 * @param startDateTime
	 *            the startDateTime to set
	 */
	public void setStartDateTime(Date startDateTime) {
		if (startDateTime == null) {
			throw new IllegalArgumentException();
		}
		this.startDateTime = startDateTime;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the dueDateTime
	 * 
	 * @return the dueDateTime
	 */
	public Date getDueDateTime() {
		return dueDateTime;
	}

	/**
	 * Sets the dueDateTime after ensuring it is not null
	 * 
	 * @param dueDateTime
	 *            the dueDateTime to set
	 */
	public void setDueDateTime(Date dueDateTime) {
		if (dueDateTime == null) {
			throw new IllegalArgumentException();
		}
		this.dueDateTime = dueDateTime;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the completedDateTime if it is complete. Otherwise it returns null
	 * 
	 * @return the completedDateTime
	 */
	public Date getCompletedDateTime() {
		return completedDateTime;
	}

	/**
	 * Sets the completedDateTime
	 * 
	 * @param completedDateTime
	 *            the completedDateTime to set
	 */
	public void setCompletedDateTime(Date completedDateTime) {
		this.completedDateTime = completedDateTime;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * returns the boolean field for if the task is completed
	 * 
	 * @return true if the task is complete
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * Sets the completed field for whether the task is complete or not
	 * 
	 * @param completed
	 *            the boolean result of whether the task is complete or not
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Sets the category the Task is located in. Throws an
	 * IllegalArgumentException if the category is null.
	 * 
	 * @param c
	 *            the category for the task
	 */
	public void setCategory(Category c) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		this.category = c;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the task's category
	 * 
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Gets the TaskID
	 * 
	 * @return the TaskID
	 */
	public String getTaskID() {
		return taskID;
	}

	/**
	 * Sets the TaskID. Throws and IllegalArgumentException if the id is null or
	 * an empty string
	 * 
	 * @param id
	 *            the id to set for the task
	 */
	private void setTaskID(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException();
		}
		this.taskID = id;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Generates the hashcode version of the Task
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((taskID == null) ? 0 : taskID.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Checks whether the object is equal based on the ID
	 * 
	 * @param obj
	 *            the object to compare to the current Task instance
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskID == null) {
			if (other.taskID != null)
				return false;
		} else if (!taskID.equals(other.taskID))
			return false;
		return true;
	}

	/**
	 * Compares the Task dueDateTime given to the current instance's dueDateTime
	 * 
	 * @param t
	 *            the task to compare to the current instance
	 * @return 0 if the two tasks are equal, <0 if the current task is less than
	 *         the parameter task or > 0 if the opposite is true
	 */
	public int compareTo(Task t) {
		return dueDateTime.compareTo(t.getDueDateTime());
	}

}
