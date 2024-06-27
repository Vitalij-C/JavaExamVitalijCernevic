package lt.java.exam.exception;

import lt.java.exam.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler({UserExistsException.class})
    protected ResponseEntity<ErrorResponse> handle(UserExistsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .code(exception.getClass().getSimpleName())
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler({UserNotExistsException.class})
    protected ResponseEntity<ErrorResponse> handle(UserNotExistsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .code(exception.getClass().getSimpleName())
                                .message(exception.getMessage())
                                .build()
                );
    }
}
