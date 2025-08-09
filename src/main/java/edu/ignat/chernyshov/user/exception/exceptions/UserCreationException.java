package edu.ignat.chernyshov.user.exception.exceptions;

public class UserCreationException extends RuntimeException {
    public UserCreationException(String message) {
        super(message);
    }

    public UserCreationException(String message, Exception exception) {
        super(message, exception);
    }
}
