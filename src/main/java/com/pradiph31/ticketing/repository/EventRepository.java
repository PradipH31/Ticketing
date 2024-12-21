package com.pradiph31.ticketing.repository;

import com.pradiph31.ticketing.model.Event;
import java.util.List;

/**
 * EventRepository interface to define the methods for EventRepository.
 */
public interface EventRepository {
  /**
   * Save the event object to the database.
   *
   * @param event Event object to be saved.
   * @return a saved Event object with the updated id
   */
  Event saveEvent(Event event);

  /**
   * Get the event with the given id.
   *
   * @param eventId the id of the event to be retrieved.
   * @return the event with the given id.
   */
  Event getEventById(int eventId);

  /**
   * Update the event object in the database.
   *
   * @param event Event object to be updated.
   * @return an updated Event object
   */
  Event updateEvent(Event event);

  /**
   * Delete the event with the given id.
   *
   * @param eventId the id of the event to be deleted.
   * @return true if the event was deleted successfully.
   */
  boolean deleteEvent(int eventId);

  /**
   * Get all events in the database.
   *
   * @return a list of all events in the database.
   */
  List<Event> getAllEvents();
}
