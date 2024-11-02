package com.pradiph31.ticketing.exception;

public class NonexistentTicketIDException extends RuntimeException {
  public NonexistentTicketIDException(String message) {
    super(message);
  }
}
