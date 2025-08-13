package edu.ignat.chernyshov.user.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import edu.ignat.chernyshov.user.exception.exceptions.AlreadyExistsException;
import edu.ignat.chernyshov.user.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException exception, Locale locale) {
        log.warn("BindException: {}", exception.getMessage(), exception);
        
        ProblemDetail problemDetail = ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST,
                this.messageSource.getMessage("errors.400.title", new Object[0],
                    "errors.400.title", locale));
        problemDetail.setProperty("errors",
            exception.getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .toList());

        return ResponseEntity.badRequest()
            .body(problemDetail);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserNotFoundException(UserNotFoundException exception,
        Locale locale) {
        log.info("UserNotFoundException: {}", exception.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                this.messageSource.getMessage(exception.getMessage(), new Object[0],
                    exception.getMessage(), locale)));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleAlreadyExistsException(AlreadyExistsException exception,
        Locale locale) {
        log.warn("AlreadyExistsException: {}", exception.getMessage());
        
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                this.messageSource.getMessage(exception.getMessage(), new Object[0],
                    exception.getMessage(), locale)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception exception, Locale locale) {
        log.error("Unexpected error occurred: ", exception);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                this.messageSource.getMessage("Ошибка на сервере", new Object[0],
                    "Ошибка на сервере", locale)));
    }
}