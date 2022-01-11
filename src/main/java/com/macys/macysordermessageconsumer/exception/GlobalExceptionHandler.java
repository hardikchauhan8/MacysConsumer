package com.macys.macysordermessageconsumer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            ErrorSavingDataToDatabaseException.class,
            ErrorParsingDataException.class

    })

    public ResponseEntity<Object> errorSavingDataToDatabaseException(RuntimeException exception, WebRequest request) {

        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    public ResponseEntity<Object> errorParsingDataException(RuntimeException exception, WebRequest request) {

        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
