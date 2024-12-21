package com.pradiph31.ticketing.exception;

/**
 * InvalidTicketDataException class to handle exceptions when the ticket data is invalid.
 */
public class InvalidTicketDataException extends RuntimeException {
  /**
   * Constructs a new InvalidTicketDataException with the specified message.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public InvalidTicketDataException(String message) {
    super(message);
  }
}
