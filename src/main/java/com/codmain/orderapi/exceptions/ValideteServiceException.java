package com.codmain.orderapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValideteServiceException extends RuntimeException {
    public ValideteServiceException() {
    }

    public ValideteServiceException(String message) {
        super(message);
    }

    public ValideteServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValideteServiceException(Throwable cause) {
        super(cause);
    }

    public ValideteServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
