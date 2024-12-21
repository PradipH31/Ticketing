package com.pradiph31.ticketing.service;

import static com.pradiph31.ticketing.util.TicketingConstants.INVALID_EVENT_DATA;
import static com.pradiph31.ticketing.util.TicketingConstants.NONEXISTENT_EVENT_ID_MESSAGE;

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
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The EventService class is a service class that implements the business logic for event
 * operations.
 */
@Service
public class EventService {
  private final EventRepository eventRepository;
  private final TicketRepository ticketRepository;
  Logger logger = LoggerFactory.getLogger(EventService.class);

  /**
   * Constructor to inject the event repository and ticket repository into the service.
   *
   * @param eventRepository  the event repository to be injected into the service.
   * @param ticketRepository the ticket repository to be injected into the service.
   */
  public EventService(EventRepository eventRepository, TicketRepository ticketRepository) {
    this.eventRepository = eventRepository;
    this.ticketRepository = ticketRepository;
  }

  /**
   * Saves the event request DTO to the event repository.
   *
   * @param eventDTO the event request DTO to be saved.
   * @return the saved event response DTO.
   */
  public EventResponseDTO saveEvent(EventRequestDTO eventDTO) {
    if (eventDTO == null
        || eventDTO.eventStartDate() == null
        || eventDTO.eventEndDate() == null
        || eventDTO.eventStartDate()
                   .isBefore(LocalDateTime.now())
        || eventDTO.eventEndDate()
                   .isBefore(eventDTO.eventStartDate())) {
      logger.error("Invalid event info for {}", eventDTO);
      throw new InvalidEventDataException(INVALID_EVENT_DATA);
    }
    return eventRepository.saveEvent(eventDTO.getEvent())
                          .getEventResponseDTO();
  }

  /**
   * Get the event with the given id.
   *
   * @param eventId the id of the event to be retrieved.
   * @return the event with the given id.
   */
  public EventResponseDTO getEventById(int eventId) {
    Event event = eventRepository.getEventById(eventId);
    if (event.isDeleted() || !event.isAvailable()) {
      logger.error("Event {} does not exist", eventId);
      throw new NonexistentTicketIDException(NONEXISTENT_EVENT_ID_MESSAGE);
    }
    return event.getEventResponseDTO();
  }

  /**
   * Update the event with the given id.
   *
   * @param eventId  the id of the event to be updated.
   * @param eventDTO the updated event information.
   * @return the updated event.
   */
  public EventResponseDTO updateEvent(int eventId, EventUpdateDTO eventDTO) {
    if (eventDTO == null
        || eventId < 0
        || eventDTO.eventStartDate() == null
        || eventDTO.eventEndDate() == null
        || eventDTO.eventStartDate()
                   .isBefore(LocalDateTime.now())
        || eventDTO.eventEndDate()
                   .isBefore(eventDTO.eventStartDate())) {
      logger.error("Invalid update request for {}", eventDTO);
      throw new InvalidEventDataException(INVALID_EVENT_DATA);
    }
    eventRepository.getEventById(eventId);
    Event event = eventDTO.getEvent();
    event.setEventId(eventId);
    return eventRepository.updateEvent(event)
                          .getEventResponseDTO();
  }

  /**
   * Delete the event with the given id.
   *
   * @param eventId the id of the event to be deleted.
   * @return true if the event was deleted successfully.
   */
  public boolean deleteEvent(int eventId) {
    return eventRepository.deleteEvent(eventId);
  }

  /**
   * Get all events in the database.
   *
   * @return a list of all events in the database.
   */
  public List<EventResponseDTO> getAllEvents() {
    return eventRepository.getAllEvents()
                          .stream()
                          .filter(event -> !event.isDeleted())
                          .map(Event::getEventResponseDTO)
                          .toList();
  }

  /**
   * Get the event with the given id along with its tickets.
   *
   * @param eventId the id of the event to be retrieved.
   * @return the event with the given id along with its tickets.
   */
  public EventWithTicketsDTO getEventWithTickets(int eventId) {
    Event event = eventRepository.getEventById(eventId);
    List<Ticket> tickets = ticketRepository.getTicketsByEventId(eventId);
    return new EventWithTicketsDTO(event.getEventResponseDTO(),
                                   tickets.stream()
                                          .map(Ticket::getTicketResponseDTO)
                                          .toList());
  }
}
