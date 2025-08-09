package edu.ignat.chernyshov.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.ignat.chernyshov.user.exception.exceptions.UserCreationException;
import edu.ignat.chernyshov.user.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException error) {
        ResponseError responseError = new ResponseError(error.getMessage());
        log.error("Пользователь не найден", error.getMessage(), error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .header("Content-Type", "application/problem+json")
            .body(responseError.toString());
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<String> handleUserCreationException(UserCreationException error) {
        log.error("Не удалось создать нового пользователя", error);
        ResponseError responseError = new ResponseError(error.getMessage());
        log.error("Пользователь не найден", error.getMessage(), error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .header("Content-Type", "application/problem+json")
            .body(responseError.toString());
    }
}
