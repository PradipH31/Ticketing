package com.pradiph31.ticketing.exception;

public class NonexistentEventIDException extends RuntimeException {
  public NonexistentEventIDException(String message) {
    super(message);
  }
}
