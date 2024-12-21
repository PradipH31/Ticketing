package com.pradiph31.ticketing.dto.event;

import com.pradiph31.ticketing.model.Event;
import java.time.LocalDateTime;
import java.util.List;

/**
 * EventUpdateDTO record to define the structure of the event update request.
 *
 * @param eventName        the name of the event
 * @param eventDescription the description of the event
 * @param eventTags        the tags associated with the event
 * @param eventLocation    the location of the event
 * @param eventStartDate   the start date and time of the event
 * @param eventEndDate     the end date and time of the event
 * @param isAvailable      the availability status of the event
 */
public record EventUpdateDTO(String eventName, String eventDescription, List<String> eventTags,
                             String eventLocation, LocalDateTime eventStartDate,
                             LocalDateTime eventEndDate, boolean isAvailable) {
  /**
   * Get the Event object created from the EventUpdateDTO object.
   *
   * @return the Event object created from the EventUpdateDTO object
   */
  public Event getEvent() {
    Event event = new Event();
    event.setEventName(eventName);
    event.setEventDescription(eventDescription);
    event.setEventTags(eventTags);
    event.setEventLocation(eventLocation);
    event.setEventStartDate(eventStartDate);
    event.setEventEndDate(eventEndDate);
    event.setAvailable(isAvailable);
    return event;
  }
}
