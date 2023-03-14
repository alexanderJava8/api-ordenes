package com.codmain.orderapi.config;

import com.codmain.orderapi.exceptions.GeneralServicesExceptions;
import com.codmain.orderapi.exceptions.NoDataFoundException;
import com.codmain.orderapi.exceptions.ValideteServiceException;
import com.codmain.orderapi.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        WrapperResponse<?> wrapperResponse = new WrapperResponse<>(false, "INTERNAL SERVER ERROR", null);
        return new ResponseEntity<>(wrapperResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ValideteServiceException.class)
    public ResponseEntity<?> valideteServiceException(ValideteServiceException e, WebRequest request) {
        WrapperResponse<?> wrapperResponse = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(wrapperResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request) {
        WrapperResponse<?> wrapperResponse = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(wrapperResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralServicesExceptions.class)
    public ResponseEntity<?> generalServicesException(GeneralServicesExceptions e, WebRequest request) {
        WrapperResponse<?> wrapperResponse = new WrapperResponse<>(false, "INTERNAL SERVER ERROR", null);
        return new ResponseEntity<>(wrapperResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
