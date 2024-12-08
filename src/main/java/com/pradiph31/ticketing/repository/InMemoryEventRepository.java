package com.pradiph31.ticketing.repository;

import com.pradiph31.ticketing.exception.NonexistentEventIDException;
import com.pradiph31.ticketing.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.pradiph31.ticketing.util.TicketingConstants.NONEXISTENT_EVENT_ID_MESSAGE;

@Repository
public class InMemoryEventRepository implements EventRepository {
  private static final List<Event> events = new ArrayList<>();
  Logger logger = LoggerFactory.getLogger(InMemoryEventRepository.class);

  static {
    Event event1 = new Event();
    event1.setEventId(1);
    event1.setEventName("Coachella");
    event1.setEventDescription("West Coast Music Festival");
    event1.setEventTags(List.of("Music", "Festival"));
    event1.setEventLocation("Indio, CA");
    event1.setEventStartDate(LocalDateTime.of(2025, 9, 1, 0, 0));
    event1.setEventEndDate(LocalDateTime.of(2025, 9, 2, 0, 0));
    event1.setAvailable(true);
    events.add(event1);

    Event event2 = new Event();
    event2.setEventId(2);
    event2.setEventName("Lollapalooza");
    event2.setEventDescription("Midwest Music Festival");
    event2.setEventTags(List.of("Music", "Festival"));
    event2.setEventLocation("Chicago, IL");
    event2.setEventStartDate(LocalDateTime.of(2025, 8, 1, 0, 0));
    event2.setEventEndDate(LocalDateTime.of(2025, 8, 2, 0, 0));
    event2.setAvailable(true);
    events.add(event2);
  }

  @Override
  public Event saveEvent(Event event) {
    event.setAvailable(true);
    event.setEventId(events.size() + 1);
    events.add(event);
    return event;
  }

  @Override
  public Event getEventById(int eventId) {
    if (eventId < 1 || eventId > events.size()) {
      logNonexistentEventIdError(eventId);
      throw new NonexistentEventIDException(NONEXISTENT_EVENT_ID_MESSAGE);
    }
    return events
            .stream()
            .filter(event -> event.getEventId() == eventId)
            .findFirst()
            .orElse(null);
  }

  @Override
  public Event updateEvent(Event event) {
    if (event.getEventId() < 0 || event.getEventId() >= events.size()) {
      logNonexistentEventIdError(event.getEventId());
      throw new NonexistentEventIDException(NONEXISTENT_EVENT_ID_MESSAGE);
    }
    events.set(event.getEventId(), event);
    return event;
  }

  @Override
  public boolean deleteEvent(int eventId) {
    Event event = getEventById(eventId);
    event.deleteEvent();
    return true;
  }

  @Override
  public List<Event> getAllEvents() {
    return events;
  }

  private void logNonexistentEventIdError(int eventId) {
    logger.error("Event ID {} does not exist at {}", eventId, LocalDateTime.now());
  }
}
