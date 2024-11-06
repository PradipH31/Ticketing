package com.pradiph31.ticketing.validation;

import com.pradiph31.ticketing.validation.validator.NotBeforeNowValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotBeforeNowValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBeforeNow {
  String message() default "must not be before the current date and time";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}