package com.pradiph31.ticketing.controller;

import com.pradiph31.ticketing.dto.EventWithTicketsDTO;
import com.pradiph31.ticketing.dto.event.EventRequestDTO;
import com.pradiph31.ticketing.dto.event.EventResponseDTO;
import com.pradiph31.ticketing.dto.event.EventUpdateDTO;
import com.pradiph31.ticketing.service.EventService;
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

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;
  Logger logger = LoggerFactory.getLogger(EventController.class);

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping
  public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
    logger.info("Fetching all events at {}", LocalDateTime.now());
    return ResponseEntity.ok(eventService.getAllEvents());
  }

  @GetMapping("/{eventId}")
  public ResponseEntity<EventResponseDTO> getEventById(@PathVariable int eventId) {
    logger.info("Fetching event with id {} at {}", eventId, LocalDateTime.now());
    return ResponseEntity.ok(eventService.getEventById(eventId));
  }

  @PostMapping
  public ResponseEntity<EventResponseDTO> saveEvent(@RequestBody EventRequestDTO eventDTO) {
    logger.info("Saving event {} at {}", eventDTO, LocalDateTime.now());
    return ResponseEntity.ok(eventService.saveEvent(eventDTO));
  }

  @PutMapping("/{eventId}")
  public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable int eventId, @RequestBody EventUpdateDTO eventDTO) {
    logger.info("Updating event {} with {} at {}", eventId, eventDTO, LocalDateTime.now());
    return ResponseEntity.ok(eventService.updateEvent(eventId, eventDTO));
  }

  @DeleteMapping("/{eventId}")
  public ResponseEntity<Boolean> deleteEvent(@PathVariable int eventId) {
    logger.info("Deleting event with id {} at {}", eventId, LocalDateTime.now());
    return ResponseEntity.ok(eventService.deleteEvent(eventId));
  }

  @GetMapping("/{eventId}/tickets")
  public ResponseEntity<EventWithTicketsDTO> getEventWithTickets(@PathVariable int eventId) {
    logger.info("Fetching event with tickets with id {} at {}", eventId, LocalDateTime.now());
    return ResponseEntity.ok(eventService.getEventWithTickets(eventId));
  }
}
