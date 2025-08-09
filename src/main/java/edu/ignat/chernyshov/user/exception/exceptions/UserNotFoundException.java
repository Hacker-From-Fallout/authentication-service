package edu.ignat.chernyshov.user.exception.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Exception exception) {
        super(message, exception);
    }
}
