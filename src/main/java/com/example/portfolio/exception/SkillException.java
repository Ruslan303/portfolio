package com.example.portfolio.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SkillException extends RuntimeException {
    public SkillException(String message) {
        super(message);
    }
}
