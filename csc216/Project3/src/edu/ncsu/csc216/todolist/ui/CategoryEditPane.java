/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentListener;

/**
 * Panel for editing Categories.
 * @author David R. Wright
 * @author Sarah Heckman
 */
class CategoryEditPane extends JPanel {
	/** Serial version UID */
	private static final long serialVersionUID = -3960029015921432795L;
	
	/** Represents the current Category being edited */
	private CategoryData data;
	/** Text field for the category id and category name */
	private JTextField txtCategoryId, txtCategoryName;
	/** Text area for the category description. */
	private JTextArea txtCategoryDescription;
	/** CategoryEditPane is in add mode - will add a new Category */
	private boolean isAddCategoryMode;
	/** CategoryEditPane is in edit mode - will edit an existing Category */
	private boolean isEditCategoryMode;
	
	/**
	 * Creates a new edit pane with an empty CategoryData.
	 */
	public CategoryEditPane() {
		this(new CategoryData("", "", ""));
	}
	
	/**
	 * Creates a new edit pane with the given CategoryData.
	 * @param data information to populate the pane with
	 */
	public CategoryEditPane(CategoryData data) {
		super();
		this.data = data;
		isAddCategoryMode = false;
		isEditCategoryMode = false;
		init();
	}
	
	/**
	 * Initializes the CategoryData to the given value.
	 * @param data new CatagorData
	 */
	public void setCategoryData(CategoryData data) {
		this.data = data;
		fillFields();
	}
	
	/**
	 * Initializes the GUI.
	 */
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.black));
		initView();
		fillFields();
	}
	
	/**
	 * Initializes the view.
	 */
	private void initView() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Category ID: ", SwingConstants.LEFT));
		p.add(getCategoryID());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Category Name: ", SwingConstants.LEFT));
		p.add(getCategoryName());
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(new JLabel("Category Description: ", SwingConstants.LEFT));
		this.add(p);
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(getCategoryDesc());
		this.add(p);
	}
	
	/**
	 * Returns the text field for the categoryID.
	 * @return the text field for the categoryID
	 */
	private JTextField getCategoryID() {
		if (null == txtCategoryId) {
			txtCategoryId = new JTextField(10);
			txtCategoryId.setEditable(false);
			txtCategoryId.setVisible(true);
			txtCategoryId.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return txtCategoryId;
	}
	
	/**
	 * Returns the text field for the categoryName.
	 * @return the text field for the categoryName
	 */
	private JTextField getCategoryName() {
		if (null == txtCategoryName) {
			txtCategoryName = new JTextField(50);
			txtCategoryName.setEditable(false);
			txtCategoryName.setVisible(true);
			txtCategoryName.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return txtCategoryName;
	}
	
	/**
	 * Returns the text field for the categoryDesc.
	 * @return the text field for the categoryDesc
	 */
	private JTextArea getCategoryDesc() {
		if (null == txtCategoryDescription) {
			txtCategoryDescription = new JTextArea(5, 70);
			txtCategoryDescription.setEditable(false);
			txtCategoryDescription.setVisible(true);
			txtCategoryDescription.setLineWrap(true);
			txtCategoryDescription.setAutoscrolls(true);
		}
		return txtCategoryDescription;
	}
	
	/**
	 * Adds the given DocumentListener to the txtCategoryName
	 * and txtCategoryDescription text fields.
	 * @param docListener DocumentListener to add to text fields
	 */
	public void addFieldListener(DocumentListener docListener) {
		getCategoryName().getDocument().addDocumentListener(docListener);
		getCategoryDesc().getDocument().addDocumentListener(docListener);
	}

	/**
	 * Fills the fields with the appropriate text from the CategoryData
	 * field.
	 */
	public void fillFields() {
		if (null == data) {
			txtCategoryId.setText("");
			txtCategoryName.setText("");
			txtCategoryDescription.setText("");
			txtCategoryName.setEditable(false);
			txtCategoryDescription.setEditable(false);
		}
		else {
			txtCategoryId.setText(data.getCategoryID());
			txtCategoryName.setText(data.getCategoryName());
			txtCategoryDescription.setText(data.getCategoryDesc());
		}
		if (isAddCategoryMode || isEditCategoryMode) {
			txtCategoryName.setEditable(true);
			txtCategoryDescription.setEditable(true);
		}
		
	}
	
	/**
	 * Clears the fields by setting data to null.
	 */
	public void clearFields() {
		data = null;
		fillFields();
	}
	
	/**
	 * Returns the fields as a CategoryData object.
	 * @return the fields as a CategoryData object
	 */
	public CategoryData getFields() {
		return new CategoryData(getCategoryID().getText(), getCategoryName().getText(), getCategoryDesc().getText());
	}
	
	/**
	 * Returns true if the in add mode.
	 * @return true if the in add mode
	 */
	public boolean isAddMode() {
		return isAddCategoryMode;
	}
	
	/**
	 * Returns true if the in edit mode.
	 * @return true if the in edit mode
	 */
	public boolean isEditMode() {
		return isEditCategoryMode;
	}
	
	/**
	 * Enables add mode and disables edit.
	 */
	public void enableAdd() {
		if (!isAddCategoryMode) {
			isAddCategoryMode = true;
			isEditCategoryMode = false;
			clearFields();	
		}
	}
	
	/**
	 * Disables add mode.
	 */
	public void disableAdd() {
		isAddCategoryMode = false;
		clearFields();
	}
	
	/**
	 * Enables edit mode and disables add.
	 * @param d CategoryData to populate the edit area with
	 */
	public void enableEdit(CategoryData d) {
		if (!isEditCategoryMode) {
			isEditCategoryMode = true;
			isAddCategoryMode = false;
			data = d;
			fillFields();
		}
	}
	
	/**
	 * Disables edit mode.
	 */
	public void disableEdit() {
		isEditCategoryMode = false;
		clearFields();
	}
	
	/**
	 * Returns true if the required fields are not empty.
	 * @return true if the required fields are not empty
	 */
	public boolean fieldsNotEmpty() {
		return getCategoryName().getDocument().getLength() != 0 && getCategoryDesc().getDocument().getLength() != 0;
	}

	
}
