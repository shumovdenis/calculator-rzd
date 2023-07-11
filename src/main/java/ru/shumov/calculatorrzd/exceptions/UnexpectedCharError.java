package ru.shumov.calculatorrzd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnexpectedCharError extends RuntimeException {
    public UnexpectedCharError(String message) {
        super(message);
    }
}
