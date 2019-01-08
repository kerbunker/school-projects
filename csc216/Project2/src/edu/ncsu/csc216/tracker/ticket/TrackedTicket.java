/**
 * 
 */
package edu.ncsu.csc216.tracker.ticket;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.ticket.xml.NoteItem;
import edu.ncsu.csc216.ticket.xml.NoteList;
import edu.ncsu.csc216.ticket.xml.Ticket;
import edu.ncsu.csc216.tracker.command.Command;
import edu.ncsu.csc216.tracker.command.Command.CommandValue;
import edu.ncsu.csc216.tracker.command.Command.Flag;
import edu.ncsu.csc216.tracker.ticket_tracker.TicketTrackerModel;
import edu.ncsu.csc216.tracker.ticket_tracker.TrackedTicketList;

/**
 * Creates a Tracked Ticket that is passed through the states of the Finite State Machine as the ticket is processed
 * @author Kbunker
 *
 */
public class TrackedTicket {
	
	/** The ticket's id */
	private int ticketId;
	/** The ticket's title */
	private String title;
	/** The ticket's submitter */
	private String submitter;
	/** The ticket's owner */
	private String owner;
	/** The name of the NewState */
	public static final String NEW_NAME = "New";
	/** The name of the AssignedState */
	public static final String ASSIGNED_NAME = "Assigned";
	/** The name of the WorkingState */
	public static final String WORKING_NAME = "Working";
	/** The name of the FeedbackState */
	public static final String FEEDBACK_NAME = "Feedback";
	/** The name of the ClosedState */
	public static final String CLOSED_NAME = "Closed";
	/** The counter for the ticket ids */
	public static int counter;
	/** The ticket's flag */
	private Flag flag;
	/** The ticket's current state */
	private TicketState state;
	/** The placeholder for the workingState */
	private TicketState workingState;
	/** The placeholder for the assignedState */
	private TicketState assignedState;
	/** The placeholder for the FeedbackState */
	private TicketState feedbackState;
	/** The placeholder for the NewState */
	private TicketState newState;
	/** The placeholder for the ClosedState */
	private TicketState closedState;
	/** The list of notes associated with the ticket */
	private ArrayList<Note> notes;
	
	/**
	 * Constructor for the TracketTicket
	 * @param title the ticket's title
	 * @param submitter the ticket's submitter
	 * @param note the ticket's note
	 */
	public TrackedTicket(String title, String submitter, String note) {
		workingState = new WorkingState();
		assignedState = new AssignedState();
		feedbackState = new FeedbackState();
		newState = new NewState();
		closedState = new ClosedState();
		
		this.title = title;
		this.submitter = submitter;
		notes = new ArrayList<Note>();
		notes.add(0, new Note(submitter, note));
		ticketId = counter;
		incrementCounter();
		setState(NEW_NAME);
		setFlag(null);
		
	}
	
	/**
	 * Constructor for the TrackedTicket from an XML ticket
	 * @param ticket the xml ticket to make a TrackedTicket from
	 */
	public TrackedTicket(Ticket ticket) {
		title = ticket.getTitle();
		submitter = ticket.getSubmitter();
		owner = ticket.getOwner();
		setFlag(ticket.getFlag());
		ticketId = ticket.getId();
		List<NoteItem> nList = ticket.getNoteList().getNotes();
		Note n = null;
		for (int i = 0; i < nList.size(); i++) {
			n = new Note(n.getNoteAuthor(), n.getNoteText());
			notes.add(n);
		}
		setState(ticket.getState());
		
		
		
		
		
	}
	
	/**
	 * Adds one to the Counter for the Ticket ids
	 */
	public static void incrementCounter() {
		counter++;
	}
	
	/**
	 * Gets the Ticket's ID
	 * @return the ID of the ticket
	 */
	public int getTicketId() {
		return ticketId;
	}
	
	/**
	 * Gets the name of the current state
	 * @return the state's name
	 */
	public String getStateName() {
		return state.getStateName();
	}
	
