package patientcaretrackbackend.shared.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import patientcaretrackbackend.shared.exception.ForbiddenException;
import patientcaretrackbackend.shared.exception.NotFoundException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    record ErrorResponse(
            Instant timestamp,
            int status,
            String error,
            String message
    ) {}

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(
                        Instant.now(),
                        404,
                        "NOT_FOUND",
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(
                        Instant.now(),
                        400,
                        "BAD_REQUEST",
                        ex.getMessage()
                )
        );
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ErrorResponse(
                        Instant.now(),
                        403,
                        "FORBIDDEN",
                        ex.getMessage()
                )
        );
    }
}
