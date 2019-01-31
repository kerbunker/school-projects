package edu.ncsu.csc216.todolist.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

import edu.ncsu.csc216.todolist.model.Task;
import edu.ncsu.csc216.todolist.model.TaskList;
import edu.ncsu.csc216.todolist.model.CategoryList;

/**
 * Represents the tab containing the list of Tasks for a given TaskList in the
 * ToDoList application
 * 
 * @author David R. Wright
 * @author Sarah Heckman
 */
class TaskTab extends JPanel implements ActionListener, ListSelectionListener, DocumentListener, ChangeListener {

	/** Serial version UID */
	private static final long serialVersionUID = 1077971879823499611L;

	/** TaskListPane in the tab (upper half) */
	private TaskListPane listPane;
	/** TaskEditPane in the tab (lower half) */
	private TaskEditPane editPane;
	/** EditButtonPanel that holds all actions for the tab */
	private EditButtonPanel buttonP;
	/** Flag if in add mode */
	private boolean addMode;
	/** Flag is in edit mode */
	private boolean editMode;
	/** TaskList associated with the tab */
	private TaskList taskList;
	
	/**
	 * Constructs the TaskTab with the given TaskList and CategoryList.
	 * @param taskList TaskList for the current tab
	 * @param categoryList CategoryList that will go in the EditPane categories
	 */
	public TaskTab(TaskList taskList, CategoryList categoryList) {
		super();
		this.taskList = taskList;
		
		//Initially there is no add/edit mode because the user hasn't selected an action
		addMode = false;
		editMode = false;
		
		//Create the TaskList Pane (table)
		listPane = new TaskListPane(taskList);
		//Add a ListSelectionListener to the listPane so that TaskTab
		//cna respons to selection events.
		listPane.getTable().getSelectionModel().addListSelectionListener(this);
		
		//Adds the listPane to the TaskList as an observer.  That means
		//that the listPane will be notified if the TaskList changes.
		this.taskList.addObserver(listPane);
		
		//Create the TaskEditPane (form
		editPane = new TaskEditPane(categoryList);
		//Adds a FieldListener to the editPage so that TaskTab can respond
		//to events in fields that are part of the TaskEditPane.
		editPane.addFieldListener(this);
		
		//Sets the layout for the tab and adds the element.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(listPane);
		add(Box.createRigidArea(new Dimension(0, 5)), BorderLayout.CENTER);
		add(editPane);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(getButtons());
	}
	
	/**
	 * Called when the user selects the option to add a new TAsk.  
	 * Any previously selected Task is cleared, addMode is set to true, and the 
	 * editPane fields are set to enabled, which allows a user to enter text.
	 */
	public void addNewTask() {
		listPane.clearSelection();
		addMode = true;
		editPane.enableAdd();
	}
	
