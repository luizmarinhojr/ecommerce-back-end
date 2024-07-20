package ecommerce.com.backend.infra.exception;

public class ValidationExceptionFields extends RuntimeException{

    public ValidationExceptionFields(String message) {
        super(message);
    }
}
