package com.pradiph31.ticketing.controller;

import com.pradiph31.ticketing.dto.ticket.TicketRequestDTO;
import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;
import com.pradiph31.ticketing.dto.ticket.TicketUpdateDTO;
import com.pradiph31.ticketing.service.TicketService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TicketController class to handle ticket-related API requests.
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketController {
  private final TicketService ticketService;
  Logger logger = LoggerFactory.getLogger(TicketController.class);

  /**
   * Constructor to inject the ticket service into the controller.
   *
   * @param ticketService the ticket service to be injected into the controller.
   */
  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  /**
   * Get all tickets.
   *
   * @return all tickets.
   */
  @GetMapping
  public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
    logger.info("Fetching all tickets");
    return ResponseEntity.ok(ticketService.getAllTickets());
  }

  /**
   * Get the ticket with the given id.
   *
   * @param ticketId the id of the ticket to be retrieved.
   * @return the ticket with the given id.
   */
  @GetMapping("/{ticketId}")
  public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable int ticketId) {
    logger.info("Fetching ticket with id {}", ticketId);
    return ResponseEntity.ok(ticketService.getTicketById(ticketId));
  }

  /**
   * Save the given ticket.
   *
   * @param ticketDTO the ticket to be saved.
   * @return the saved ticket.
   */
  @PostMapping
  public ResponseEntity<TicketResponseDTO> saveTicket(@RequestBody TicketRequestDTO ticketDTO) {
    logger.info("Saving ticket {}", ticketDTO);
    return ResponseEntity.ok(ticketService.saveTicket(ticketDTO));
  }

  /**
   * Update the ticket with the given id.
   *
   * @param ticketId  the id of the ticket to be updated.
   * @param ticketDTO the updated ticket information.
   * @return the updated ticket.
   */
  @PutMapping("/{ticketId}")
  public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable int ticketId,
                                                        @RequestBody TicketUpdateDTO ticketDTO) {
    logger.info("Updating ticket {} with {}", ticketId, ticketDTO);
    return ResponseEntity.ok(ticketService.updateTicket(ticketId, ticketDTO));
  }

  /**
   * Delete the ticket with the given id.
   *
   * @param ticketId the id of the ticket to be deleted.
   * @return true response entity if the ticket is deleted, false otherwise.
   */
  @DeleteMapping("/{ticketId}")
  public ResponseEntity<Boolean> deleteTicket(@PathVariable int ticketId) {
    logger.info("Deleting ticket with id {}", ticketId);
    return ResponseEntity.ok(ticketService.deleteTicket(ticketId));
  }
}
