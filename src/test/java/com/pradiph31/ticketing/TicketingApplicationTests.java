package com.pradiph31.ticketing;

import com.pradiph31.ticketing.repository.EventRestRepository;
import com.pradiph31.ticketing.repository.TicketRestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TicketingApplicationTests {

	@Autowired
	private EventRestRepository eventRestRepository;

	@Autowired
	private TicketRestRepository ticketRestRepository;

	@Test
	void contextLoads() {
		assertThat(eventRestRepository).isNotNull();
		assertThat(ticketRestRepository).isNotNull();
	}

}
