/**
 * 
 */
package edu.ncsu.csc216.tracker.ticket_tracker;




import edu.ncsu.csc216.ticket.xml.TicketIOException;
import edu.ncsu.csc216.ticket.xml.TicketReader;
import edu.ncsu.csc216.ticket.xml.TicketWriter;
import edu.ncsu.csc216.tracker.command.Command;
import edu.ncsu.csc216.tracker.ticket.TrackedTicket;

/**
 * @author Katelyn
 *
 */
public class TicketTrackerModel {
	
	private static TicketTrackerModel instance = null;
	
	private TrackedTicketList list;
	
	private TicketTrackerModel() {
		list = new TrackedTicketList();
	}
	
	public static TicketTrackerModel getInstance() {
		if (instance == null) {
			instance = new TicketTrackerModel();
		}
		
		return instance;
	}
	
	public void saveTicketsToFile(String fileName) {
		TicketWriter writer = new TicketWriter(fileName);
		for (int i = 0; i < list.getTrackedTickets().size(); i++) {
			writer.addItem(list.getTrackedTickets().get(i).getXMLTicket());
		}
		try {
			writer.marshal();
		} catch (TicketIOException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public void loadTicketsFromFile(String fileName) {
		TicketReader reader;
		try {
			reader = new TicketReader(fileName);
			list.addXMLTickets(reader.getTickets());
		} catch (TicketIOException e) {
			throw new IllegalArgumentException();
		}
		
		
	}
	
	public void createNewTicketList() {
		list = new TrackedTicketList();
		TrackedTicket.setCounter(1);
	}
	
	public Object[][] getTicketListAsArray() {
		Object[][] TicketArray = new Object[list.getTrackedTickets().size()][3];
		for (int i = 0; i < list.getTrackedTickets().size(); i++) {
			TicketArray[i][0] = list.getTrackedTickets().get(i).getTicketId();
			TicketArray[i][1] = list.getTrackedTickets().get(i).getStateName();
			TicketArray[i][2] = list.getTrackedTickets().get(i).getTitle();
		}
		return TicketArray;
	}
	
	public Object[][] getTicketListByOwnerAsArray(String owner) {
		Object[][] TicketArray = new Object[list.getTrackedTickets().size()][3];
		for (int i = 0; i < list.getTrackedTickets().size(); i++) {
			if (list.getTrackedTickets().get(i).getOwner().equals(owner)) {
				TicketArray[i][0] = list.getTrackedTickets().get(i).getTicketId();
				TicketArray[i][1] = list.getTrackedTickets().get(i).getStateName();
				TicketArray[i][2] = list.getTrackedTickets().get(i).getTitle();
			}
			
		}
		return TicketArray;
		
	}
	
	public Object[][] getTicketListBySubmitterAsArray(String submitter) {
		Object[][] TicketArray = new Object[list.getTrackedTickets().size()][3];
		for (int i = 0; i < list.getTrackedTickets().size(); i++) {
			if (list.getTrackedTickets().get(i).getSubmitter().equals(submitter)) {
				TicketArray[i][0] = list.getTrackedTickets().get(i).getTicketId();
				TicketArray[i][1] = list.getTrackedTickets().get(i).getStateName();
				TicketArray[i][2] = list.getTrackedTickets().get(i).getTitle();
			}
			
		}
		return TicketArray;
		
	}
	
	public TrackedTicket getTicketById(int id) {
		return list.getTicketById(id);
	}
	
	public void executeCommand(int i, Command c) {
		list.executeCommand(i, c);
	}
	
	public void deleteTicketById(int id) {
		list.deleteTicketById(id);
	}
	
	public void addTicketToList(String title, String submitter, String note) {
		list.addTrackedTicket(title, submitter, note);
	}

}
