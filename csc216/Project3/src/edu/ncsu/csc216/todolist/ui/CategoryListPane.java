/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.Color;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.*;

import java.util.*;

import edu.ncsu.csc216.todolist.model.CategoryList;

/**
 * Maintains the list of Categories.
 * @author David R. Wright
 * @author Sarah Heckman
 */
class CategoryListPane extends JScrollPane implements Observer {

	/** Serial version UID */
	private static final long serialVersionUID = 9036210048399941619L;
	
	/** List of categories to display */
	private CategoryList categoryList;
	/** CategoryTableModel which displays the list of categories */
	private CategoryTableModel categoryTableModel;
	/** Table for the categories */
	private JTable table;
	/** Widths of columns */
	private int[] colWidths = {50, 250, 750};
	
	/**
	 * Creates the CategoryListPane that shows the CategoryData in a table.
	 * @param categoryList CategoryList of Category objects
	 */
	public CategoryListPane(CategoryList categoryList) {
		super();
		this.categoryList = categoryList;
		//Register as an observer of CategoryList so that the pane is updated of
		//any changes to the CategoryList.
		this.categoryList.addObserver(this);
		categoryTableModel = new CategoryTableModel(categoryList.get2DArray());
		initView();
	}
	
	/**
	 * Clears the selection.
	 */
	public void clearSelection() {
		table.clearSelection();
	}
	
	/**
	 * Returns the CategoryTableModel.
	 * @return the CategoryTableModel
	 */
	public CategoryTableModel getCategoryTableModel() {
		return categoryTableModel;
	}
	
	/**
	 * Returns the JTable.
	 * @return the JTable
	 */
	public JTable getTable() {
		return table;
	}
	
	/**
	 * Initializes the view by creating the JTable (that is wrapped in  JScrollPane)
	 * and associating the JTable with the CategoryTableModel.
	 */
	private void initView() {
		//Associates the CategoryTableModel with the JTable.
		//The CategoryTableModel contains the data that the JTable will display.
		table = new JTable(categoryTableModel);
		//Set up the column widths so the table will look nice.
		for (int i = 0; i < colWidths.length; i++) {
			TableColumn col = table.getColumnModel().getColumn(i);
			col.setPreferredWidth(colWidths[i]);
		}
		//Set the table so that only one row can be selected at a time.
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(false);
		setViewportView(table);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * This method is called by the observed object, whenever the observed object
	 * is changed.  In this case, the observed object is the CategoryList. Any changes 
	 * to the CategoryList will lead to an update of the CategoryTableModel.
	 * @param o the observable object
	 * @param arg any additional information needed about the change.
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof CategoryList) {
			CategoryList cl = (CategoryList)o;
			//If there is a different number of rows, create a show new CategoryTableModel.
			if (cl.size() != categoryTableModel.getRowCount()) {
				 categoryTableModel = new CategoryTableModel(cl.get2DArray());
				 table.setModel(categoryTableModel);
			} else {
				//Otherwise, just update the values directly.
				Object[][] arr = cl.get2DArray();
				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < categoryTableModel.getColumnCount(); j++) {
						categoryTableModel.setValueAt(arr[i][j], i, j);
					}
				}
			}
		}
	}
	
	
}
