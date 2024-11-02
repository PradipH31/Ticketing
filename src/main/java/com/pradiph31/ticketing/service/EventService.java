package com.pradiph31.ticketing.service;

import com.pradiph31.ticketing.dto.EventWithTicketsDTO;
import com.pradiph31.ticketing.dto.event.EventRequestDTO;
import com.pradiph31.ticketing.dto.event.EventResponseDTO;
import com.pradiph31.ticketing.dto.event.EventUpdateDTO;
import com.pradiph31.ticketing.exception.InvalidEventDataException;
import com.pradiph31.ticketing.exception.NonexistentTicketIDException;
import com.pradiph31.ticketing.model.Event;
import com.pradiph31.ticketing.model.Ticket;
import com.pradiph31.ticketing.repository.EventRepository;
import com.pradiph31.ticketing.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.pradiph31.ticketing.util.TicketingConstants.INVALID_EVENT_DATA;
import static com.pradiph31.ticketing.util.TicketingConstants.NONEXISTENT_EVENT_ID_MESSAGE;

@Service
public class EventService {
  private final EventRepository eventRepository;
  private final TicketRepository ticketRepository;
  Logger logger = LoggerFactory.getLogger(EventService.class);

  public EventService(EventRepository eventRepository, TicketRepository ticketRepository) {
    this.eventRepository = eventRepository;
    this.ticketRepository = ticketRepository;
  }

  public EventResponseDTO saveEvent(EventRequestDTO eventDTO) {
    if (eventDTO == null || eventDTO.eventStartDate() == null || eventDTO.eventEndDate() == null || eventDTO.eventStartDate().isBefore(LocalDateTime.now()) || eventDTO.eventEndDate().isBefore(eventDTO.eventStartDate())) {
      logger.error("Invalid event info for {} at {}", eventDTO, LocalDateTime.now());
      throw new InvalidEventDataException(INVALID_EVENT_DATA);
    }
    return eventRepository.saveEvent(eventDTO.getEvent()).getEventResponseDTO();
  }

  public EventResponseDTO getEventById(int eventId) {
    Event event = eventRepository.getEventById(eventId);
    if (event.isDeleted() || !event.isAvailable()) {
      logger.error("Event {} does not exist at {}", eventId, LocalDateTime.now());
      throw new NonexistentTicketIDException(NONEXISTENT_EVENT_ID_MESSAGE);
    }
    return event.getEventResponseDTO();
  }

  public EventResponseDTO updateEvent(int eventId, EventUpdateDTO eventDTO) {
    if (eventDTO == null || eventId < 0 || eventDTO.eventStartDate() == null || eventDTO.eventEndDate() == null || eventDTO.eventStartDate().isBefore(LocalDateTime.now()) || eventDTO.eventEndDate().isBefore(eventDTO.eventStartDate())) {
      logger.error("Invalid update request for {} at {}", eventDTO, LocalDateTime.now());
      throw new InvalidEventDataException(INVALID_EVENT_DATA);
    }
    eventRepository.getEventById(eventId);
    Event event = eventDTO.getEvent();
    event.setEventId(eventId);
    return eventRepository.updateEvent(event).getEventResponseDTO();
  }

  public boolean deleteEvent(int eventId) {
    return eventRepository.deleteEvent(eventId);
  }

  public List<EventResponseDTO> getAllEvents() {
    return eventRepository.getAllEvents().stream().filter(event -> !event.isDeleted()).map(Event::getEventResponseDTO).toList();
  }

  public EventWithTicketsDTO getEventWithTickets(int eventId) {
    Event event = eventRepository.getEventById(eventId);
    List<Ticket> tickets = ticketRepository.getTicketsByEventId(eventId);
    return new EventWithTicketsDTO(event.getEventResponseDTO(),
            tickets.stream().map(Ticket::getTicketResponseDTO).toList());
  }
}
