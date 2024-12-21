package com.pradiph31.ticketing.dto;

import com.pradiph31.ticketing.dto.event.EventResponseDTO;
import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;
import java.util.List;

/**
 * EventWithTicketsDTO record to define the structure of the event with tickets.
 *
 * @param event   the event details
 * @param tickets the list of tickets associated with the event
 */
public record EventWithTicketsDTO(EventResponseDTO event, List<TicketResponseDTO> tickets) {
}