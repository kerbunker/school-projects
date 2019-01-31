/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ncsu.csc216.todolist.model.CategoryList;
import edu.ncsu.csc216.todolist.model.Category;

/**
 * Represents the tab containing the list of Categories in the ToDoList application.
 * 
 * @author David R. Wright
 * @author Sarah Heckman
 */
class CategoryTab extends JPanel implements DocumentListener, ActionListener, ListSelectionListener {

	/** Serial version UID */
	private static final long serialVersionUID = 1077971879823499611L;

	/** CategoryListPane in the tab (upper half) */
	private CategoryListPane listPane;
	/** CategoryEditPane in the tab (lower half) */
	private CategoryEditPane editPane;
	/** EditButtonPanel that holds all actions for the tab */
	private EditButtonPanel editButtonPanel;
	/** Flag if in add mode */
	private boolean addMode;
	/** Flag if in edit mode */
	private boolean editMode;
	/** Reference to the ToDoList CategoryList */
	private CategoryList categoryList;
	
	/**
	 * Constructs the CategoryTab with the given CategoryList.
	 * @param categoryList list of Categories to display
	 */
	public CategoryTab(CategoryList categoryList) {
		super();
		this.categoryList = categoryList;
		
		//Initially there is no add/edit mode because the user hasn't selected an action
		addMode = false;
		editMode = false;
		
		//Create the CategoryListPane (table)
		listPane = new CategoryListPane(this.categoryList);
		//Add a ListSelectionListener to the listPane so that CategoryTab
		//can respond to selection events.
		listPane.getTable().getSelectionModel().addListSelectionListener(this);
		
		//Creates the CategoryEditPane (form)
		editPane = new CategoryEditPane();
		//Adds a FieldListener to the editPane so that CategoryTab can respond
		//to events in fields that are part of the CategoryEditPane.
		editPane.addFieldListener(this);
		
		//Sets the layout for the tab and adds the element.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(listPane);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(editPane);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(getButtons());
	}
	
	/**
	 * Called when the user selects the option to add a new Category.  
	 * Any previously selected Category is cleared, addMode is set to true, and the 
	 * editPane fields are set to enabled, which allows a user to enter text.
	 */
	public void addNewCategory() {
		listPane.clearSelection();
		addMode = true;
		editPane.enableAdd();
	}
	
	/**
	 * Called when the user selects the option to delete a selected Category.
	 * If there is no Category selected, a pop-up is displayed.  If a Category is
	 * selected, the Category is removed from the CategoryList, and the remaining
	 * fields are reset to default (non-editing) states.
	 */
	public void deleteCategory() {
		int row = listPane.getTable().getSelectedRow();
		if (row >= 0) {
			CategoryData d = listPane.getCategoryTableModel().getCategoryRowData(row);
			//Removing the Category from the model.
			categoryList.removeCategory(d.getCategoryID());
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
		if (null == editButtonPanel) {
			editButtonPanel = new EditButtonPanel();
			editButtonPanel.getAddButton().addActionListener(this);
			editButtonPanel.getSaveButton().addActionListener(this);
			editButtonPanel.getCancelButton().addActionListener(this);
			editButtonPanel.setVisible(true);
		}
		return editButtonPanel;
	}
	
	/**
	 * Disables all buttons so that the user cannot select them.  Prevents users from completing
	 * inappropriate actions.
	 */
	private void disableButtons() {
		editButtonPanel.getAddButton().setEnabled(false);
		editButtonPanel.getSaveButton().setEnabled(false);
		editButtonPanel.getCancelButton().setEnabled(false);
	}

	/**
	 * Enables the CategoryTab for adding a new Category if the flag is true.  Otherwise,
	 * the CategoryTab is disabled for adding a new Category.
	 * @param flag true if enabling for add
	 */
	private void enableAdd(boolean flag) {
		if (flag) {
			editButtonPanel.getAddButton().setEnabled(true);
			editButtonPanel.getSaveButton().setEnabled(false);
			editButtonPanel.getCancelButton().setEnabled(true);
			editPane.enableAdd();
			listPane.clearSelection();
		} else {
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
			editButtonPanel.getAddButton().setEnabled(false);
			editButtonPanel.getSaveButton().setEnabled(true);
			editButtonPanel.getCancelButton().setEnabled(true);
		} else {
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
			} else if (editMode) {
				enableSave(true);
			}
		} else {
			disableButtons();
		}
	}
	
	/**
	 * Method inherited from DocumentListener.  If there's an insert, handleDocEvent()
	 * is called.
	 * @param e DocuementEvent from the user editing a field in CategoryTab
	 */
	public void insertUpdate(DocumentEvent e) {
		handleDocEvent(e);
	}

	/**
	 * Method inherited from DocumentListener.  If there's a remove, handleDocEvent()
	 * is called.
	 * @param e DocuementEvent from the user editing a field in CategoryTab
	 */
	public void removeUpdate(DocumentEvent e) {
		handleDocEvent(e);
	}

	/**
	 * Method inherited from DocumentListener.  If there's a change, handleDocEvent()
	 * is called.
	 * @param e DocuementEvent from the user editing a field in CategoryTab
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
			CategoryData d = editPane.getFields();
			editPane.setCategoryData(d);
			//Updates the model by adding the new Category to the CategoryList
			categoryList.addCategory(d.getCategoryName(), d.getCategoryDesc());
			enableAdd(false);
			editPane.disableAdd();
		} else if (editMode && e.getActionCommand().equals("save")) {
			CategoryData d = editPane.getFields();
			editPane.setCategoryData(d);
			//Updates the model by editing the existing category with the new information
			Category cat = categoryList.getCategoryAt(categoryList.indexOf(d.getCategoryID()));
			cat.setDescription(d.getCategoryDesc());
			cat.setName(d.getCategoryName());
			listPane.clearSelection();
			enableSave(false);
			editPane.disableEdit();
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
			//There is not a concrete selection yet, so return.
            return;
        }
        int row = listPane.getTable().getSelectedRow();
        if (row >= 0) {
        	//If there's a valid selected row, set up for editing.
        	CategoryData d = listPane.getCategoryTableModel().getCategoryRowData(row);
        	editPane.setCategoryData(d);
        	editMode = true;
        	editPane.enableEdit(d);
        } else {
        	editMode = false;
        }
	}
	
}

