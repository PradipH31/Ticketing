package com.pradiph31.ticketing.repository;

import com.pradiph31.ticketing.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRestRepository extends PagingAndSortingRepository<Event, Long>, CrudRepository<Event, Long> {
}
