package com.pradiph31.ticketing.repository;

import com.pradiph31.ticketing.model.Ticket;
import java.util.List;

/**
 * TicketRepository interface to define the methods for TicketRepository.
 */
public interface TicketRepository {

  /**
   * Save the ticket object to the database.
   *
   * @param ticket Ticket object to be saved.
   * @return a saved Ticket object with the updated id
   */
  Ticket saveTicket(Ticket ticket);

  /**
   * Get the ticket object with the given ticketId.
   *
   * @param ticketId Ticket id to be searched.
   * @return Ticket object with the given ticketId.
   */
  Ticket getTicketById(int ticketId);

  /**
   * Update the ticket object in the database.
   *
   * @param ticket Ticket object to be updated.
   * @return Updated Ticket object.
   */
  Ticket updateTicket(Ticket ticket);

  /**
   * Delete the ticket object with the given ticketId.
   *
   * @param ticketId Ticket id to be deleted.
   * @return true if the ticket is deleted successfully, false otherwise.
   */
  boolean deleteTicket(int ticketId);

  /**
   * Get all the tickets in the database.
   *
   * @return List of all tickets in the database.
   */
  List<Ticket> getAllTickets();

  /**
   * Get all the tickets with the given eventId.
   *
   * @param eventId Event id to be searched.
   * @return List of tickets with the given eventId.
   */
  List<Ticket> getTicketsByEventId(int eventId);
}
