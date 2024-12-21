package com.pradiph31.ticketing.dto;

import java.time.LocalDateTime;

/**
 * ErrorResponseDTO class to define the structure of the error response.
 *
 * @param message   the error message
 * @param timestamp the time at which the error occurred
 * @param status    the status code of the error
 */
public record ErrorResponseDTO(String message, LocalDateTime timestamp, int status) {
}
