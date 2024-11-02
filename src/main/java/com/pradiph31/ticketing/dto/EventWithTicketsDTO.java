package com.pradiph31.ticketing.dto;

import com.pradiph31.ticketing.dto.event.EventResponseDTO;
import com.pradiph31.ticketing.dto.ticket.TicketResponseDTO;

import java.util.List;

public record EventWithTicketsDTO(EventResponseDTO event, List<TicketResponseDTO> tickets) {
}