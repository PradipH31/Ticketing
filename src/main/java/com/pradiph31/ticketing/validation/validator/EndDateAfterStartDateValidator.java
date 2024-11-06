package com.pradiph31.ticketing.validation.validator;

import com.pradiph31.ticketing.model.Event;
import com.pradiph31.ticketing.validation.EndDateAfterStartDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EndDateAfterStartDateValidator implements ConstraintValidator<EndDateAfterStartDate, Event> {

  @Override
  public boolean isValid(Event event, ConstraintValidatorContext context) {
    if (event == null || event.getEventStartDate() == null || event.getEventEndDate() == null) {
      return true;
    }
    return event.getEventEndDate().isAfter(event.getEventStartDate());
  }
}