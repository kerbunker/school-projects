package edu.ncsu.csc216.todolist.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Date;
import java.util.EventListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;

import edu.ncsu.csc216.todolist.model.Category;
import edu.ncsu.csc216.todolist.model.CategoryList;
import edu.ncsu.csc216.todolist.model.Task;

/**
 * The TaskEditPane class
 * @author Kbunker
 *
 */
public class TaskEditPane extends JPanel implements Serializable {

	private static final long serialVersionUID = 5479139338455751629L;
	private TaskData data;
	private CategoryList categories;
	private JTextField taskID;
	private JTextField taskTitle;
	private JComboBox<Category> taskCat;
	private JTextArea taskDetails;
	private JSpinner taskStart;
	private JSpinner taskDue;
	private JSpinner taskCompleted;
	private JCheckBox complete;
	private boolean add;
	private boolean edit;
	
	/**
	 * The TaskEditPane constructor
	 * @param categories the CategoryList to use
	 */
	public TaskEditPane(CategoryList categories) {
		this(new TaskData(), categories);
	}
	
	/**
	 * The more robust TaskEditPaneConstructor
	 * @param data the TaskData to use for the Task
	 * @param list the CategoryList
	 */
	public TaskEditPane(TaskData data, CategoryList list) {
		super();
		this.data = data;
		categories = list;
		add = false;
		edit = false;
		init();
	}
	
