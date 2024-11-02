package com.pradiph31.ticketing.model;

import com.pradiph31.ticketing.dto.event.EventResponseDTO;

import java.time.LocalDateTime;

public class Event {
  private int eventId;
  private String eventName;
  private String eventDescription;
  private String eventLocation;
  private LocalDateTime eventStartDate;
  private LocalDateTime eventEndDate;
  private boolean isAvailable;
  private boolean deleted;

  public int getEventId() {
    return eventId;
  }

  public void setEventId(int eventId) {
    this.eventId = eventId;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public String getEventLocation() {
    return eventLocation;
  }

  public void setEventLocation(String eventLocation) {
    this.eventLocation = eventLocation;
  }

  public LocalDateTime getEventStartDate() {
    return eventStartDate;
  }

  public void setEventStartDate(LocalDateTime eventStartDate) {
    this.eventStartDate = eventStartDate;
  }

  public LocalDateTime getEventEndDate() {
    return eventEndDate;
  }

  public void setEventEndDate(LocalDateTime eventEndDate) {
    this.eventEndDate = eventEndDate;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public void deleteEvent() {
    this.deleted = true;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public EventResponseDTO getEventResponseDTO() {
    return new EventResponseDTO(this.eventId, this.eventName, this.eventDescription, this.eventLocation,
            this.eventStartDate, this.eventEndDate, this.isAvailable);
  }

}
