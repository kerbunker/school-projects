package edu.ncsu.csc216.todolist;

import java.io.*;
import java.util.*;

import edu.ncsu.csc216.todolist.model.CategoryList;
import edu.ncsu.csc216.todolist.model.TaskList;

/**
 * The main class for the ToDoList program.  Holds references to the top-level 
 * data structures that contain Task and Category objects and acts as the controller
 * between the model and the GUI presentation view.
 * 
 * @author David Wright
 * @version 1.0
 * 
 */
public class ToDoList extends Observable implements Serializable, Observer {
	
	/** teh id of the serializable object */
	private static final long serialVersionUID = 34992L;
	
	
	
	/**
	 * Increment for increasing the capacity of the array of TaskLists
	 */
	private static final int RESIZE = 3;
	
	/** the tasklists */
	private TaskList[] tasks;
	
	/** The number of task lists */
	private int numLists;
	
	/** The categories */
	private CategoryList categories;
	
	/** The filename to save to */
	private String filename;
	
	/** sets whether anything has changed */
	private boolean changed;
	
	/** The next number for the next created tasklist id */
	private int nextTaskListNum;
	
	/**
	 * Creates the todolist
	 */
	public ToDoList() {
		nextTaskListNum = 1;
		tasks = new TaskList[3];
		tasks[0] = new TaskList("New List", "TL" + getNextTaskListNum());
		numLists++;
		tasks[0].addObserver(this);
		categories = new CategoryList();
		categories.addObserver(this);
		changed = false;
		incNextTaskListNum();
		
	}
	
	/**
	 * Gets whether the todolist has been changed
	 * @return true if changed is true
	 */
	public boolean isChanged() {
		return changed;
	}
	
	/**
	 * Sets whether the list has chnaged or not
	 * @param changed the boolean of whether it has been changed
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	/**
	 * gets the filename
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Sets the filename
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		if (filename == null || filename.equals("")) {
			throw new IllegalArgumentException();
		}
		this.filename = filename;
		
	}
	
	/**
	 * Gets the next task list number for ids
	 * @return the next task list number
	 */
	private int getNextTaskListNum() {
		return nextTaskListNum;
	}
	
	/**
	 * Adds one to the nextTaskListNum
	 */
	private void incNextTaskListNum() {
		nextTaskListNum++;
	}
	
	/**
	 * Gets the number of taskLists
	 * @return the number of task lists
	 */
	public int getNumTaskLists() {
		int count = 0;
		for (int i = 0; i < tasks.length; i++) {
			if (tasks[i] != null) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Gets the tasklist at the given index
	 * @param index the index of the tasklist to find
	 * @return the tasklist at the given index
	 */
	public TaskList getTaskList(int index) {
		if (index < 0 || index >= getNumTaskLists()) {
			throw new IndexOutOfBoundsException();
		}
		
		return tasks[index];
	}
	
	/**
	 * Gets the list of categories
	 * @return the lists of categories
	 */
	public CategoryList getCategoryList() {
		return categories;
	}
	
	/**
	 * Adds a tasklist to the list
	 * @return the index of the tasklist
	 */
	public int addTaskList() {
		TaskList list = new TaskList("New List", "TL" + getNextTaskListNum());
		if (getNumTaskLists() == tasks.length) {
			TaskList[] tasks2 = new TaskList[tasks.length + 3];
			for (int i = 0; i < getNumTaskLists(); i++) {
				tasks2[i] = tasks[i];
			}
			tasks = tasks2;
		}
		int index = getNumTaskLists();
		tasks[index] = list;
		numLists++;
		incNextTaskListNum();
		list.addObserver(this);
		setChanged();
		notifyObservers(this);
		return index;
	}
	
	/**
	 * Removes the task list
	 * @param index the index of the tasklist to remove
	 */
	public void removeTaskList(int index) {
		if (index < 0 || index >= numLists) {
			throw new IndexOutOfBoundsException();
		}
		TaskList list = tasks[index];
		for (int i = index; i < numLists; i++) {
			tasks[i] = tasks[i + 1];
		}
		numLists--;
		list.deleteObserver(this);
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * Saves the CategoryList and the array of TaskLists to the given file using 
	 * object serialization.  
	 * @param fname filename to save ToDoList information to.
	 */
	public void saveDataFile(String fname) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fname);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for (int i = 0; i < numLists; i++) {
				out.writeObject(tasks[i]);
			}
			out.writeObject(categories);
			out.writeObject(filename);
			out.writeInt(nextTaskListNum);
			changed = false;
			out.close();
			fileOut.close();
		}
		catch (IOException e) {
			System.err.println("An error occurred while saving file " + fname);
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * Opens a data file with the given name and creates the data structures from 
	 * the serialized objects in the file.
	 * @param fname filename to create ToDoList information from.
	 */
	public void openDataFile(String fname) {
		if (changed) {
			saveDataFile(filename);
		}
		try {
			FileInputStream fileIn = new FileInputStream(fname);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<TaskList> temp = new ArrayList<TaskList>();
			Object tl = in.readObject();
			while (tl instanceof TaskList) {
				TaskList l = (TaskList)tl;
				l.addObserver(this);
				temp.add(l);
				tl = in.readObject();
			}
			tasks = new TaskList[RESIZE];
			tasks = temp.toArray(tasks);
			numLists = temp.size();
			categories = (CategoryList)tl;
			categories.addObserver(this);
			filename = (String)in.readObject();
			nextTaskListNum = (int)in.readInt();
			changed = false;
			in.close();
			fileIn.close();
			
		}
		catch (IOException e) {
			System.err.println("An error occurred while reading file " + fname);
			e.printStackTrace(System.err);
		}
		catch (ClassNotFoundException c) {
			System.err.println("Error reconstructing ToDoList from file " + fname);
			c.printStackTrace(System.err);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o.equals(categories)) {
			changed = true;
			setChanged();
			notifyObservers(arg);
		}
		for (int i = 0; i < getNumTaskLists(); i++) {
			if (tasks[i].equals(o)) {
				changed = true;
				setChanged();
				notifyObservers(arg);
			}
		}
		
	}

}
