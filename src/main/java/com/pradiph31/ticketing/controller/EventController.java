package com.pradiph31.ticketing.controller;

import com.pradiph31.ticketing.dto.EventWithTicketsDTO;
import com.pradiph31.ticketing.dto.event.EventRequestDTO;
import com.pradiph31.ticketing.dto.event.EventResponseDTO;
import com.pradiph31.ticketing.dto.event.EventUpdateDTO;
import com.pradiph31.ticketing.service.EventService;
import java.util.List;
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
 * EventController class to handle event-related API requests.
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;

  /**
   * Constructor to inject the event service into the controller.
   *
   * @param eventService the event service to be injected into the controller.
   */
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping
  public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
    return ResponseEntity.ok(eventService.getAllEvents());
  }

  /**
   * Get the event with the given id.
   *
   * @param eventId the id of the event to be retrieved.
   * @return the event with the given id.
   */
  @GetMapping("/{eventId}")
  public ResponseEntity<EventResponseDTO> getEventById(@PathVariable int eventId) {
    return ResponseEntity.ok(eventService.getEventById(eventId));
  }

  /**
   * Save the given event.
   *
   * @param eventDTO the event to be saved.
   * @return the saved event.
   */
  @PostMapping
  public ResponseEntity<EventResponseDTO> saveEvent(@RequestBody EventRequestDTO eventDTO) {
    return ResponseEntity.ok(eventService.saveEvent(eventDTO));
  }

  /**
   * Update the event with the given id.
   *
   * @param eventId  the id of the event to be updated.
   * @param eventDTO the updated event information.
   * @return the updated event.
   */
  @PutMapping("/{eventId}")
  public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable int eventId,
                                                      @RequestBody EventUpdateDTO eventDTO) {
    return ResponseEntity.ok(eventService.updateEvent(eventId, eventDTO));
  }

  /**
   * Delete the event with the given id.
   *
   * @param eventId the id of the event to be deleted.
   * @return true response entity if the event was deleted successfully.
   */
  @DeleteMapping("/{eventId}")
  public ResponseEntity<Boolean> deleteEvent(@PathVariable int eventId) {
    return ResponseEntity.ok(eventService.deleteEvent(eventId));
  }

  /**
   * Get the event with the given id along with its tickets.
   *
   * @param eventId the id of the event to be retrieved.
   * @return the event with the given id along with its tickets.
   */
  @GetMapping("/{eventId}/tickets")
  public ResponseEntity<EventWithTicketsDTO> getEventWithTickets(@PathVariable int eventId) {
    return ResponseEntity.ok(eventService.getEventWithTickets(eventId));
  }
}