	/**
	 * Called when the user selects the option to delete a selected Task.
	 * If there is no Task selected, a pop-up is displayed.  If a Task is
	 * selected, the Task is removed from the TaskList, and the remaining
	 * fields are reset to default (non-editing) states.
	 */
	public void deleteTask() {
		int row = listPane.getTable().getSelectedRow();
		if (row >= 0) {
			TaskData d = listPane.getTaskTableModel().getTaskRowData(row);
			taskList.removeTask(d.getTaskID());
			editPane.clearFields();
			listPane.clearSelection();
			enableAdd(false);
			enableSave(false);
		}
		else {
			JOptionPane.showMessageDialog(this, "Category not selected.", "Category List Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Returns the EditButtonPanel.  If the EditButtonPanel hasn't been created before, 
	 * The current object is added as an ActionListener to each button.
	 * @return a panel of buttons for intereacting with the CategoryTab.
	 */
	private EditButtonPanel getButtons() {
		if (null == buttonP) {
			buttonP = new EditButtonPanel();
			buttonP.getAddButton().addActionListener(this);
			buttonP.getSaveButton().addActionListener(this);
			buttonP.getCancelButton().addActionListener(this);
			buttonP.setVisible(true);
		}
		return buttonP;
	}
	
	/**
	 * Disables all buttons so that the user cannot select them.  Prevents users from completing
	 * inappropriate actions.
	 */
	private void disableButtons() {
		buttonP.getAddButton().setEnabled(false);
		buttonP.getSaveButton().setEnabled(false);
		buttonP.getCancelButton().setEnabled(false);
	}

	/**
	 * Enables the CategoryTab for adding a new Category if the flag is true.  Otherwise,
	 * the CategoryTab is disabled for adding a new Category.
	 * @param flag true if enabling for add
	 */
	private void enableAdd(boolean flag) {
		if (flag) {
			buttonP.getAddButton().setEnabled(true);
			buttonP.getSaveButton().setEnabled(false);
			buttonP.getCancelButton().setEnabled(true);
			editPane.enableAdd();
			listPane.clearSelection();
		}
		else {
			addMode = false;
			disableButtons();
			editPane.disableAdd();
		}
	}
	
	/**
	 * Enables the CategoryTab for saving an edited Category if the flag is true.  Otherwise,
	 * the CategoryTab is disabled for saving an edited Category.
	 * @param flag true if enabling for save
	 */
	private void enableSave(boolean flag) {
		if (flag) {
			buttonP.getAddButton().setEnabled(false);
			buttonP.getSaveButton().setEnabled(true);
			buttonP.getCancelButton().setEnabled(true);
		}
		else {
			editMode = false;
			disableButtons();
			editPane.disableEdit();
		}
	}
	
	/**
	 * A DocumentEvent happens when a user starts typing in the category name or 
	 * category description text fields. If a DocumentEvent occurs, this method 
	 * will enable or disable the appropriate buttons depending on the mode.
	 * @param e DocuementEvent from the user typing in a field in the CategoryTab
	 */
	private void handleDocEvent(DocumentEvent e) {
		if (editPane.fieldsNotEmpty()) {
			if (addMode) {
				enableAdd(true);
			}
			else if (editMode) {
				enableSave(true);
			}
		}
		else {
			disableButtons();
		}
	}
	
	/**
	 * Method inherited from DocumentListener.  If there's an insert, handleDocEvent()
	 * is called.
	 * @param e DocuementEvent from the user editing a field in TaskTab
	 */
	public void insertUpdate(DocumentEvent e) {
		handleDocEvent(e);
	}

	/**
	 * Method inherited from DocumentListener.  If there's a remove, handleDocEvent()
	 * is called.
	 * @param e DocuementEvent from the user editing a field in TaskTab
	 */
	public void removeUpdate(DocumentEvent e) {
		handleDocEvent(e);
	}

	/**
	 * Method inherited from DocumentListener.  If there's a change, handleDocEvent()
	 * is called.
	 * @param e DocuementEvent from the user editing a field in TaskTab
	 */
	public void changedUpdate(DocumentEvent e) {
		handleDocEvent(e);
	}
	
	/**
	 * Method inherited from ActionListener.  If there is an add, save, or cancel action (which
	 * correspond to the three buttons), this method is called.  The model is updated (or not)
	 * depending on the action.
	 * @param e ActionEvent that represents the user's interaction with the GUI.
	 */
	public void actionPerformed(ActionEvent e) {
		if (addMode && e.getActionCommand().equals("add")) {
			TaskData d = editPane.getFields();
			if (!d.getStartDateTime().before(d.getDueDateTime())) {
				JOptionPane.showMessageDialog(this, "Due date must be after start date.", "Task Date Error", JOptionPane.ERROR_MESSAGE);
			} else if (d.isCompleted() && !d.getStartDateTime().before(d.getCompletedDateTime())) {
				JOptionPane.showMessageDialog(this, "Completed date must be after start date.", "Task Date Error", JOptionPane.ERROR_MESSAGE);
			} else if (d.getCategory() == null) {
				JOptionPane.showMessageDialog(this, "Select a category.", "Category Error", JOptionPane.ERROR_MESSAGE);
			} else {
				editPane.setTaskData(d);
				taskList.addTask(d.getTitle(), d.getDetails(), d.getStartDateTime(), d.getDueDateTime(), d.getCategory());
				enableAdd(false);
				editPane.disableAdd();
			}
		} else if (editMode && e.getActionCommand().equals("save")) {
			TaskData d = editPane.getFields();
			if (!d.getStartDateTime().before(d.getDueDateTime())) {
				JOptionPane.showMessageDialog(this, "Due date must be after start date.", "Task Date Error", JOptionPane.ERROR_MESSAGE);
			} else if (d.isCompleted() && !d.getStartDateTime().before(d.getCompletedDateTime())) {
				JOptionPane.showMessageDialog(this, "Completed date must be after start date.", "Task Date Error", JOptionPane.ERROR_MESSAGE);
			} else if (d.getCategory() == null) {
				JOptionPane.showMessageDialog(this, "Select a category.", "Category Error", JOptionPane.ERROR_MESSAGE);
			} else {
				editPane.setTaskData(d);
				Task task = taskList.getTaskAt(taskList.indexOf(d.getTaskID()));
				task.setTitle(d.getTitle());
				task.setCategory(d.getCategory());
				task.setStartDateTime(d.getStartDateTime());
				task.setDueDateTime(d.getDueDateTime());
				task.setCompleted(d.isCompleted());
				task.setCompletedDateTime(d.getCompletedDateTime());
				task.setDetails(d.getDetails());
				listPane.clearSelection();
				enableSave(false);
				editPane.disableEdit();
			}
		} else if (e.getActionCommand().equals("cancel")) {
			editPane.clearFields();
			if (addMode) {
				enableAdd(false);
				editPane.disableAdd();
			}
			if (editMode) {
				listPane.clearSelection();
				enableSave(false);
				editPane.disableEdit();
			}
		}
		
	}
	
	/**
	 * Method inherited from ListSelectionListener.  Anytime the user interacts with the 
	 * JTable that contains the CategoryList (as represented by the CategoryTableModel), this
	 * method will be called. The method will populate the CategoryEditPane fields with 
	 * the Category information.
	 * @param e ListSelectionEvent of a user selecting a row in the table
	 */
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
            return;
        }
        int row = listPane.getTable().getSelectedRow();
        if (row >= 0) {
        	TaskData d = listPane.getTaskTableModel().getTaskRowData(row);
        	editPane.setTaskData(d);
        	editMode = true;
        	editPane.enableEdit(d);
        } else {
        	editMode = false;
        }
	}

	/**
	 * Sets the state as changed for the ChangeListener.
	 * @param e ChangeEvent
	 */
	public void stateChanged(ChangeEvent e) {
		//Not used
	}
}
