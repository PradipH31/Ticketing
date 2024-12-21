package com.pradiph31.ticketing.controller.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.pradiph31.ticketing.controller.EventController;
import com.pradiph31.ticketing.dto.EventWithTicketsDTO;
import com.pradiph31.ticketing.dto.event.EventRequestDTO;
import com.pradiph31.ticketing.dto.event.EventResponseDTO;
import com.pradiph31.ticketing.dto.event.EventUpdateDTO;
import com.pradiph31.ticketing.model.Event;
import com.pradiph31.ticketing.model.Ticket;
import com.pradiph31.ticketing.service.EventService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class EventControllerUnitTest {

  @Mock
  private EventService eventService;

  @InjectMocks
  private EventController eventController;

  private static final List<Event> events = new ArrayList<>();
  private static final Event event1;
  private static final Event event2;
  private static final Ticket ticket1;
  private static final Ticket ticket2;
  private static final Ticket ticket3;

  static {
    event1 = new Event();
    event1.setEventId(1);
    event1.setEventName("Coachella");
    event1.setEventDescription("West Coast Music Festival");
    event1.setEventLocation("Indio, CA");
    event1.setEventStartDate(LocalDateTime.of(2025, 9, 1, 0, 0));
    event1.setEventEndDate(LocalDateTime.of(2025, 9, 2, 0, 0));
    event1.setAvailable(true);
    events.add(event1);

    event2 = new Event();
    event2.setEventId(2);
    event2.setEventName("Lollapalooza");
    event2.setEventDescription("Midwest Music Festival");
    event2.setEventLocation("Chicago, IL");
    event2.setEventStartDate(LocalDateTime.of(2025, 8, 1, 0, 0));
    event2.setEventEndDate(LocalDateTime.of(2025, 8, 2, 0, 0));
    event2.setAvailable(true);
    events.add(event2);

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
  }

  @Test
  void testGetAllEvents() {
    Mockito.when(eventService.getAllEvents())
           .thenReturn(events.stream()
                             .map(Event::getEventResponseDTO)
                             .toList());
    ResponseEntity<List<EventResponseDTO>> response = eventController.getAllEvents();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(events.size(),
                 Optional.ofNullable(response.getBody())
                         .map(List::size)
                         .orElse(0));
  }

  @Test
  void testGetEventById() {
    when(eventService.getEventById(1)).thenReturn(event1.getEventResponseDTO());
    ResponseEntity<EventResponseDTO> response = eventController.getEventById(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(event1.getEventResponseDTO(), response.getBody());
  }

  @Test
  void testSaveEvent() {
    when(eventService.saveEvent(any())).thenReturn(event1.getEventResponseDTO());
    EventRequestDTO eventDTO = new EventRequestDTO("Coachella",
                                                   "West Coast Music Festival",
                                                   List.of(),
                                                   "Indio, CA",
                                                   LocalDateTime.of(2025, 9, 1, 0, 0),
                                                   LocalDateTime.of(2025, 9, 2, 0, 0));
    ResponseEntity<EventResponseDTO> response = eventController.saveEvent(eventDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(event1.getEventResponseDTO(), response.getBody());
  }

  @Test
  void testUpdateEvent() {
    when(eventService.updateEvent(anyInt(), any())).thenReturn(event1.getEventResponseDTO());
    EventUpdateDTO eventDTO = new EventUpdateDTO("Coachella",
                                                 "West Coast Music Festival",
                                                 List.of(),
                                                 "Indio, CA",
                                                 LocalDateTime.of(2025, 9, 1, 0, 0),
                                                 LocalDateTime.of(2025, 9, 2, 0, 0),
                                                 true);
    ResponseEntity<EventResponseDTO> response = eventController.updateEvent(1, eventDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(event1.getEventResponseDTO(), response.getBody());
  }

  @Test
  void testDeleteEvent() {
    when(eventService.deleteEvent(anyInt())).thenReturn(true);
    ResponseEntity<Boolean> response = eventController.deleteEvent(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(true, response.getBody());
  }

  @Test
  void testGetEventWithTickets() {
    Mockito.when(eventService.getEventWithTickets(anyInt()))
           .thenReturn(new EventWithTicketsDTO(event1.getEventResponseDTO(),
                                               List.of(ticket3.getTicketResponseDTO(),
                                                       ticket1.getTicketResponseDTO())));
    ResponseEntity<EventWithTicketsDTO> response = eventController.getEventWithTickets(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(event1.getEventResponseDTO(),
                 Optional.ofNullable(response.getBody())
                         .map(EventWithTicketsDTO::event)
                         .orElse(new EventResponseDTO(0,
                                                      "",
                                                      "",
                                                      List.of(),
                                                      "",
                                                      LocalDateTime.now(),
                                                      LocalDateTime.now(),
                                                      false)));
    assertEquals(2,
                 Optional.ofNullable(response.getBody())
                         .map(EventWithTicketsDTO::tickets)
                         .map(List::size)
                         .orElse(0));
  }
}
