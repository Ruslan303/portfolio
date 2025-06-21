package com.example.portfolio.exception;

import com.example.portfolio.Entity.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailException extends RuntimeException {
    public EmailException(ErrorMessage errorMessage, Object... args) {
        super(errorMessage.format(args)); // Dinamik mesajı formatlayır
    }
}
