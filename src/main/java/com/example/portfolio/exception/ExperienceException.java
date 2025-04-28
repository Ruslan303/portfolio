package com.example.portfolio.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExperienceException extends RuntimeException{
    public ExperienceException(String message){
        super(message);
    }
}
