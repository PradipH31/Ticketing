package com.pradiph31.ticketing.exception;

/**
 * InvalidEventDataException class to handle exceptions when the event data is invalid.
 */
public class InvalidEventDataException extends RuntimeException {
  /**
   * Constructs a new InvalidEventDataException with the specified message.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public InvalidEventDataException(String message) {
    super(message);
  }
}
