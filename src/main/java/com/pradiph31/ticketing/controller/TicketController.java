package com.pradiph31.ticketing.controller;

import com.pradiph31.ticketing.dto.ticket.TicketRequestDTO;
import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;
import com.pradiph31.ticketing.dto.ticket.TicketUpdateDTO;
import com.pradiph31.ticketing.service.TicketService;
import java.time.LocalDateTime;
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

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
  private final TicketService ticketService;
  Logger logger = LoggerFactory.getLogger(TicketController.class);

  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @GetMapping
  public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
    logger.info("Fetching all tickets at {}", LocalDateTime.now());
    return ResponseEntity.ok(ticketService.getAllTickets());
  }

  @GetMapping("/{ticketId}")
  public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable int ticketId) {
    logger.info("Fetching ticket with id {} at {}", ticketId, LocalDateTime.now());
    return ResponseEntity.ok(ticketService.getTicketById(ticketId));
  }

  @PostMapping
  public ResponseEntity<TicketResponseDTO> saveTicket(@RequestBody TicketRequestDTO ticketDTO) {
    logger.info("Saving ticket {} at {}", ticketDTO, LocalDateTime.now());
    return ResponseEntity.ok(ticketService.saveTicket(ticketDTO));
  }

  @PutMapping("/{ticketId}")
  public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable int ticketId,
                                                        @RequestBody TicketUpdateDTO ticketDTO) {
    logger.info("Updating ticket {} with {} at {}", ticketId, ticketDTO, LocalDateTime.now());
    return ResponseEntity.ok(ticketService.updateTicket(ticketId, ticketDTO));
  }

  @DeleteMapping("/{ticketId}")
  public ResponseEntity<Boolean> deleteTicket(@PathVariable int ticketId) {
    logger.info("Deleting ticket with id {} at {}", ticketId, LocalDateTime.now());
    return ResponseEntity.ok(ticketService.deleteTicket(ticketId));
  }
}
