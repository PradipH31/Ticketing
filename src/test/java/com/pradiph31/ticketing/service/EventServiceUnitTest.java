package com.pradiph31.ticketing.service;

import com.pradiph31.ticketing.dto.EventWithTicketsDTO;
import com.pradiph31.ticketing.dto.event.EventRequestDTO;
import com.pradiph31.ticketing.dto.event.EventResponseDTO;
import com.pradiph31.ticketing.dto.event.EventUpdateDTO;
import com.pradiph31.ticketing.model.Event;
import com.pradiph31.ticketing.model.Ticket;
import com.pradiph31.ticketing.repository.EventRepository;
import com.pradiph31.ticketing.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class EventServiceUnitTest {

  @InjectMocks
  private EventService eventService;

  @Mock
  private EventRepository eventRepository;

  @Mock
  private TicketRepository ticketRepository;

  private static final Event expectedEvent;
  private static final Ticket expectedTicket;

  static {
    expectedEvent = new Event();
    expectedEvent.setEventId(1);
    expectedEvent.setEventName("Coachella");
    expectedEvent.setEventDescription("West Coast Music Festival");
    expectedEvent.setEventLocation("Indio, CA");
    expectedEvent.setEventStartDate(LocalDateTime.of(2025, 9, 1, 0, 0));
    expectedEvent.setEventEndDate(LocalDateTime.of(2025, 9, 2, 0, 0));
    expectedEvent.setAvailable(true);

    expectedTicket = new Ticket();
    expectedTicket.setTicketId(1);
    expectedTicket.setTicketPrice(100.0);
    expectedTicket.setAvailable(true);
  }

  @Test
  void testSaveEvent() {
    EventRequestDTO eventDTO = new EventRequestDTO("Coachella", "West Coast Music Festival", List.of("Music", "Party"), "Indio, CA",
            LocalDateTime.of(2025, 9, 1, 0, 0), LocalDateTime.of(2025, 9, 2, 0, 0));

    when(eventRepository.saveEvent(any(Event.class))).thenReturn(expectedEvent);
    EventResponseDTO actualEvent = eventService.saveEvent(eventDTO);

    verify(eventRepository, times(1)).saveEvent(any(Event.class));
    assertEquals(actualEvent.eventId(), expectedEvent.getEventId());
    assertEquals(actualEvent.eventName(), expectedEvent.getEventName());
    assertEquals(actualEvent.eventDescription(), expectedEvent.getEventDescription());
    assertEquals(actualEvent.eventLocation(), expectedEvent.getEventLocation());
    assertEquals(actualEvent.eventStartDate(), expectedEvent.getEventStartDate());
    assertEquals(actualEvent.eventEndDate(), expectedEvent.getEventEndDate());
    assertEquals(actualEvent.isAvailable(), expectedEvent.isAvailable());
  }

  @Test
  void testGetEventById() {
    expectedEvent.setAvailable(true);
    when(eventRepository.getEventById(anyInt())).thenReturn(expectedEvent);
    EventResponseDTO actualEvent = eventService.getEventById(1);

    verify(eventRepository, times(1)).getEventById(1);
    assertEquals(actualEvent.eventId(), expectedEvent.getEventId());
    assertEquals(actualEvent.eventName(), expectedEvent.getEventName());
    assertEquals(actualEvent.eventDescription(), expectedEvent.getEventDescription());
    assertEquals(actualEvent.eventLocation(), expectedEvent.getEventLocation());
    assertEquals(actualEvent.eventStartDate(), expectedEvent.getEventStartDate());
    assertEquals(actualEvent.eventEndDate(), expectedEvent.getEventEndDate());
    assertEquals(actualEvent.isAvailable(), expectedEvent.isAvailable());
  }

  @Test
  void testUpdateEvent() {
    EventUpdateDTO eventDTO = new EventUpdateDTO("Coachella", "West Coast Music Festival", List.of(), "Indio, CA",
            LocalDateTime.of(2025, 9, 1, 0, 0), LocalDateTime.of(2025, 9, 2, 0, 0), true);

    when(eventRepository.updateEvent(any(Event.class))).thenReturn(expectedEvent);
    EventResponseDTO actualEvent = eventService.updateEvent(1, eventDTO);

    verify(eventRepository, times(1)).updateEvent(any(Event.class));
    assertEquals(actualEvent.eventId(), expectedEvent.getEventId());
    assertEquals(actualEvent.eventName(), expectedEvent.getEventName());
    assertEquals(actualEvent.eventDescription(), expectedEvent.getEventDescription());
    assertEquals(actualEvent.eventLocation(), expectedEvent.getEventLocation());
    assertEquals(actualEvent.eventStartDate(), expectedEvent.getEventStartDate());
    assertEquals(actualEvent.eventEndDate(), expectedEvent.getEventEndDate());
    assertEquals(actualEvent.isAvailable(), expectedEvent.isAvailable());
  }

  @Test
  void testDeleteEvent() {
    when(eventRepository.getEventById(anyInt())).thenReturn(new Event());
    when(eventRepository.deleteEvent(anyInt())).thenReturn(true);
    assertTrue(eventService.deleteEvent(1));
  }

  @Test
  void testSaveEventInvalidData() {
    EventRequestDTO eventDTO = new EventRequestDTO("Coachella", "West Coast Music Festival", List.of(), "Indio, CA",
            LocalDateTime.of(2025, 9, 2, 0, 0), LocalDateTime.of(2025, 9, 1, 0, 0));
    try {
      eventService.saveEvent(eventDTO);
    } catch (Exception e) {
      assertEquals("Invalid event data", e.getMessage());
    }
  }

  @Test
  void testUpdateEventInvalidData() {
    EventUpdateDTO eventDTO = new EventUpdateDTO("Coachella", "West Coast Music Festival", List.of(), "Indio, CA",
            LocalDateTime.of(2025, 9, 2, 0, 0), LocalDateTime.of(2025, 9, 1, 0, 0), true);
    try {
      eventService.updateEvent(1, eventDTO);
    } catch (Exception e) {
      assertEquals("Invalid event data", e.getMessage());
    }
  }

  @Test
  void testDeleteEventNonexistent() {
    when(eventRepository.getEventById(anyInt())).thenReturn(expectedEvent);
    when(eventRepository.deleteEvent(anyInt())).thenReturn(true);
    assertTrue(eventService.deleteEvent(1));
  }

  @Test
  void testGetEventByIdNonexistent() {
    expectedEvent.setAvailable(false);
    when(eventRepository.getEventById(anyInt())).thenReturn(expectedEvent);
    try {
      eventService.getEventById(1);
    } catch (Exception e) {
      assertEquals("Event ID does not exist", e.getMessage());
    }
  }

  @Test
  void testGetAllEvents() {
    when(eventRepository.getAllEvents()).thenReturn(List.of(expectedEvent));
    List<EventResponseDTO> actualEvents = eventService.getAllEvents();

    verify(eventRepository, times(1)).getAllEvents();
    assertEquals(1, actualEvents.size());
    assertEquals(expectedEvent.getEventId(), actualEvents.getFirst().eventId());
    assertEquals(expectedEvent.getEventName(), actualEvents.getFirst().eventName());
    assertEquals(expectedEvent.getEventDescription(), actualEvents.getFirst().eventDescription());
    assertEquals(expectedEvent.getEventLocation(), actualEvents.getFirst().eventLocation());
    assertEquals(expectedEvent.getEventStartDate(), actualEvents.getFirst().eventStartDate());
    assertEquals(expectedEvent.getEventEndDate(), actualEvents.getFirst().eventEndDate());
    assertEquals(expectedEvent.isAvailable(), actualEvents.getFirst().isAvailable());
  }

  @Test
  void testGetEventWithTickets() {
    when(eventRepository.getEventById(anyInt())).thenReturn(expectedEvent);
    when(ticketRepository.getTicketsByEventId(anyInt())).thenReturn(List.of(expectedTicket));
    EventWithTicketsDTO actualEventWithTickets = eventService.getEventWithTickets(1);

    assertEquals(expectedEvent.getEventResponseDTO(), actualEventWithTickets.event());
    assertEquals(1, actualEventWithTickets.tickets().size());
    assertEquals(expectedTicket.getTicketResponseDTO(), actualEventWithTickets.tickets().getFirst());
  }
}
