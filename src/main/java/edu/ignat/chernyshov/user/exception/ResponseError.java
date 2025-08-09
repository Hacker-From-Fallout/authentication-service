package edu.ignat.chernyshov.user.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError {
    private String message;

    public String toString() {
        return message;
    }
}