	/**
	 * Sets the State to the parameter state
	 * @param state the state to set the state to
	 */
	private void setState(String state) {
		if (state.equals(NEW_NAME)) {
			this.state = newState;
		} else if (state.equals(ASSIGNED_NAME)) {
			this.state = assignedState;
		} else if (state.equals(CLOSED_NAME)) {
			this.state = closedState;
		} else if (state.equals(FEEDBACK_NAME)) {
			this.state = feedbackState;
		} else if (state.equals(WORKING_NAME)) {
			this.state = workingState;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Gets the flag associated with the Ticket
	 * @return the ticket's flag
	 */
	public Flag getFlag() {
		return flag;
	}
	
	/**
	 * Gets the String representation of the flag
	 * @return the flag name
	 */
	public String getFlagString() {
		if (flag == null) {
			return null;
		}
		return flag.name();
	}
	
	/**
	 * Sets the flag to the given parameter
	 * @param flag the flag to set to the Ticket
	 */
	private void setFlag(String flag) {
		if (flag == null) {
			this.flag = null;
		} else if (flag.equals(Command.F_DUPLICATE)) {
			this.flag = Flag.DUPLICATE;
		} else if (flag.equals(Command.F_INAPPROPRIATE)) {
			this.flag = Flag.INAPPROPRIATE;
		} else if (flag.equals(Command.F_RESOLVED)) {
			this.flag = Flag.RESOLVED;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Gets the Ticket's owner
	 * @return the owner of the Ticket
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Gets the Ticket's title
	 * @return the title of the Ticket
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the submitter of the Ticket
	 * @return the submitter of the ticket
	 */
	public String getSubmitter() {
		return submitter;
	}
	
	/**
	 * Gets the array list of Notes
	 * @return the array of notes
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	/**
	 * Updates the Ticket following the command from the user
	 * @param command the command to update the Ticket
	 */
	public void update(Command command) {
		state.updateState(command);
		owner = command.getOwner();
		flag = command.getFlag();
		Note n = new Note(command.getNoteAuthor(), command.getNoteText());
		notes.add(n);
	}
	
	/**
	 * Creates an XML ticket from the TrackedTicket info
	 * @return the XML ticket
	 */
	public Ticket getXMLTicket() {
		Ticket t = new Ticket();
		t.setFlag(flag.name());
		t.setId(ticketId);
		t.setOwner(owner);
		t.setState(state.getStateName());
		t.setSubmitter(submitter);
		t.setTitle(title);
		NoteList nl = new NoteList();
		NoteItem ni = new NoteItem();
		for (int i = 0; i < notes.size(); i++) {
			ni.setNoteAuthor(notes.get(i).getNoteAuthor());
			ni.setNoteText(notes.get(i).getNoteText());
			nl.getNotes().add(ni);
		}
		t.setNoteList(nl);
		
		return t;
	}
	
	/**
	 * Sets the counter
	 * @param count the count to set the counter to
	 */
	public static void setCounter(int count) {
		if (counter <= 0) {
			throw new IllegalArgumentException();
		}
		counter = count;
	}
	
	/**
	 * Gets the 2D array of the notes
	 * @return the note array
	 */
	public String[][] getNotesArray() {
		String[][] array = new String[notes.size()][];
		for (int i = 0; i < notes.size(); i++) {
			array[i] = notes.get(i).getNoteArray();
		}
		return array;
	}
	
	/**
	 * Interface for states in the Ticket State Pattern.  All 
	 * concrete ticket states must implement the TicketState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TicketState {
		
		/**
		 * Update the {@link TrackedTicket} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TrackedTicket}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	
	}
	
	/**
	 * The concrete state for the NewState of the Ticket
	 * @author Kbunker
	 *
	 */
	private class NewState implements TicketState {

		/**
		 * Updates the State to a new State based on the CommandValue
		 * 
		 * @param c the command to update the Ticket
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.POSSESSION) {
				state = assignedState;
			} else {
				throw new UnsupportedOperationException();
			}
			
		}

		/**
		 * Returns the State Name
		 * 
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			
			return NEW_NAME;
		}
		
	}
	
	/**
	 * The concrete state for the AssignedState
	 * @author Kbunker
	 *
	 */
	private class AssignedState implements TicketState {

		/**
		 * Updates the state based on the CommandValue given int the command
		 * 
		 * @param c the command to update the State
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.ACCEPTED) {
				state = workingState;
			} else if (c.getCommand() == CommandValue.CLOSED) {
				state = closedState;
			} else {
				throw new UnsupportedOperationException();
			}
			
			
		}

		/**
		 * Gets the state's name
		 * 
		 * @return the name of the state
		 */
		@Override
		public String getStateName() {
			
			return ASSIGNED_NAME;
		}
		
	}
	
	/**
	 * The concrete class for the WorkingState of the ticketState
	 * @author Kbunker
	 *
	 */
	private class WorkingState implements TicketState {

		/**
		 * Updates the State based on the CommandValue in the command parameter
		 * 
		 * @param c the command to update the Ticket state 
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.POSSESSION) {
				state = assignedState;
			} else if (c.getCommand() == CommandValue.PROGRESS) {
				state = workingState;
			} else if (c.getCommand() == CommandValue.FEEDBACK) {
				state = feedbackState;
			} else if (c.getCommand() == CommandValue.CLOSED) {
				state = closedState;
			} else {
				throw new UnsupportedOperationException();
			}
			
			
		}

		/**
		 * Gets the state's name
		 * 
		 * @return the String of the state's name
		 */
		@Override
		public String getStateName() {
			
			return WORKING_NAME;
		}
		
		
	}
	
	/**
	 * Concrete class for the Feedback state
	 * @author Kbunker
	 *
	 */
	private class FeedbackState implements TicketState {

		/**
		 * Updates the Ticket's state from the command  parameter
		 * 
		 * @param c the command to update the Ticket
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.FEEDBACK) {
				state = workingState;
			} else {
				throw new UnsupportedOperationException();
			}
			
			
		}

		/**
		 * Gets the State's name of the Ticket's current state
		 * 
		 * @return the state's name
		 */
		@Override
		public String getStateName() {
			
			return FEEDBACK_NAME;
		}
		
		
	}
	
	/**
	 * The concrete class for the ClosedState of the Ticket
	 * @author Kbunker
	 *
	 */
	private class ClosedState implements TicketState {

		/**
		 * The command to update the Ticket's State
		 * 
		 * @param the command to update the State
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.PROGRESS) {
				state = workingState;
			} else if (c.getCommand() == CommandValue.POSSESSION) {
				state = assignedState;
			} else {
				throw new UnsupportedOperationException();
			}
			
		}

		/**
		 * Gets the state's name
		 * 
		 * @return the State's name
		 */
		@Override
		public String getStateName() {
			
			return CLOSED_NAME;
		}
		
		
	}


}
