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

@RestController
@RequestMapping("/api/events")
public class EventController {

  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping
  public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
    return ResponseEntity.ok(eventService.getAllEvents());
  }

  @GetMapping("/{eventId}")
  public ResponseEntity<EventResponseDTO> getEventById(@PathVariable int eventId) {
    return ResponseEntity.ok(eventService.getEventById(eventId));
  }

  @PostMapping
  public ResponseEntity<EventResponseDTO> saveEvent(@RequestBody EventRequestDTO eventDTO) {
    return ResponseEntity.ok(eventService.saveEvent(eventDTO));
  }

  @PutMapping("/{eventId}")
  public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable int eventId,
                                                      @RequestBody EventUpdateDTO eventDTO) {
    return ResponseEntity.ok(eventService.updateEvent(eventId, eventDTO));
  }

  @DeleteMapping("/{eventId}")
  public ResponseEntity<Boolean> deleteEvent(@PathVariable int eventId) {
    return ResponseEntity.ok(eventService.deleteEvent(eventId));
  }

  @GetMapping("/{eventId}/tickets")
  public ResponseEntity<EventWithTicketsDTO> getEventWithTickets(@PathVariable int eventId) {
    return ResponseEntity.ok(eventService.getEventWithTickets(eventId));
  }
}
