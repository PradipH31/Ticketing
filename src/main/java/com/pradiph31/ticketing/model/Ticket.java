package com.pradiph31.ticketing.model;

import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;
import java.time.LocalDateTime;

/**
 * Ticket class to define the structure of a ticket.
 */
public class Ticket {
  private int ticketId;
  private Double ticketPrice;
  private LocalDateTime soldDateTime;
  private boolean isAvailable;
  private boolean deleted;
  private int eventId;
  private Event event;

  public int getTicketId() {
    return ticketId;
  }

  public void setTicketId(int ticketId) {
    this.ticketId = ticketId;
  }

  public Double getTicketPrice() {
    return ticketPrice;
  }

  public void setTicketPrice(Double ticketPrice) {
    this.ticketPrice = ticketPrice;
  }

  public LocalDateTime getSoldDateTime() {
    return soldDateTime;
  }

  public void setSoldDateTime(LocalDateTime soldDateTime) {
    this.soldDateTime = soldDateTime;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  /**
   * Method to sell a ticket.
   */
  public void sellTicket() {
    this.isAvailable = false;
    this.soldDateTime = LocalDateTime.now();
  }

  public boolean isDeleted() {
    return deleted;
  }

  /**
   * Method to delete a ticket.
   */
  public void deleteTicket() {
    this.deleted = true;
  }

  public int getEventId() {
    return eventId;
  }

  public void setEventId(int eventId) {
    this.eventId = eventId;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public TicketResponseDTO getTicketResponseDTO() {
    return new TicketResponseDTO(this.ticketId,
                                 this.ticketPrice,
                                 this.soldDateTime,
                                 this.isAvailable,
                                 this.eventId);
  }
}
