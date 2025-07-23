package io.github.joaoVitorLeal.gof_with_spring.exceptions;

public class InvalidFieldsCustomerException extends RuntimeException {

    public InvalidFieldsCustomerException() {
        super("Customer not found.");
    }
    public InvalidFieldsCustomerException(String message) {
        super(message);
    }

    public InvalidFieldsCustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
