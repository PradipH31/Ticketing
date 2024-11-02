package com.pradiph31.ticketing.dto.ticket;

import com.pradiph31.ticketing.model.Ticket;

import java.time.LocalDateTime;

public record TicketUpdateDTO(Double ticketPrice, LocalDateTime soldDateTime, boolean isAvailable, int eventId) {
  public Ticket getTicket() {
    Ticket ticket = new Ticket();
    ticket.setTicketPrice(ticketPrice);
    ticket.setAvailable(isAvailable);
    ticket.setSoldDateTime(soldDateTime);
    ticket.setEventId(eventId);
    return ticket;
  }
}