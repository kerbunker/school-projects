/**
 * 
 */
package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.todolist.util.LinkedList;

/**
 * The class for the TaskLists, which store the Task in lists for the user to
 * organize
 * 
 * @author Kbunker
 *
 */
public class TaskList extends Observable implements Tabular, Serializable, Observer {

	/** The id for the Serializable object */
	private static final long serialVersionUID = 98734509L;
	/** The list to store the Tasks */
	private LinkedList list;
	/** The task list's name */
	private String name;
	/** The int for the ID for the next task */
	private int nextTaskNum;
	/** The id for the taskList */
	private String taskListID;

	/**
	 * Constructor for the taskList. Sets the tasklist name and id and creates
	 * the list to store the tasks in
	 * 
	 * @param name
	 *            the task list's name
	 * @param id
	 *            the taskList's id
	 */
	public TaskList(String name, String id) {
		list = new LinkedList();
		setName(name);
		setTaskListID(id);
		nextTaskNum = 1;
	}

	/**
	 * Gets the TaskList's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name. If the name is null or an empty string an
	 * IllegalARgumentException is thrown
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		setChanged();
		notifyObservers(this);
		this.name = name;
	}

	/**
	 * Gets the TaskListID
	 * 
	 * @return the taskListID
	 */
	public String getTaskListID() {
		return taskListID;
	}

	/**
	 * Sets the taskListId. If the id is null or an empty String an
	 * IllegalArgumentException is thrown
	 * 
	 * @param id
	 *            the id to set
	 */
	private void setTaskListID(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException();
		}
		this.taskListID = id;
	}

	/**
	 * Gets the next number for the Task ID
	 * 
	 * @return the next number for the task being added to the list
	 */
	private int getNextTaskNum() {
		return nextTaskNum;
	}

	/**
	 * Increments the nextTaskNum
	 */
	private void incNextTaskNum() {
		nextTaskNum++;
	}

	/**
	 * Creates a new Task and adds it to the List
	 * 
	 * @param title
	 *            the title of the new Task
	 * @param description
	 *            the task's description
	 * @param startDate
	 *            the task's startDate
	 * @param dueDate
	 *            the task's dueDate
	 * @param category
	 *            the category the task is in
	 * @return true if the Task is successfully added to the list
	 */
	public boolean addTask(String title, String description, Date startDate, Date dueDate, Category category) {
		Task t = null;
		try {
			t = new Task(title, description, startDate, dueDate, category, taskListID + "-T" + getNextTaskNum());
		} catch (IllegalArgumentException e) {
			return false;
		}

		if (list.size() == 0) {
			list.add(0, t);
			incNextTaskNum();
			t.addObserver(this);
			setChanged();
			notifyObservers(this);
			return true;
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (t.compareTo((Task) list.get(i)) < 0) {
					list.add(i, t);
					incNextTaskNum();
					t.addObserver(this);
					setChanged();
					notifyObservers(this);
					return true;
				}
			}
			list.add(list.size(), t);
			incNextTaskNum();
			t.addObserver(this);
			setChanged();
			notifyObservers(this);
			return true;
		}

	}

	/**
	 * Gets the task at a given index in the list. If the index is outside the
	 * allowable bounds, an IndexOutOfBoundsException is thrown
	 * 
	 * @param index
	 *            the index of the Task to retrieve
	 * @return the task at the index
	 */
	public Task getTaskAt(int index) {
		if (index < 0 || index >= list.size()) {
			throw new IndexOutOfBoundsException();
		}
		return (Task) list.get(index);
	}

	/**
	 * Gets the index of the Task with the given id. If the Task is not found in
	 * the List -1 is returned
	 * 
	 * @param id
	 *            the id of the Task to find the index of
	 * @return the index of the given Task
	 */
	public int indexOf(String id) {
		if (id == null || list.size() == 0) {
			return -1;
		}
		for (int i = 0; i < list.size(); i++) {
			if (id.equals(((Task) list.get(i)).getTaskID())) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return the size of the list
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Returns if the list is empty
	 * 
	 * @return true if the list is empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Removes the task at the given index. If the index is outside the
	 * allowable bounds an IndexOutOfBoundsException is thrown.
	 * 
	 * @param index
	 *            the index of the Task to remove
	 * @return the task that was removed
	 */
	public Task removeTaskAt(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Task t = (Task) list.remove(index);
		setChanged();
		notifyObservers(this);
		t.deleteObserver(this);
		return t;
	}

	/**
	 * Removes the task that has the ID given
	 * 
	 * @param taskID
	 *            the id of the Task to remove
	 * @return true if the Task is removed from the list
	 */
	public boolean removeTask(String taskID) {
		if (taskID == null) {
			return false;
		}
		for (int i = 0; i < size(); i++) {
			if (taskID.equals(((Task) list.get(i)).getTaskID())) {
				Task t = (Task) list.remove(i);
				setChanged();
				notifyObservers(this);
				t.deleteObserver(this);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the 2d object array of the Task data
	 * 
	 * @return the 2d array
	 */
	public Object[][] get2DArray() {
		Object[][] array = new Object[size()][8];
		if (size() == 0) {
			return array;
		}
		for (int i = 0; i < size(); i++) {
			array[i][0] = ((Task) list.get(i)).getTaskID();
			array[i][1] = ((Task) list.get(i)).getTitle();
			array[i][2] = ((Task) list.get(i)).getCategory();
			array[i][3] = ((Task) list.get(i)).getStartDateTime();
			array[i][4] = ((Task) list.get(i)).getDueDateTime();
			array[i][5] = ((Task) list.get(i)).getCompletedDateTime();
			array[i][6] = ((Task) list.get(i)).isCompleted();
			array[i][7] = ((Task) list.get(i)).getDetails();
		}
		return array;
	}

	/**
	 * Notifies observers if a change is made in an observed Task
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
