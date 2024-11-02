package com.pradiph31.ticketing.dto.event;

import java.time.LocalDateTime;

public record EventResponseDTO(int eventId, String eventName, String eventDescription, String eventLocation, LocalDateTime eventStartDate, LocalDateTime eventEndDate, boolean isAvailable) { }