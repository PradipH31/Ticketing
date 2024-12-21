package com.pradiph31.ticketing.dto.ticket;

import java.time.LocalDateTime;

/**
 * TicketResponseDTO record to define the structure of a ticket response data transfer object.
 *
 * @param ticketId     the id of the ticket
 * @param ticketPrice  the price of the ticket
 * @param soldDateTime the date and time the ticket was sold
 * @param isAvailable  the availability of the ticket
 * @param eventId      the id of the event the ticket is for
 */
public record TicketResponseDTO(int ticketId, Double ticketPrice, LocalDateTime soldDateTime,
                                boolean isAvailable, int eventId) {
}
