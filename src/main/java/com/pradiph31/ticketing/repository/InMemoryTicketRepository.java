package com.pradiph31.ticketing.repository;

import static com.pradiph31.ticketing.util.TicketingConstants.NONEXISTENT_TICKET_ID_MESSAGE;

import com.pradiph31.ticketing.exception.NonexistentTicketIDException;
import com.pradiph31.ticketing.model.Ticket;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * The InMemoryTicketRepository class is a repository class that implements the TicketRepository
 * interface. It is used to store and retrieve ticket data from an in-memory list. The class
 * contains methods to save, retrieve, update, and delete ticket data. The class also contains
 * methods to retrieve all tickets and tickets by event ID.
 */
@Repository
public class InMemoryTicketRepository implements TicketRepository {

  private static final List<Ticket> tickets = new ArrayList<>();
  Logger logger = LoggerFactory.getLogger(InMemoryTicketRepository.class);

  static {
    Ticket ticket1 = new Ticket();
    ticket1.setTicketId(1);
    ticket1.setEventId(1);
    ticket1.setTicketPrice(100.00);
    ticket1.setAvailable(true);
    tickets.add(ticket1);

    Ticket ticket2 = new Ticket();
    ticket2.setTicketId(2);
    ticket2.setEventId(2);
    ticket2.setTicketPrice(200.00);
    ticket2.setAvailable(true);
    tickets.add(ticket2);

    Ticket ticket3 = new Ticket();
    ticket3.setTicketId(3);
    ticket3.setEventId(1);
    ticket3.setTicketPrice(150.00);
    ticket3.setAvailable(true);
    tickets.add(ticket3);
  }

  @Override
  public Ticket saveTicket(Ticket ticket) {
    ticket.setAvailable(true);
    ticket.setTicketId(tickets.size() + 1);
    tickets.add(ticket);
    return ticket;
  }

  @Override
  public Ticket getTicketById(int ticketId) {
    if (ticketId < 0 || ticketId >= tickets.size()) {
      logNonexistentTicketIdError(ticketId);
      throw new NonexistentTicketIDException(NONEXISTENT_TICKET_ID_MESSAGE);
    }
    return tickets.get(ticketId);
  }

  @Override
  public Ticket updateTicket(Ticket ticket) {
    if (ticket.getTicketId() < 0 || ticket.getTicketId() > tickets.size()) {
      logNonexistentTicketIdError(ticket.getTicketId());
      throw new NonexistentTicketIDException(NONEXISTENT_TICKET_ID_MESSAGE);
    }
    tickets.set(ticket.getTicketId(), ticket);
    return ticket;
  }

  @Override
  public boolean deleteTicket(int ticketId) {
    Ticket ticket = tickets.get(ticketId);
    ticket.deleteTicket();
    return true;
  }

  @Override
  public List<Ticket> getAllTickets() {
    return tickets;
  }

  @Override
  public List<Ticket> getTicketsByEventId(int eventId) {
    return tickets.stream()
                  .filter(ticket -> ticket.getEventId() == eventId)
                  .toList();
  }

  private void logNonexistentTicketIdError(int ticketId) {
    logger.error("Ticket ID {} does not exist", ticketId);
  }
}
