/**
 * 
 */
package edu.ncsu.csc216.tracker.ticket;

/**
 * Creates a note for the Ticket Tracker system and keeps track of the Note's author and the Text
 * @author Katelyn
 *
 */
public class Note {
	
	/** Note's Author */
	private String noteAuthor;
	/** Note's text contents */
	private String noteText;

	/**
	 * Constructs the Note and sets the NoteAuthor and NoteText fields
	 * @param noteAuthor the author of the note
	 * @param noteText the contents of the note
	 */
	public Note(String noteAuthor, String noteText) {
		setNoteAuthor(noteAuthor);
		setNoteText(noteText);
	}
	
	/**
	 * Gets the Author of the Note
	 * @return the note's author
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}
	
	/**
	 * Sets the noteAuthor field. If the noteAuthor is null or an empty String an IllegalArgumentException is thrown
	 * @param noteAuthor the author of the note
	 */
	private void setNoteAuthor(String noteAuthor) {
		if (noteAuthor == null || noteAuthor.equals("")) {
			throw new IllegalArgumentException();
		}
		this.noteAuthor = noteAuthor;
	}
	
	/**
	 * Gets the Text contents of the Note
	 * @return the note's text
	 */
	public String getNoteText() {
		return noteText;
	}
	
	/**
	 * Sets the noteText field. If the noteText is null or an empty String an IllegalArgumentException is thrown
	 * @param noteText the text contents of the note to set
	 */
	private void setNoteText(String noteText) {
		if (noteText == null || noteText.equals("")) {
			throw new IllegalArgumentException();
		}
		this.noteText = noteText;
	}
	
	/**
	 * Gets the String array of the note data. 
	 * @return the array of the note information
	 */
	public String[] getNoteArray() {
		String[] a = new String[2];
		a[0] = noteAuthor;
		a[1] = noteText;
		return a;
	}
	
	
	
}
