package edu.ncsu.csc216.todolist.ui;

import java.io.Serializable;
import java.util.Date;
import edu.ncsu.csc216.todolist.model.Category;

import javax.swing.table.AbstractTableModel;

/**
 * The TaskTableModel class
 * @author Katelyn
 *
 */
public class TaskTableModel extends AbstractTableModel implements Serializable {

	/** Id for serialiable object */
	private static final long serialVersionUID = 5954551753060998701L;
	/** Column names */
	private String[] colNames = { "ID", "Title", "Start Date", "Due Date", "Completed Date", "Complete", "Category" };
	/** Column data */
	private Object[][] data;

	/**
	 * Constructs the TaskTableModel
	 * 
	 * @param array
	 *            the array of data
	 */
	public TaskTableModel(Object[][] array) {
		super();
		data = array;
	}

	/**
	 * Gets the number of rows
	 * 
	 * @return int the number of rows
	 */
	public int getRowCount() {
		return data.length;
	}

	/**
	 * Gets the number of columns
	 * 
	 * @return the number of columns
	 */
	public int getColumnCount() {
		return colNames.length;
	}

	/**
	 * gets the column name
	 * 
	 * @param col
	 *            the column to find the name of
	 * @return the column name
	 */
	public String getColumnName(int col) {
		return colNames[col];
	}

	/**
	 * Gets the value at the specified row and column
	 * 
	 * @param row
	 *            the row to get the data from
	 * @param col
	 *            the column to get the data from
	 * @return the object at the specified location
	 */
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/**
	 * Sets the value at the given location
	 * 
	 * @param value
	 *            the object the set at the location
	 * @param row
	 *            the row of the location
	 * @param col
	 *            the column of the location
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

	/**
	 * Gets the data from the row
	 * 
	 * @param row
	 *            the row to get the data from
	 * @return the taskdata from the row
	 */
	public TaskData getTaskRowData(int row) {
		return new TaskData((String) data[row][0], (String) data[row][1], (Category) data[row][2], (Date) data[row][3],
				(Date) data[row][4], (Date) data[row][5], (boolean) data[row][6], (String) data[row][7]);
	}

	/**
	 * Sets the data for the row from the given data
	 * 
	 * @param row
	 *            the row to set the data of
	 * @param data
	 *            the data to set
	 */
	public void setTaskRowData(int row, TaskData data) {
		setValueAt(data.getTaskID(), row, 0);
		setValueAt(data.getTitle(), row, 1);
		setValueAt(data.getCategory(), row, 2);
		setValueAt(data.getStartDateTime(), row, 3);
		setValueAt(data.getDueDateTime(), row, 4);
		setValueAt(data.getCompletedDateTime(), row, 5);
		setValueAt(data.isCompleted(), row, 6);
		setValueAt(data.getDetails(), row, 7);
	}

}
