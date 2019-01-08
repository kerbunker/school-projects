/**
 * 
 */
package edu.ncsu.csc216.tracker.ticket_tracker;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.ticket.xml.Ticket;
import edu.ncsu.csc216.tracker.command.Command;
import edu.ncsu.csc216.tracker.ticket.TrackedTicket;

/**
 * @author Katelyn
 *
 */
public class TrackedTicketList {
	
	
	private static final int INITIAL_COUNTER_VALUE = 1;
	private List<TrackedTicket> tickets;
	
	public TrackedTicketList() {
		tickets = new ArrayList<TrackedTicket>();
		TrackedTicket.setCounter(INITIAL_COUNTER_VALUE);
	}
	
	public int addTrackedTicket(String title, String submitter, String note) {
		TrackedTicket t = new TrackedTicket(title, submitter, note);
		tickets.add(t);
		return t.getTicketId();
	}
	
	public void addXMLTickets(List<Ticket> tickets) {
		TrackedTicket t;
		int num = 0;
		for (int i = 0; i < tickets.size(); i++) {
			t = new TrackedTicket(tickets.get(i));
			this.tickets.add(t);
			if (tickets.get(i).getId() > num) {
				num = tickets.get(i).getId();
			}
		}
		TrackedTicket.setCounter(num + 1);
	}
	
	public List<TrackedTicket> getTrackedTickets() {
		return tickets;
	}
	
	public List<TrackedTicket> getTicketsByOwner(String owner) {
		List<TrackedTicket> tList = new ArrayList<TrackedTicket>();
		for (int i = 0; i < tickets.size(); i++) {
			if (owner.equals(tickets.get(i).getOwner())) {
				tList.add(tickets.get(i));
			}
		}
		return tList;
	}
	
	public List<TrackedTicket> getTicketsBySubmitter(String submitter) {
		List<TrackedTicket> tList = new ArrayList<TrackedTicket>();
		for (int i = 0; i < tickets.size(); i++) {
			if (submitter.equals(tickets.get(i).getSubmitter())) {
				tList.add(tickets.get(i));
			}
		}
		return tList;
	}
	
	public TrackedTicket getTicketById(int id) {
		for (int i = 0; i < tickets.size(); i++) {
			if (id == tickets.get(i).getTicketId()) {
				return tickets.get(i);
			}
		}
		return null;
	}
	
	public void executeCommand(int i, Command c) {
		tickets.get(i).update(c);
	}
	
	public void deleteTicketById(int id) {
		for (int i = 0; i < tickets.size(); i++) {
			if (id == tickets.get(i).getTicketId()) {
				tickets.remove(i);
			}
		}
	}

}
