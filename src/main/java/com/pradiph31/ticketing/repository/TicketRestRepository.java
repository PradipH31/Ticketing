package com.pradiph31.ticketing.repository;

import com.pradiph31.ticketing.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TicketRestRepository extends PagingAndSortingRepository<Ticket, Long>, CrudRepository<Ticket, Long> {
}
