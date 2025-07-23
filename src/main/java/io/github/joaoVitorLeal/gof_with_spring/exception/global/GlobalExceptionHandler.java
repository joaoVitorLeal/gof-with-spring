package io.github.joaoVitorLeal.gof_with_spring.exception.global;

import io.github.joaoVitorLeal.gof_with_spring.domain.exceptions.CustomerNotFoundException;
import io.github.joaoVitorLeal.gof_with_spring.domain.exceptions.InvalidFieldsCustomerException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);static

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        logger.error("Validation error at [{}]: {}", request.getRequestURI(), ex.toString(), ex);

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        new ErrorResponseDTO(
                                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                                errors,
                                request.getRequestURI()
                        )
                );
    }

    @ExceptionHandler(InvalidFieldsCustomerException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidFieldsCustomerException(InvalidFieldsCustomerException ex, HttpServletRequest request) {
        logger.error("Invalid Customer fields error at [{}]: {}", request.getRequestURI(), ex.toString(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponseDTO(
                                HttpStatus.BAD_REQUEST.value(),
                                List.of(ex.getMessage()),
                                request.getRequestURI()
                        )
                );
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFoundException(CustomerNotFoundException ex, HttpServletRequest request) {
        logger.error("Customer not found error at [{}]: {}", request.getRequestURI(), ex.toString(), ex);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponseDTO(
                                HttpStatus.NOT_FOUND.value(),
                                List.of(extractMessage(ex)),
                                request.getRequestURI()
                        )
                );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleUntreatedErrors(RuntimeException ex, HttpServletRequest request) {
        logger.error("Unexpected error at [{}]: {}", request.getRequestURI(), ex.toString(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorResponseDTO(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                List.of("An unexpected error occurred. Please try again later."),
                                request.getRequestURI()
                        )
                );
    }

    private String extractMessage(Throwable ex) {
        String message = ex.getMessage();
        if (message == null || message.isBlank()) {
            logger.warn("Exception with null/empty message: {}", ex.getClass().getSimpleName());
            return "Unexpected server error.";
        }
        return message;
    }
}
