package com.pradiph31.ticketing.dto.ticket;

import com.pradiph31.ticketing.model.Ticket;
import java.time.LocalDateTime;

/**
 * TicketUpdateDTO record to define the structure of a ticket update data transfer object.
 *
 * @param ticketPrice  the price of the ticket
 * @param soldDateTime the date and time the ticket was sold
 * @param isAvailable  the availability of the ticket
 * @param eventId      the id of the event the ticket is for
 */
public record TicketUpdateDTO(Double ticketPrice, LocalDateTime soldDateTime, boolean isAvailable,
                              int eventId) {
  /**
   * Creates a ticket object using the ticket update data.
   *
   * @return a ticket object using the ticket update data
   */
  public Ticket getTicket() {
    Ticket ticket = new Ticket();
    ticket.setTicketPrice(ticketPrice);
    ticket.setAvailable(isAvailable);
    ticket.setSoldDateTime(soldDateTime);
    ticket.setEventId(eventId);
    return ticket;
  }
}