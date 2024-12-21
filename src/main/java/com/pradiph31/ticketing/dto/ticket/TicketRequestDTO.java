package com.pradiph31.ticketing.dto.ticket;

import com.pradiph31.ticketing.model.Ticket;

/**
 * A DTO for a ticket request.
 *
 * @param ticketPrice the price of the ticket
 * @param eventId     the id of the event the ticket is for
 */
public record TicketRequestDTO(Double ticketPrice, int eventId) {

  /**
   * Creates a ticket object using the ticket request data.
   *
   * @return a ticket object using the ticket request data
   */
  public Ticket getTicket() {
    Ticket ticket = new Ticket();
    ticket.setTicketPrice(ticketPrice);
    ticket.setEventId(eventId);
    return ticket;
  }
}