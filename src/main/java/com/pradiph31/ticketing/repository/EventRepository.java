package com.pradiph31.ticketing.repository;

import com.pradiph31.ticketing.model.Event;

import java.util.List;

public interface EventRepository {
  Event saveEvent(Event event);

  Event getEventById(int eventId);

  Event updateEvent(Event event);

  boolean deleteEvent(int eventId);

  List<Event> getAllEvents();
}
