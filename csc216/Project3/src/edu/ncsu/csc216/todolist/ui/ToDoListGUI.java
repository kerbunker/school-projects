/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import edu.ncsu.csc216.todolist.*;
import edu.ncsu.csc216.todolist.model.TaskList;

/**
 * ToDoListGUI is the GUI for the ToDoList application.
 * 
 * @author David Wright
 */
public class ToDoListGUI extends JFrame implements ActionListener, WindowListener {

	/** Serial version UID */
	private static final long serialVersionUID = 48371L;
	
	/** Reference to the todoList model */
	private ToDoList todoList;
	/** Reference to the TabbedPane that holds the CategoryTab and TaskTabs */
	private JTabbedPane tabbedPane;
	/** Boolean that tracks if we are opening a ToDoList file */
	private boolean openFile;
	
	/**
	 * Constructs the ToDoListGUI.
	 * @param t the ToDoList model shown by the GUI.
	 */
	public ToDoListGUI(ToDoList t) {
		super("ToDoList");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		todoList = t;
		openFile = false;
		initGUI();
		this.addWindowListener(this);
	}
	
	/**
	 * Returns the ToDoList instance associated with the ToDoList GUI.
	 * @return the ToDoList instance associated with the GUI.
	 */
	public ToDoList getToDoList() {
		return todoList;
	}
	
	/**
	 * Initializes the GUI.
	 */
	private void initGUI() {
		setPreferredSize(new Dimension(1000, 650));
		setJMenuBar(new ToDoListMenuBar(this));
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		initTabbedPane();
	}
	
