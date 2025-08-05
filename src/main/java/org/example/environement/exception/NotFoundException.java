package org.example.environement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class NotFoundException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final LocalDateTime time;

    public NotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
        this.time = LocalDateTime.now();
    }
}
