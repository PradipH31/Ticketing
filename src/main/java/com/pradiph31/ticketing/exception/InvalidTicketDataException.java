package com.pradiph31.ticketing.exception;

public class InvalidTicketDataException extends RuntimeException {
  public InvalidTicketDataException(String message) {
    super(message);
  }
}
