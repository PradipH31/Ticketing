package com.pradiph31.ticketing.service;

import com.pradiph31.ticketing.dto.ticket.TicketRequestDTO;
import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;
import com.pradiph31.ticketing.dto.ticket.TicketUpdateDTO;
import com.pradiph31.ticketing.model.Ticket;
import com.pradiph31.ticketing.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TicketServiceTest {

  @Autowired
  private TicketService ticketService;

  @MockBean
  private TicketRepository ticketRepository;

  private static final Ticket expectedTicket;

  static {
    expectedTicket = new Ticket();
    expectedTicket.setTicketId(1);
    expectedTicket.setTicketPrice(100.0);
    expectedTicket.setAvailable(true);
  }

  @Test
  void testSaveTicket() {
    TicketRequestDTO ticketDTO = new TicketRequestDTO(100.0, 1);

    when(ticketRepository.saveTicket(any(Ticket.class))).thenReturn(expectedTicket);
    TicketResponseDTO actualTicket = ticketService.saveTicket(ticketDTO);

    verify(ticketRepository, times(1)).saveTicket(any(Ticket.class));
    assertEquals(actualTicket.ticketId(), expectedTicket.getTicketId());
    assertEquals(actualTicket.ticketPrice(), expectedTicket.getTicketPrice());
    assertEquals(actualTicket.isAvailable(), expectedTicket.isAvailable());
  }

  @Test
  void testGetTicketById() {
    when(ticketRepository.getTicketById(anyInt())).thenReturn(expectedTicket);
    TicketResponseDTO actualTicket = ticketService.getTicketById(1);

    verify(ticketRepository, times(1)).getTicketById(1);
    assertEquals(actualTicket.ticketId(), expectedTicket.getTicketId());
    assertEquals(actualTicket.ticketPrice(), expectedTicket.getTicketPrice());
    assertEquals(actualTicket.isAvailable(), expectedTicket.isAvailable());
  }

  @Test
  void testUpdateTicket() {
    TicketUpdateDTO ticketDTO = new TicketUpdateDTO(100.0, null, true, 1);

    when(ticketRepository.updateTicket(any(Ticket.class))).thenReturn(expectedTicket);
    TicketResponseDTO actualTicket = ticketService.updateTicket(1, ticketDTO);

    verify(ticketRepository, times(1)).updateTicket(any(Ticket.class));
    assertEquals(actualTicket.ticketId(), expectedTicket.getTicketId());
    assertEquals(actualTicket.ticketPrice(), expectedTicket.getTicketPrice());
    assertEquals(actualTicket.isAvailable(), expectedTicket.isAvailable());
  }

  @Test
  void testDeleteTicket() {
    when(ticketRepository.getTicketById(anyInt())).thenReturn(new Ticket());
    when(ticketRepository.deleteTicket(anyInt())).thenReturn(true);
    assertTrue(ticketService.deleteTicket(1));
  }

  @Test
  void testSaveTicketInvalidData() {
    TicketRequestDTO ticketDTO = new TicketRequestDTO(-100.0, 1);
    try {
      ticketService.saveTicket(ticketDTO);
    } catch (Exception e) {
      assertEquals("Invalid ticket data", e.getMessage());
    }
  }

  @Test
  void testUpdateTicketInvalidData() {
    TicketUpdateDTO ticketDTO = new TicketUpdateDTO(-100.0, null, true, 1);
    try {
      ticketService.updateTicket(1, ticketDTO);
    } catch (Exception e) {
      assertEquals("Invalid ticket data", e.getMessage());
    }
  }

  @Test
  void testDeleteTicketNonexistent() {
    when(ticketRepository.getTicketById(anyInt())).thenReturn(null);
    try {
      ticketService.deleteTicket(1);
    } catch (Exception e) {
      assertEquals("Ticket ID does not exist", e.getMessage());
    }
  }

  @Test
  void testGetAllTickets() {
    when(ticketRepository.getAllTickets()).thenReturn(List.of(expectedTicket));
    List<TicketResponseDTO> actualTickets = ticketService.getAllTickets();

    verify(ticketRepository, times(1)).getAllTickets();
    assertEquals(1, actualTickets.size());
    assertEquals(expectedTicket.getTicketId(), actualTickets.getFirst().ticketId());
    assertEquals(expectedTicket.getTicketPrice(), actualTickets.getFirst().ticketPrice());
    assertEquals(expectedTicket.isAvailable(), actualTickets.getFirst().isAvailable());
  }

  @Test
  void testGetTicketByIdNonexistent() {
    Ticket deletedTicket = new Ticket();
    deletedTicket.deleteTicket();

    when(ticketRepository.getTicketById(anyInt())).thenReturn(deletedTicket);
    try {
      ticketService.getTicketById(1);
    } catch (Exception e) {
      assertEquals("Ticket ID does not exist", e.getMessage());
    }
  }
}
