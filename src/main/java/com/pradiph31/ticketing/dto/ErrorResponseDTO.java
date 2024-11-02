package com.pradiph31.ticketing.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO (String message, LocalDateTime timestamp, int status) { }
