package com.pradiph31.ticketing.validation;

import com.pradiph31.ticketing.validation.validator.EndDateAfterStartDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EndDateAfterStartDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
  public @interface EndDateAfterStartDate {
  String message() default "eventEndDate must be after eventStartDate";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}