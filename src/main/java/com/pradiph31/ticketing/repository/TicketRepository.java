package com.pradiph31.ticketing.repository;

import com.pradiph31.ticketing.model.Ticket;

import java.util.List;

public interface TicketRepository {
  Ticket saveTicket(Ticket ticket);

  Ticket getTicketById(int ticketId);

  Ticket updateTicket(Ticket ticket);

  boolean deleteTicket(int ticketId);

  List<Ticket> getAllTickets();

    List<Ticket> getTicketsByEventId(int eventId);
}
