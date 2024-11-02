package com.pradiph31.ticketing.dto.ticket;

import java.time.LocalDateTime;

public record TicketResponseDTO(int ticketId, Double ticketPrice, LocalDateTime soldDateTime, boolean isAvailable, int eventId) {
}