	/**
	 * Initialize the data
	 */
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));
		initView();
		//fillFields();
		
	}
	
	/**
	 * Initialize the Panel views
	 */
	private void initView() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Task ID: ", SwingConstants.LEFT));
		p.add(getTaskID());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Task Title: ", SwingConstants.LEFT));
		p.add(getTaskTitle());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Category", SwingConstants.LEFT));
		p.add(getCategory());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Start Date & Time: ", SwingConstants.LEFT));
		p.add(getTaskStartSpinner());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Due Date & Time: ", SwingConstants.LEFT));
		p.add(getTaskDueSpinner());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Completed Date & Time: ", SwingConstants.LEFT));
		p.add(getTaskCompletedSpinner());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Completed? ", SwingConstants.LEFT));
		p.add(getComplete());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Task Details: ", SwingConstants.LEFT));
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(getTaskDetails());
		this.add(p);
		
		
	}
	
	/**
	 * Creates the StartDateTime spinner
	 * @return the spinner
	 */
	JSpinner getTaskStartSpinner() {
		if (taskStart == null) {
			taskStart = new JSpinner(new SpinnerDateModel());
			taskStart.setEditor(new JSpinner.DateEditor(taskStart, "EEE MMM d, yyyy HH:mm"));
			taskStart.setEnabled(false);
			taskStart.setVisible(true);
			
		}
		return taskStart;
	}
	
	/**
	 * Creates the DueDateTime spinner
	 * @return the JSpinner
	 */
	JSpinner getTaskDueSpinner() {
		if (taskDue == null) {
			taskDue = new JSpinner(new SpinnerDateModel());
			taskDue.setEditor(new JSpinner.DateEditor(taskDue, "EEE MMM d, yyyy HH:mm"));
			taskDue.setEnabled(false);
			taskDue.setVisible(true);
		}
		
		return taskDue;
	}
	
	/**
	 * Creates the CompletedDateTime Spinner
	 * @return the JSpinner
	 */
	JSpinner getTaskCompletedSpinner() {
		if (taskCompleted == null) {
			taskCompleted = new JSpinner(new SpinnerDateModel());
			taskCompleted.setEditor(new JSpinner.DateEditor(taskDue, "EEE MMM d, yyyy HH:mm"));
			taskCompleted.setEnabled(false);
			taskCompleted.setVisible(true);
		}
		
		return taskCompleted;
	}
	
	/**
	 * Gets the StartDate from the TaskData
	 * @return the startDate
	 */
	Date getTaskStart() {
		return data.getStartDateTime();
	}
	
	/**
	 * Gets the dueDateTime form the TaskData
	 * @return the dueDateTime
	 */
	Date getTaskDue() {
		return data.getDueDateTime();
	}
	
	/**
	 * Gets the CompletedDateTime
	 * @return the completedDateTime
	 */
	Date getTaskCompleted() {
		return data.getCompletedDateTime();
	}
	
	/**
	 * Gets the JTextField of the TaskId
	 * @return the JTextField
	 */
	JTextField getTaskID() {
		if (null == taskID) {
			taskID = new JTextField(10);
			taskID.setEditable(false);
			taskID.setVisible(true);
			taskID.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return taskID;
	}
	
	/**
	 * Creates the JTextField for the title
	 * @return the JTextField
	 */
	JTextField getTaskTitle() {
		if (taskTitle == null) {
			taskTitle = new JTextField(50);
			taskTitle.setEditable(false);
			taskTitle.setVisible(true);
			taskTitle.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return taskTitle;
	}
	
	/**
	 * Creates the JComboBox for the categories
	 * @return the JComboBox
	 */
	JComboBox<Category> getCategory() {
		if (taskCat == null) {
			taskCat = new JComboBox<Category>();
			for (int i = 0; i < categories.size(); i++) {
				taskCat.addItem(categories.getCategoryAt(i));
			}
			taskCat.setEditable(false);
			taskCat.setVisible(true);
			//taskCat.setHorizontalAlignment(SwingConstants.LEFT);
		}
		
		return taskCat;
	}
	
	/**
	 * Gets the JTextArea for the details
	 * @return the JTextArea
	 */
	JTextArea getTaskDetails() {
		if (taskDetails == null) {
			taskDetails = new JTextArea(5, 70);
			taskDetails.setEditable(false);
			taskDetails.setVisible(true);
			taskDetails.setLineWrap(true);
			taskDetails.setAutoscrolls(true);
		}
		return taskDetails;
	}
	
	/**
	 * Returns the check box for whether the task is complete
	 * @return the JCheckBox
	 */
	JCheckBox getComplete() {
		if (complete == null) {
			complete = new JCheckBox();
			complete.setEnabled(false);
			complete.setVisible(true);
			complete.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return complete;
	}
	
	/**
	 * Sets the start Date
	 * @param startDate the startDate to set
	 */
	void setTaskStart(Date startDate) {
		taskStart.setValue(startDate);
	}
	
	/**
	 * Sets the dueDate
	 * @param dueDate the dueDate to set
	 */
	void setTaskDue(Date dueDate) {
		taskDue.setValue(dueDate);
	}
	
	/**
	 * Sets the completed data
	 * @param completedDate the completedDate to set
	 */
	void setTaskCompleted(Date completedDate) {
		taskCompleted.setValue(completedDate);
	}
	
	/**
	 * Checks if add mode is enabled
	 * @return true if add more is enabled
	 */
	boolean isAddMode() {
		return add;
	}
	
	/**
	 * Checks if edit mode is enabled
	 * @return true if edit mode is enabled
	 */
	boolean isEditMode() {
		return edit;
	}
	
	/**
	 * Enabled add mode
	 */
	void enableAdd() {
		if (!add) {
			add = true;
			edit = false;
			clearFields();
		}
	}
	
	/**
	 * Disables add mode
	 */
	void disableAdd() {
		add = false;
		clearFields();
	}
	
	/**
	 * Enables edit mode
	 * @param data the data to edit
	 */
	void enableEdit(TaskData data) {
		if (!edit) {
			edit = true;
			add = false;
			this.data = data;
			fillFields();
		}
	}
	
	/**
	 * Disables edit mode
	 */
	void disableEdit() {
		edit = false;
		clearFields();
	}
	
	/**
	 * Checks if the fields are empty
	 * @return true if the fields are not empty
	 */
	boolean fieldsNotEmpty() {
		return getTaskTitle().getDocument().getLength() != 0 && getTaskDetails().getDocument().getLength() != 0;
	}
	
	/**
	 * Sets the taskData
	 * @param data the task data to set
	 */
	void setTaskData(TaskData data) {
		this.data = data;
		fillFields();
	}
	
	/**
	 * Adds the listeners to the different fields
	 * @param listener the listener to add to the fields
	 */
	void addFieldListener(EventListener listener) {
		getTaskTitle().getDocument().addDocumentListener((DocumentListener) listener);
		getTaskDetails().getDocument().addDocumentListener((DocumentListener) listener);
		getComplete().addChangeListener((ChangeListener) listener);
		getTaskStartSpinner().addChangeListener((ChangeListener)listener);
		getTaskDueSpinner().addChangeListener((ChangeListener) listener);
		getTaskCompletedSpinner().addChangeListener((ChangeListener) listener);
		getCategory().addActionListener((ActionListener) listener);
	}
	
	/**
	 * Fills the fields with data
	 */
	void fillFields() {
		if (null == data) {
			taskID.setText("");
			taskTitle.setText("");
			taskDetails.setText("");
			complete.setSelected(false);
			taskTitle.setEditable(false);
			taskDetails.setEditable(false);
			taskStart.setEnabled(false);
			taskDue.setEnabled(false);
			taskCompleted.setEnabled(false);
			complete.setEnabled(false);
			taskCat.setEditable(false);
			
			
		} else {
			taskID.setText(data.getTaskID());
			taskTitle.setText(data.getTitle());
			taskDetails.setText(data.getDetails());
			setTaskCompleted(data.getCompletedDateTime());
			setTaskStart(data.getStartDateTime());
			setTaskDue(data.getDueDateTime());
			complete.setSelected(data.isCompleted());
			for (int i = 0; i < categories.size(); i++) {
				taskCat.addItem(categories.getCategoryAt(i));
			}
			
		}
		if (add || edit) {
			taskTitle.setEditable(true);
			taskDetails.setEditable(true);
			taskStart.setEnabled(true);
			taskDue.setEnabled(true);
			taskCompleted.setEnabled(true);
			complete.setEnabled(true);
			taskCat.setEditable(true);
		}
	}
	
	/**
	 * Clears the data
	 */
	void clearFields() {
		data = null;
		fillFields();
	}
	
	/**
	 * gets the data
	 * @return the TaskData
	 */
	TaskData getFields() {
		return new TaskData(taskID.getText(), taskTitle.getText(), taskCat.getItemAt(0), getTaskStart(), getTaskDue(), getTaskCompleted(), complete.isSelected(), taskDetails.getText());
	}
	
	/**
	 * Updates rhe observable
	 * @param observable the observabe to update
	 * @param object the object to notify the observers of
	 */
	public void update(Observable observable, Object object) {
		if (observable instanceof Task) {
			Task t = (Task) observable;
			data = new TaskData(t.getTaskID(), t.getTitle(), t.getCategory(), t.getStartDateTime(), t.getDueDateTime(), t.getCompletedDateTime(), t.isCompleted(), t.getDetails());
			
		}
	}
	
	

}
