package com.pradiph31.ticketing.repository;

import com.pradiph31.ticketing.model.Ticket;
import java.util.List;

/**
 * TicketRepository interface to define the methods for TicketRepository.
 */
public interface TicketRepository {

  /**
   * @param ticket Ticket object to be saved.
   * @return a saved Ticket object with the updated id
   */
  Ticket saveTicket(Ticket ticket);

  Ticket getTicketById(int ticketId);

  Ticket updateTicket(Ticket ticket);

  boolean deleteTicket(int ticketId);

  List<Ticket> getAllTickets();

  List<Ticket> getTicketsByEventId(int eventId);
}
