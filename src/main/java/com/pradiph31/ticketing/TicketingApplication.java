package com.pradiph31.ticketing;

import com.pradiph31.ticketing.model.Event;
import com.pradiph31.ticketing.model.Ticket;
import com.pradiph31.ticketing.repository.EventRestRepository;
import com.pradiph31.ticketing.repository.TicketRestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class TicketingApplication {

	private static final Random random = new Random();
	public static void main(String[] args) {
		SpringApplication.run(TicketingApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(EventRestRepository eventRestRepository, TicketRestRepository ticketRestRepository) {
		return args -> {
			Event event = Event.builder()
							.eventName("Coachella")
							.eventDescription("Music Festival")
							.eventLocation("California")
							.eventStartDate(LocalDateTime.now().plusMonths(5))
							.eventEndDate(LocalDateTime.now().plusMonths(6))
							.isAvailable(true)
							.deleted(false)
							.build();
			eventRestRepository.save(event);
			event = Event.builder()
							.eventName("Tomorrowland")
							.eventDescription("Music Festival")
							.eventLocation("Belgium")
							.eventStartDate(LocalDateTime.now().plusMonths(7))
							.eventEndDate(LocalDateTime.now().plusMonths(8))
							.isAvailable(true)
							.deleted(false)
							.build();
			eventRestRepository.save(event);
            List<Event> events = StreamSupport.stream(eventRestRepository.findAll().spliterator(), false).toList();
			IntStream.range(0, 20).parallel().forEach(i -> {
				Event randomEvent = events.get(random.nextInt(events.size()));
				ticketRestRepository.save(Ticket.builder()
								.ticketPrice(
                                        BigDecimal.valueOf(random.nextDouble(20.0, 500.0))
                                                .setScale(2, RoundingMode.HALF_UP)
                                                .doubleValue()
                                )
								.soldDateTime(null)
								.isAvailable(true)
								.deleted(false)
								.event(randomEvent)
								.build());
			});
		};
	}

}
