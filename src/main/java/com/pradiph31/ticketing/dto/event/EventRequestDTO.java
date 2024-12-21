package com.pradiph31.ticketing.dto.event;

import com.pradiph31.ticketing.model.Event;
import java.time.LocalDateTime;
import java.util.List;

/**
 * EventRequestDTO record to define the structure of the event request.
 *
 * @param eventName        the name of the event
 * @param eventDescription the description of the event
 * @param eventTags        the tags associated with the event
 * @param eventLocation    the location of the event
 * @param eventStartDate   the start date and time of the event
 * @param eventEndDate     the end date and time of the event
 */
public record EventRequestDTO(String eventName, String eventDescription, List<String> eventTags,
                              String eventLocation, LocalDateTime eventStartDate,
                              LocalDateTime eventEndDate) {
  /**
   * Get the Event object created from the EventRequestDTO object.
   *
   * @return the Event object created from the EventRequestDTO object
   */
  public Event getEvent() {
    Event event = new Event();
    event.setEventName(eventName);
    event.setEventDescription(eventDescription);
    event.setEventTags(eventTags);
    event.setEventLocation(eventLocation);
    event.setEventStartDate(eventStartDate);
    event.setEventEndDate(eventEndDate);
    return event;
  }
}
