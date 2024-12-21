package com.pradiph31.ticketing.dto.event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * EventResponseDTO record to define the structure of the event response.
 *
 * @param eventId          the id of the event
 * @param eventName        the name of the event
 * @param eventDescription the description of the event
 * @param eventTags        the tags associated with the event
 * @param eventLocation    the location of the event
 * @param eventStartDate   the start date and time of the event
 * @param eventEndDate     the end date and time of the event
 * @param isAvailable      the availability status of the event
 */
public record EventResponseDTO(int eventId, String eventName, String eventDescription,
                               List<String> eventTags, String eventLocation,
                               LocalDateTime eventStartDate, LocalDateTime eventEndDate,
                               boolean isAvailable) {
}