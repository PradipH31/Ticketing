package com.pradiph31.ticketing.dto.event;

import com.pradiph31.ticketing.model.Event;

import java.time.LocalDateTime;

public record EventUpdateDTO(String eventName, String eventDescription, String eventLocation, LocalDateTime eventStartDate, LocalDateTime eventEndDate, boolean isAvailable) {
  public Event getEvent() {
    Event event = new Event();
    event.setEventName(eventName);
    event.setEventDescription(eventDescription);
    event.setEventLocation(eventLocation);
    event.setEventStartDate(eventStartDate);
    event.setEventEndDate(eventEndDate);
    event.setAvailable(isAvailable);
    return event;
  }
}
