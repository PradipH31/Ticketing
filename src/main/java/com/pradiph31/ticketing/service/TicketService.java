package com.pradiph31.ticketing.service;

import com.pradiph31.ticketing.dto.ticket.TicketRequestDTO;
import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;
import com.pradiph31.ticketing.dto.ticket.TicketUpdateDTO;
import com.pradiph31.ticketing.exception.InvalidTicketDataException;
import com.pradiph31.ticketing.exception.NonexistentTicketIDException;
import com.pradiph31.ticketing.model.Ticket;
import com.pradiph31.ticketing.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.pradiph31.ticketing.util.TicketingConstants.INVALID_TICKET_DATA;
import static com.pradiph31.ticketing.util.TicketingConstants.NONEXISTENT_TICKET_ID_MESSAGE;

@Service
public class TicketService {
  private final TicketRepository ticketRepository;
  Logger logger = LoggerFactory.getLogger(TicketService.class);

  public TicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public TicketResponseDTO saveTicket(TicketRequestDTO ticketDTO) {
    if (ticketDTO == null || ticketDTO.ticketPrice().isNaN() || ticketDTO.ticketPrice() < 0) {
      logger.error("Invalid ticket info for {} at {}", ticketDTO, LocalDateTime.now());
      throw new InvalidTicketDataException(INVALID_TICKET_DATA);
    }
    return ticketRepository.saveTicket(ticketDTO.getTicket()).getTicketResponseDTO();
  }

  public TicketResponseDTO getTicketById(int ticketId) {
    Ticket ticket = ticketRepository.getTicketById(ticketId);
    if (ticket.isDeleted()) {
      logger.error("Ticket {} does not exist at {}", ticketId, LocalDateTime.now());
      throw new NonexistentTicketIDException(NONEXISTENT_TICKET_ID_MESSAGE);
    }
    return ticket.getTicketResponseDTO();
  }

  public TicketResponseDTO updateTicket(int ticketId, TicketUpdateDTO ticketDTO) {
    if (ticketDTO == null || ticketId < 0 || ticketDTO.ticketPrice() < 0) {
      logger.error("Invalid update request for {} at {}", ticketDTO, LocalDateTime.now());
      throw new InvalidTicketDataException(INVALID_TICKET_DATA);
    }
    ticketRepository.getTicketById(ticketId);
    Ticket ticket = ticketDTO.getTicket();
    ticket.setTicketId(ticketId);
    return ticketRepository.updateTicket(ticket).getTicketResponseDTO();
  }

  public boolean deleteTicket(int ticketId) {
    if (ticketRepository.getTicketById(ticketId) == null) {
      throw new NonexistentTicketIDException(NONEXISTENT_TICKET_ID_MESSAGE);
    }
    return ticketRepository.deleteTicket(ticketId);
  }

  public List<TicketResponseDTO> getAllTickets() {
    return ticketRepository.getAllTickets().stream().filter(ticket -> !ticket.isDeleted()).map(Ticket::getTicketResponseDTO).toList();
  }

}
