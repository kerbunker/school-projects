/**
 * 
 */
package edu.ncsu.csc216.tracker.command;

/**
 * Encapsulates the User actions of the Ticket Tracker system
 * @author Kbunker
 *
 */
public class Command {
	/** Static String for DUPLICATE flag */
	public static final String F_DUPLICATE = "Duplicate";
	/** Static String for INAPPROPRIATE flag */
	public static final String F_INAPPROPRIATE = "Inappropriate";
	/** Static String for RESOLVED flag */
	public static final String F_RESOLVED = "Resolved";
	/** The Flag for the Command */
	private Flag flag;
	/** The CommandValue for the command */
	private CommandValue c;
	/** The owner for the command */
	private String owner;
	/** The note Text */
	private String noteText;
	/** The note author */
	private String noteAuthor;
	
	/**
	 * The Constructor for the Command object. 
	 * @param value the value of the Command
	 * @param owner the owner of the Command
	 * @param flag the flag for the Command
	 * @param noteAuthor the note's author given with the Command
	 * @param noteText then note's text given with the command
	 */
	public Command(CommandValue value, String owner, Flag flag, String noteAuthor, String noteText) {
		setCommandValue(value);
		setNoteAuthor(noteAuthor);
		setNoteText(noteText);
		setOwner(owner);
		setFlag(flag);
		
	}
	
	/**
	 * Returns the command for the CommandValue
	 * @return the value of the Command enum
	 */
	public CommandValue getCommand() {
		return c;
	}
	
	/**
	 * Returns the owner for the Command
	 * @return the owner of the command
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Returns the flag for the command
	 * @return the flag value
	 */
	public Flag getFlag() {
		return flag;
	}
	
	/**
	 * Returns the text for the note
	 * @return the note's text
	 */
	public String getNoteText() {
		return noteText;
	}
	
	/**
	 * Returns the noteAuthor
	 * @return the note Author
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}
	
	/**
	 * sets the Command Value. Checks that the CommandValue field is not null. Otherwise an IllegalArgumentException is thrown
	 * @param value the value for the CommandValue enum
	 */
	private void setCommandValue(CommandValue value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		c = value;
	}
	
	/**
	 * Sets the Note Author field. If the field is null or an empty String, an IllegalArgumentException is thrown
	 * @param noteAuthor the notes Author
	 */
	private void setNoteAuthor(String noteAuthor) {
		if (noteAuthor == null || noteAuthor.equals("")) {
			throw new IllegalArgumentException();
		}
		this.noteAuthor = noteAuthor;
	}
	
	/**
	 * Sets the note Text field. If the field is null or an empty String, an IllegalArgumentException is thrown
	 * @param noteText the text for the Note
 	 */
	private void setNoteText(String noteText) {
		if (noteText == null || noteText.equals("")) {
			throw new IllegalArgumentException();
		}
		this.noteText = noteText;
		
	}
	
	/**
	 * Sets the owner for the Command. If the CommandValue is POSSESSION and the owner is null or an empty string, an IllegalArgumentException is thrown
	 * @param owner the owner of the Ticket to add to the Ticket
	 */
	private void setOwner(String owner) {
		if (c == CommandValue.POSSESSION && (owner == null || owner.equals(""))) {
			throw new IllegalArgumentException();	
		}
		this.owner = owner;
	}
	
	/**
	 * Sets the Flag for the Command if the Command is to close the Ticket. If the CommandValue is CLOSED and the Flag is null an IllegalARgumentException is thrown
	 * @param flag the Flag associated with the Closed Ticket
	 */
	private void setFlag(Flag flag) {
		if (c == CommandValue.CLOSED && flag == null) {
			throw new IllegalArgumentException();
		}
		this.flag = flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * The flag for the CLOSED Ticket
	 * @author Kbunker
	 *
	 */
	public enum Flag {
		/** Flag for a Duplicate closed ticket */
		DUPLICATE,
		/** Flag for an Inappropriate closed ticket */
		INAPPROPRIATE,
		/** Flag for a Resolved closed ticket */
		RESOLVED
		
	}
	
	/**
	 * The CommandValue for the Command given
	 * @author Kbunker
	 *
	 */
	public enum CommandValue {
		/** enum for the Possession command value */
		POSSESSION,
		/** enum for the Accepted command value */
		ACCEPTED,
		/** enum for the Closed command value */
		CLOSED,
		/** enum for the Progress command value */
		PROGRESS,
		/** enum for the Feedback command value */
		FEEDBACK
	}

}
