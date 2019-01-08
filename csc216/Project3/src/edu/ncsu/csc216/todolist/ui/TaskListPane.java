package edu.ncsu.csc216.todolist.ui;

import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.todolist.model.TaskList;

/**
 * The TaskListPane class
 * @author Katelyn
 *
 */
public class TaskListPane extends JScrollPane implements Serializable, Observer {
	
	/** Id for the serializable object */
	private static final long serialVersionUID = -2210716111020406799L;
	/** The TaskTableModel */
	private TaskTableModel ttm;
	/** The Tasks for the Pane */
	private TaskList tasks;
	/** The JTable to add the tasks to */
	private JTable table;
	/** The column widths for the pane */
	private int[] colWidths = {50, 200, 100, 100, 100, 100};
	
	/**
	 * Constructs the TaskEditPane with the data from the TaskList
	 * @param taskList the data to add to the pane
	 */
	public TaskListPane(TaskList taskList) {
		super();
		tasks = taskList;
		this.tasks.addObserver(this);
		ttm = new TaskTableModel(tasks.get2DArray());
		initView();
	}
	
	/**
	 * Gets the TaskTableModel
	 * @return the ttm
	 */
	public TaskTableModel getTaskTableModel() {
		return ttm;
	}
	
	/**
	 *  Gets the table
	 * @return the JTable
	 */
	public JTable getTable() {
		return table;
	}
	
	/**
	 * Initializes the view of the listPane
	 */
	private void initView() {
		table = new JTable(ttm);
		for (int i = 0; i < colWidths.length; i++) {
			TableColumn col = table.getColumnModel().getColumn(i);
			col.setPreferredWidth(colWidths[i]);
		}
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(false);
		setViewportView(table);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Clears the data on the pane
	 */
	public void clearSelection() {
		table.clearSelection();
	}
	
	/**
	 * Updates the data on the pane
	 * 
	 * @param o the observable to update
	 * @param arg the object to notify the observers of
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof TaskList) {
			TaskList tl = (TaskList) o;
			if (tl.size() != ttm.getRowCount()) {
				ttm = new TaskTableModel(tl.get2DArray());
				table.setModel(ttm);
 			} else {
 				Object[][] arr = tl.get2DArray();
 				for (int i = 0; i < arr.length; i++) {
 					for (int j = 0; j < ttm.getColumnCount(); j++) {
 						ttm.setValueAt(arr[i][j], i, j);
 					}
 				}
 			}
		}
	}

}
