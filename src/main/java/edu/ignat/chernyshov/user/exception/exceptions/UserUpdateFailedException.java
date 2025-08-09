package edu.ignat.chernyshov.user.exception.exceptions;

public class UserUpdateFailedException extends RuntimeException {
    public UserUpdateFailedException(String message) {
        super(message);
    }

    public UserUpdateFailedException(String message, Exception exception) {
        super(message, exception);
    }
}
