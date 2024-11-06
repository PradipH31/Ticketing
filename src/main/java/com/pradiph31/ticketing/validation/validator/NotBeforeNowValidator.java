package com.pradiph31.ticketing.validation.validator;

import com.pradiph31.ticketing.validation.NotBeforeNow;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class NotBeforeNowValidator implements ConstraintValidator<NotBeforeNow, LocalDateTime> {

  @Override
  public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
    return value != null && !value.isBefore(LocalDateTime.now());
  }
}