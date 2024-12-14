package com.pradiph31.ticketing.dto.event;

import com.pradiph31.ticketing.model.Event;
import java.time.LocalDateTime;
import java.util.List;

public record EventRequestDTO(String eventName, String eventDescription, List<String> eventTags,
                              String eventLocation, LocalDateTime eventStartDate,
                              LocalDateTime eventEndDate) {
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
