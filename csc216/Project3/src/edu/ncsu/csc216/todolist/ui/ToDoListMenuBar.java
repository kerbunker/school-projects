/**
 * 
 */
package edu.ncsu.csc216.todolist.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * The ToDoListMenu bar that holds all major ToDoList operations.
 * @author David R. Wright
 */
public class ToDoListMenuBar extends JMenuBar {
	
	/** Serial version UID */
	private static final long serialVersionUID = 49171L;
	
	/** Names for the File menu items */
	private static final String[] FILE_MENU_NAMES = {"Open File", "Save File", "Exit"};
	/** Keyboard shortcuts for the File menu items */
	private static final int[] FILE_MENU_KEYS = {KeyEvent.VK_O, KeyEvent.VK_S, KeyEvent.VK_X};
	/** ActionCommand for File menu items */
	private static final String[] FILE_MENU_COMMANDS = {"11", "12", "13"};
	/** Names for the TaskList menu items */
	private static final String[] TASKLIST_MENU_NAMES = {"Rename Task List", "New Task List", "Delete Task List"};
	/** Keyboard shortcuts for the TaskList menu items */
	private static final int[] TASKLIST_MENU_KEYS = {KeyEvent.VK_R, KeyEvent.VK_N, KeyEvent.VK_D};
	/** ActionCommands for TaskList menu items */
	private static final String[] TASKLIST_MENU_COMMANDS = {"21", "22", "23"};
	/** Names for the Task menu items */
	private static final String[] TASK_MENU_NAMES = {"Add New Task", "Delete Task"};
	/** Keyboard shortcuts for the Task menu items */
	private static final int[] TASK_MENU_KEYS = {KeyEvent.VK_A, KeyEvent.VK_D};
	/** ActionCommands for Task menu items */
	private static final String[] TASK_MENU_COMMANDS = {"31", "32"};
	/** Names for the Category menu items */
	private static final String[] CATEGORY_MENU_NAMES = {"Add Category", "Delete Category"};
	/** Keyboard shortcuts for the Category menu items */
	private static final int[] CATEGORY_MENU_KEYS = {KeyEvent.VK_A, KeyEvent.VK_D};
	/** ActionCommands for the Category menu items */
	private static final String[] CATEGORY_MENU_COMMANDS = {"41", "42"};
	/** Reference to ToDoListGUI */
	private ToDoListGUI parent;
	
	/**
	 * Creates the ToDoListMenuBar for the ToDoListGUI.
	 * @param parent the ToDoListGUI that created the ToDoListMenuBar
	 */
	public ToDoListMenuBar(ToDoListGUI parent) {
		super();
		this.parent = parent;
		initMenu();
	}
	
	/**
	 * Initializes the Menu bar by adding each of the Menues.
	 */
	private void initMenu() {
		this.add(fileMenu());
		this.add(taskListMenu());
		this.add(taskMenu());
		this.add(categoryMenu());
	}
	
	/**
	 * Creates the File menu.
	 * @return the File menu
	 */
	private JMenu fileMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		addMenuItems(menu, FILE_MENU_NAMES, FILE_MENU_KEYS, FILE_MENU_COMMANDS);
		return menu;
	}
	
	/**
	 * Creates the TaskList menu
	 * @return the TaskList menu
	 */
	private JMenu taskListMenu() {
		JMenu menu = new JMenu("Task List");
		menu.setMnemonic(KeyEvent.VK_L);
		addMenuItems(menu, TASKLIST_MENU_NAMES, TASKLIST_MENU_KEYS, TASKLIST_MENU_COMMANDS);
		return menu;
	}
	
	/**
	 * Creates the Task menu
	 * @return the Task menu
	 */
	private JMenu taskMenu() {
		JMenu menu = new JMenu("Tasks");
		menu.setMnemonic(KeyEvent.VK_T);
		addMenuItems(menu, TASK_MENU_NAMES, TASK_MENU_KEYS, TASK_MENU_COMMANDS);
		return menu;
	}
	
	/**
	 * Creates the Category menu
	 * @return the Category menu
	 */
	private JMenu categoryMenu() {
		JMenu menu = new JMenu("Categories");
		menu.setMnemonic(KeyEvent.VK_C);
		addMenuItems(menu, CATEGORY_MENU_NAMES, CATEGORY_MENU_KEYS, CATEGORY_MENU_COMMANDS);
		return menu;
	}
	
	/**
	 * Creates MenuItems with the given names, hot keys, and command values and adds
	 * them to the given Menu.
	 * @param menu JMenu to add MenuItems to
	 * @param itemNames name for the MenuItems
	 * @param hotkeys keyboard shortcut for the MenuItems
	 * @param cmds command strings for the MenuItems
	 */
	private void addMenuItems(JMenu menu, String[] itemNames, int[] hotkeys, String[] cmds) {
		for (int i = 0; i < itemNames.length; i++) {
			JMenuItem item = new JMenuItem(itemNames[i], hotkeys[i]);
			item.setActionCommand(cmds[i]);
			item.addActionListener(parent);
			menu.add(item);
		}
	}
}
