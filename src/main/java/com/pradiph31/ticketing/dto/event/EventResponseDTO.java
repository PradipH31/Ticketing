package com.pradiph31.ticketing.dto.event;

import java.time.LocalDateTime;
import java.util.List;

public record EventResponseDTO(int eventId, String eventName, String eventDescription,
                               List<String> eventTags, String eventLocation,
                               LocalDateTime eventStartDate, LocalDateTime eventEndDate,
                               boolean isAvailable) {
}