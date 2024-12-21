package com.pradiph31.ticketing.exception;

/**
 * NonexistentEventIDException class to handle exceptions when the event ID does not exist.
 */
public class NonexistentEventIDException extends RuntimeException {
  /**
   * Constructs a new NonexistentEventIDException with the specified message.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public NonexistentEventIDException(String message) {
    super(message);
  }
}
