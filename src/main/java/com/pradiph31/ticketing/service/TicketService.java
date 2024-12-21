package com.pradiph31.ticketing.service;

import static com.pradiph31.ticketing.util.TicketingConstants.INVALID_TICKET_DATA;
import static com.pradiph31.ticketing.util.TicketingConstants.NONEXISTENT_TICKET_ID_MESSAGE;

import com.pradiph31.ticketing.dto.ticket.TicketRequestDTO;
import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;
import com.pradiph31.ticketing.dto.ticket.TicketUpdateDTO;
import com.pradiph31.ticketing.exception.InvalidTicketDataException;
import com.pradiph31.ticketing.exception.NonexistentTicketIDException;
import com.pradiph31.ticketing.model.Ticket;
import com.pradiph31.ticketing.repository.TicketRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * TicketService class to define the methods for TicketService.
 */
@Service
public class TicketService {
  private final TicketRepository ticketRepository;
  Logger logger = LoggerFactory.getLogger(TicketService.class);

  /**
   * Constructor to inject TicketRepository object.
   *
   * @param ticketRepository TicketRepository object to be injected.
   */
  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  /**
   * Save the ticket object to the database.
   *
   * @param ticketDTO TicketRequestDTO object to be saved.
   * @return TicketResponseDTO object with the updated id.
   */
  public TicketResponseDTO saveTicket(TicketRequestDTO ticketDTO) {
    if (ticketDTO == null || ticketDTO.ticketPrice()
                                      .isNaN() || ticketDTO.ticketPrice() < 0) {
      logger.error("Invalid ticket info for {}", ticketDTO);
      throw new InvalidTicketDataException(INVALID_TICKET_DATA);
    }
    return ticketRepository.saveTicket(ticketDTO.getTicket())
                           .getTicketResponseDTO();
  }

  /**
   * Get the ticket object with the given ticketId.
   *
   * @param ticketId Ticket id to be searched.
   * @return TicketResponseDTO object with the given ticketId.
   */
  public TicketResponseDTO getTicketById(int ticketId) {
    Ticket ticket = ticketRepository.getTicketById(ticketId);
    if (ticket.isDeleted()) {
      logger.error("Ticket {} does not exist", ticketId);
      throw new NonexistentTicketIDException(NONEXISTENT_TICKET_ID_MESSAGE);
    }
    return ticket.getTicketResponseDTO();
  }

  /**
   * Update the ticket object in the database.
   *
   * @param ticketId  Ticket id to be updated.
   * @param ticketDTO TicketUpdateDTO object to be updated.
   * @return TicketResponseDTO object with the updated ticket.
   */
  public TicketResponseDTO updateTicket(int ticketId, TicketUpdateDTO ticketDTO) {
    if (ticketDTO == null || ticketId < 0 || ticketDTO.ticketPrice() < 0) {
      logger.error("Invalid update request for {}", ticketDTO);
      throw new InvalidTicketDataException(INVALID_TICKET_DATA);
    }
    ticketRepository.getTicketById(ticketId);
    Ticket ticket = ticketDTO.getTicket();
    ticket.setTicketId(ticketId);
    return ticketRepository.updateTicket(ticket)
                           .getTicketResponseDTO();
  }

  /**
   * Delete the ticket object with the given ticketId.
   *
   * @param ticketId Ticket id to be deleted.
   * @return true if the ticket is deleted successfully, false otherwise.
   */
  public boolean deleteTicket(int ticketId) {
    if (ticketRepository.getTicketById(ticketId) == null) {
      throw new NonexistentTicketIDException(NONEXISTENT_TICKET_ID_MESSAGE);
    }
    return ticketRepository.deleteTicket(ticketId);
  }

  /**
   * Get all the tickets in the database.
   *
   * @return List of all tickets in the database.
   */
  public List<TicketResponseDTO> getAllTickets() {
    return ticketRepository.getAllTickets()
                           .stream()
                           .filter(ticket -> !ticket.isDeleted())
                           .map(Ticket::getTicketResponseDTO)
                           .toList();
  }

}
