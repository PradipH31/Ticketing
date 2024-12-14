package com.pradiph31.ticketing;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.pradiph31.ticketing.controller.EventController;
import com.pradiph31.ticketing.controller.TicketController;
import com.pradiph31.ticketing.repository.EventRepository;
import com.pradiph31.ticketing.repository.TicketRepository;
import com.pradiph31.ticketing.service.EventService;
import com.pradiph31.ticketing.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TicketingApplicationTests {

  @Autowired
  private EventController eventController;
  @Autowired
  private TicketController ticketController;
  @Autowired
  private EventService eventService;
  @Autowired
  private TicketService ticketService;
  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private TicketRepository ticketRepository;

  @Test
  void contextLoads() {
    assertThat(eventController).isNotNull();
    assertThat(ticketController).isNotNull();
    assertThat(eventService).isNotNull();
    assertThat(ticketService).isNotNull();
    assertThat(eventRepository).isNotNull();
    assertThat(ticketRepository).isNotNull();
  }

}
