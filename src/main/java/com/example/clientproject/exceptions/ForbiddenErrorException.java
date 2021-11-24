package com.example.clientproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenErrorException extends RuntimeException{
    public ForbiddenErrorException(String s) {
        super(s);
    }
}
