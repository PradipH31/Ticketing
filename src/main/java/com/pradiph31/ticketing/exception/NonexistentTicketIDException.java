package com.pradiph31.ticketing.exception;

/**
 * NonexistentTicketIDException class to handle exceptions when the ticket ID does not exist.
 */
public class NonexistentTicketIDException extends RuntimeException {
  /**
   * Constructs a new NonexistentTicketIDException with the specified message.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public NonexistentTicketIDException(String message) {
    super(message);
  }
}
