package edu.ncsu.csc216.todolist.ui;

import javax.swing.table.AbstractTableModel;

/**
 * CategoryTableModel is a wrapper for the information in CategoryList that 
 * can be used by a JTable. 
 * 
 * @author David R. Wright
 * @author Sarah Heckman
 */
class CategoryTableModel extends AbstractTableModel {
	/** Serial version UID */
	private static final long serialVersionUID = 5954551753060998701L;
	
	/** Names for each of the columns in the TableModel */
	private String[] colNames = {"ID", "Category Name", "Category Description"};
	/** Array of CategoryList information */
	private Object[][] data;
	
	/** 
	 * Creates the model from the given data.
	 * @param data the data to populate the TableModel
	 */
	public CategoryTableModel(Object[][] data) {
		super();
		this.data = data;
	}
	
	/**
	 * Returns the number of rows in the data.
	 * @return the number of rows in the data
	 */
	public int getRowCount() {
		return data.length;
	}
	
	/**
	 * Returns the number of columns in the data.
	 * @return the number of columns in the data 
	 */
	public int getColumnCount() {
		return colNames.length;
	}
	
	/**
	 * Returns the column name at the given index.
	 * @param col the index for finding the column name
	 * @return the column name at the given index
	 */
	public String getColumnName(int col) {
		return colNames[col];
	}
	
	/**
	 * Returns the value at the given cell in the TableModel.
	 * @param row the index for the row
	 * @param col the index for the column
	 * @return the value in the data at the given row and col
	 */
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	/**
	 * Sets the value to the given cell in the TableModel.
	 * @param value the value to set
	 * @param row the index for the row
	 * @param col the index for the column
	 */
	public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);	
	}
	
	/**
	 * Returns the CategoryData object associated with the given
	 * row in the TableModel.
	 * @param row the CategoryData to return
	 * @return the CategoryData for the given row
	 */
	public CategoryData getCategoryRowData(int row) {
		return new CategoryData((String)data[row][0], (String)data[row][1], (String)data[row][2]);
	}
	
	/**
	 * Sets the given row with the provided CategoryData.
	 * @param d CategoryData to set in the row
	 * @param row the row to set
	 */
	public void setCategoryRowData(CategoryData d, int row) {
		setValueAt(d.getCategoryID(), row, 0);
		setValueAt(d.getCategoryName(), row, 1);
		setValueAt(d.getCategoryDesc(), row, 2);
	}
    
}

