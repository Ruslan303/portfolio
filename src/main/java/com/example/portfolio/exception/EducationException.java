package com.example.portfolio.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EducationException extends RuntimeException{
    public EducationException(String message) {
        super(message);
    }
}
