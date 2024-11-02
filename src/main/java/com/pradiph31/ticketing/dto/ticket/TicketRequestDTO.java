package com.pradiph31.ticketing.dto.ticket;

import com.pradiph31.ticketing.model.Ticket;

public record TicketRequestDTO(Double ticketPrice, int eventId) {
  public Ticket getTicket() {
    Ticket ticket = new Ticket();
    ticket.setTicketPrice(ticketPrice);
    ticket.setEventId(eventId);
    return ticket;
  }
}