package edu.ignat.chernyshov.user.exception.exceptions;

public class UserDeleteFailedException extends RuntimeException {
    public UserDeleteFailedException(String message) {
        super(message);
    }

    public UserDeleteFailedException(String message, Exception exception) {
        super(message, exception);
    }
}
