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

    // @ExceptionHandler(UserNotFoundException.class)
    // public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException error) {
    //     ResponseError responseError = new ResponseError(error.getMessage());
    //     log.error("Пользователь не найден", error.getMessage(), error);
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //         .header("Content-Type", "application/problem+json")
    //         .body(responseError.toString());
    // }

    private final MessageSource messageSource ;
	
	@ExceptionHandler(BindException.class) 
	public ResponseEntity<ProblemDetail> handleBindException(BindException exception, Locale locale) {
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        this.messageSource.getMessage(exception.getMessage(), new Object[0],
                                exception.getMessage(), locale)));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleAlreadyExistsException(AlreadyExistsException exception,
                                                                    Locale locale) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                        this.messageSource.getMessage(exception.getMessage(), new Object[0],
                                exception.getMessage(), locale)));
    }
}

