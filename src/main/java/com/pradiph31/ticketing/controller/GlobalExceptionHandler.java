package com.pradiph31.ticketing.controller;

import com.pradiph31.ticketing.dto.ErrorResponseDTO;
import com.pradiph31.ticketing.exception.InvalidEventDataException;
import com.pradiph31.ticketing.exception.InvalidTicketDataException;
import com.pradiph31.ticketing.exception.NonexistentEventIDException;
import com.pradiph31.ticketing.exception.NonexistentTicketIDException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({NonexistentTicketIDException.class, NonexistentEventIDException.class})
  public ResponseEntity<ErrorResponseDTO> handleNonexistentTicketIDException(RuntimeException exception) {
    return ResponseEntity.status(404)
                         .body(new ErrorResponseDTO(exception.getMessage(),
                                                    LocalDateTime.now(),
                                                    HttpStatus.NOT_FOUND.value()));
  }

  @ExceptionHandler({InvalidTicketDataException.class, InvalidEventDataException.class})
  public ResponseEntity<ErrorResponseDTO> handleInvalidDataException(RuntimeException exception) {
    return ResponseEntity.status(400)
                         .body(new ErrorResponseDTO(exception.getMessage(),
                                                    LocalDateTime.now(),
                                                    HttpStatus.BAD_REQUEST.value()));
  }
}
