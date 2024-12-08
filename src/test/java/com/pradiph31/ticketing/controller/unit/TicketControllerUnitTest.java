package com.pradiph31.ticketing.controller.unit;

import com.pradiph31.ticketing.controller.TicketController;
import com.pradiph31.ticketing.dto.ticket.TicketRequestDTO;
import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;
import com.pradiph31.ticketing.dto.ticket.TicketUpdateDTO;
import com.pradiph31.ticketing.model.Ticket;
import com.pradiph31.ticketing.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class TicketControllerUnitTest {

  @Mock
  private TicketService ticketService;

  @InjectMocks
  private TicketController ticketController;

  private static final List<Ticket> tickets = new ArrayList<>();
  private static final Ticket ticket1, ticket2, ticket3;

  static {
    ticket1 = new Ticket();
    ticket1.setTicketId(1);
    ticket1.setEventId(1);
    ticket1.setTicketPrice(100.00);
    ticket1.setAvailable(true);

    ticket2 = new Ticket();
    ticket2.setTicketId(2);
    ticket2.setEventId(2);
    ticket2.setTicketPrice(200.00);
    ticket2.setAvailable(true);

    ticket3 = new Ticket();
    ticket3.setTicketId(3);
    ticket3.setEventId(1);
    ticket3.setTicketPrice(150.00);
    ticket3.setAvailable(true);

    tickets.addAll(List.of(ticket1, ticket2, ticket3));
  }

  @Test
  void testGetAllTickets() {
    when(ticketService.getAllTickets()).thenReturn(tickets.stream().map(Ticket::getTicketResponseDTO).toList());
    ResponseEntity<List<TicketResponseDTO>> response = ticketController.getAllTickets();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(tickets.size(), Optional.ofNullable(response.getBody()).map(List::size).orElse(0));
  }

  @Test
  void testGetTicketById() {
    when(ticketService.getTicketById(1)).thenReturn(ticket1.getTicketResponseDTO());
    ResponseEntity<TicketResponseDTO> response = ticketController.getTicketById(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ticket1.getTicketResponseDTO(), response.getBody());
  }

  @Test
  void testSaveTicket() {
    when(ticketService.saveTicket(any())).thenReturn(ticket1.getTicketResponseDTO());
    TicketRequestDTO ticketRequestDTO = new TicketRequestDTO(75.00, 1);
    ResponseEntity<TicketResponseDTO> response = ticketController.saveTicket(ticketRequestDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ticket1.getTicketResponseDTO(), response.getBody());
  }

  @Test
  void testUpdateTicket() {
    when(ticketService.updateTicket(anyInt(), any())).thenReturn(ticket1.getTicketResponseDTO());
    TicketUpdateDTO ticketUpdateDTO = new TicketUpdateDTO(75.00, LocalDateTime.now(), true, 1);
    ResponseEntity<TicketResponseDTO> response = ticketController.updateTicket(1, ticketUpdateDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ticket1.getTicketResponseDTO(), response.getBody());
  }

    @Test
    void testDeleteTicket() {
        when(ticketService.deleteTicket(1)).thenReturn(true);
        ResponseEntity<Boolean> response = ticketController.deleteTicket(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

}
