package ecommerce.com.backend.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ValidationExceptionFields.class)
    public ResponseEntity<ExceptionDto> handler404(ValidationExceptionFields ex) {
        return ResponseEntity.status(422).body(new ExceptionDto(ex.getMessage()));
    }

}