	/**
	 * Initializes the TabbedPane that contains the CategoryTab and a TaskTab
	 * for each TaskList in the ToDoList system.
	 */
	private void initTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Categories", new CategoryTab(todoList.getCategoryList()));
		for (int i = 0; i < todoList.getNumTaskLists(); i++) {
			TaskList t = todoList.getTaskList(i);
			tabbedPane.addTab(t.getName(), new TaskTab(t, todoList.getCategoryList()));
		}
		tabbedPane.setSelectedIndex(1);
		if (openFile) {
			getContentPane().removeAll();
			openFile = false;
		}
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		getContentPane().validate();
	}
	
	/**
	 * Each command in the JMenuItem is given a number.  The number determines which
	 * action is taken for the given command passed in through the ActionEvent.
	 * @param e ActionEvent representing user's interaction with the GUI.
	 */
	public void actionPerformed(ActionEvent e) {
		if (null != e.getSource() && e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem)(e.getSource());
			String cmd = source.getActionCommand();
			switch (getInt(cmd)) {
				case 11:
					doOpenFile();
					break;
				case 12:
					doSaveFile();
					break;
				case 13:
					doExit();
					break;
				case 21: 
					doRenameTaskList();
					break;
				case 22:
					doNewTaskList();
					break;
				case 23:
					doDeleteTaskList();
					break;
				case 31:
					doAddTask();
					break;
				case 32:
					doDeleteTask();
					break;
				case 41:
					doAddCategory();
					break;
				case 42:
					doDeleteCategory();
					break;
				default:
					break;
			}
		}
	}	

	/**
	 * Provides a JFileChooser so a user can load a previous ToDoList file into the
	 * system.
	 */
	private void doOpenFile() {
		JFileChooser chooser = new JFileChooser("./");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("ToDoList files", "tdl");
	    chooser.setFileFilter(filter);
	    chooser.setMultiSelectionEnabled(false);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	todoList.openDataFile(chooser.getSelectedFile().getName());
	    	openFile = true;
	    	initTabbedPane();
	    }
	}
	
	/**
	 * Provides a JFileChooser so a user can select where to save their file.
	 */
	private void doSaveFile() {
		JFileChooser chooser = new JFileChooser("./");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("ToDoList files", "tdl");
	    chooser.setFileFilter(filter);
	    chooser.setMultiSelectionEnabled(false);
	    int returnVal = chooser.showSaveDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String filename = chooser.getSelectedFile().getName();
	    	todoList.setFilename(filename);
	    	todoList.saveDataFile(filename);
	    }
	}
	
	/** 
	 * If the ToDoList has changed since the last save, the user is prompted
	 * to save the ToDoList.  
	 */
	private void doExit() {
		if (todoList.isChanged()) {
			doSaveFile();
		}
		System.exit(NORMAL);
	}
	
	/**
	 * Adds a new TaskList to the system.
	 */
	private void doNewTaskList() {
		int newList = todoList.addTaskList();
		TaskList t = todoList.getTaskList(newList);
		tabbedPane.addTab(t.getName(), new TaskTab(t, todoList.getCategoryList()));
	}
	
	/**
	 * Renames the selected TaskList.  An error is displayed if the user attempts 
	 * to rename the CategoryList.
	 */
	private void doRenameTaskList() {
		int tab = tabbedPane.getSelectedIndex();
		if (tabbedPane.getComponentAt(tab) instanceof TaskTab) {
			String newName = tabbedPane.getTitleAt(tab);
			boolean loop = true;
			while (loop) {
				newName = (String)JOptionPane.showInputDialog(this, "Edit the name of this Task List",
																newName, JOptionPane.PLAIN_MESSAGE);
				if (null == newName || newName.length() == 0) {
					JOptionPane.showMessageDialog(this, "Task List name cannot be empty.", "Task List Error", JOptionPane.ERROR_MESSAGE);
				}
				if (null != newName) {
					todoList.getTaskList(tab - 1).setName(newName);
					tabbedPane.setTitleAt(tab, newName);
					loop = false;
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Category List cannot be renamed.", "Task List Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Deletes the selected TaskList if it is not the last TaskList in the system.
	 * If the delete is possible, there's a confirmation of the delete.  If there's
	 * a problem with the delete an error message is displayed.
	 */
	private void doDeleteTaskList() {
		int tab = tabbedPane.getSelectedIndex();
		if (0 == tab) {
			JOptionPane.showMessageDialog(this, "Task List not selected.", "Task List Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (1 == todoList.getNumTaskLists()) {
			JOptionPane.showMessageDialog(this, "Cannot delete last Task List.", "Task List Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			int optRes = JOptionPane.showConfirmDialog(this, "Confirm Task List Delete", null, JOptionPane.YES_NO_OPTION);
			if (tab > 0 && JOptionPane.YES_OPTION == optRes) {
				todoList.removeTaskList(tab - 1);
				tabbedPane.remove(tab);
			}
		}	
	}
	
	/**
	 * Handles adding a task to the given TaskTab.  If there is no TaskList selected, 
	 * an error message is displayed. 
	 */
	private void doAddTask() {
		int index = tabbedPane.getSelectedIndex();
		if (tabbedPane.getComponentAt(index) instanceof TaskTab) {
			((TaskTab)tabbedPane.getSelectedComponent()).addNewTask();
		}
		else {
			JOptionPane.showMessageDialog(this, "Task List not selected.", "Task List Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	/**
	 * Handles deleting the selected task in the given TaskTab.  If there is no TaskList selected,
	 * an error message is displayed.
	 */
	private void doDeleteTask() {
		int index = tabbedPane.getSelectedIndex();
		if (0 != index) {
			((TaskTab)tabbedPane.getSelectedComponent()).deleteTask();
		}
		else {
			JOptionPane.showMessageDialog(this, "Task List not selected.", "Task List Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Handles the add command for a category.
	 */
	private void doAddCategory() {
		//Updates the tab to the category tab (index 0)
		tabbedPane.setSelectedIndex(0);
		((CategoryTab)tabbedPane.getSelectedComponent()).addNewCategory();
	}
	
	/**
	 * Handles the delete command for a category.  If there's no category selected,
	 * an error message is displayed.
	 */
	private void doDeleteCategory() {
		int index = tabbedPane.getSelectedIndex();
		if (0 != index) {
			JOptionPane.showMessageDialog(this, "Category List not selected.", "Category List Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			((CategoryTab)tabbedPane.getSelectedComponent()).deleteCategory();
		}
	}
	
	/**
	 * Helper method for converting a string to an int. Since all ints in
	 * program should be positive, the method returns -1 on error.
	 * @param s String to convert
	 * @return integer value 
	 */
	private int getInt(String s) {
		try {
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Method inherited from WindowListener.  Not used in this implementation.
	 * @param e WindowEvent 
	 */
	public void windowOpened(WindowEvent e) {
		// not interested in this event
	}

	/**
	 * Method inherited from WindowListener.  When the window is closing, the 
	 * doExit() method will be called to handle the option for saving on exit.
	 * If the cancel button is pressed in the FileChooser, the program will
	 * exit without saving.
	 * @param e WindowEvent 
	 */
	public void windowClosing(WindowEvent e) {
		doExit();
	}

	/**
	 * Method inherited from WindowListener.  Not used in this implementation.
	 * @param e WindowEvent 
	 */
	public void windowClosed(WindowEvent e) {
		// not interested in this event
	}

	/**
	 * Method inherited from WindowListener.  Not used in this implementation.
	 * @param e WindowEvent 
	 */
	public void windowIconified(WindowEvent e) {
		// not interested in this event
	}

	/**
	 * Method inherited from WindowListener.  Not used in this implementation.
	 * @param e WindowEvent 
	 */
	public void windowDeiconified(WindowEvent e) {
		// not interested in this event
	}

	/**
	 * Method inherited from WindowListener.  Not used in this implementation.
	 * @param e WindowEvent 
	 */
	public void windowActivated(WindowEvent e) {
		// not interested in this event
	}

	/**
	 * Method inherited from WindowListener.  Not used in this implementation.
	 * @param e WindowEvent 
	 */
	public void windowDeactivated(WindowEvent e) {
		// not interested in this event
	}

	/**
	 * Starts the application.
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	private static void createAndShowGUI() {
		ToDoList tdl = new ToDoList();
		ToDoListGUI frame = new ToDoListGUI(tdl);
		//Display the window.
        frame.pack();
        frame.setVisible(true);
	}

}
