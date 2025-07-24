package io.github.joaoVitorLeal.gof_with_spring.domain.exceptions;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException() {
        super("Address not found.");
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
