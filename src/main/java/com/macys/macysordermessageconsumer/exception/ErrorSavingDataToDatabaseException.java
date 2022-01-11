package com.macys.macysordermessageconsumer.exception;

import com.macys.macysordermessageconsumer.utils.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class ErrorSavingDataToDatabaseException extends RuntimeException {

    @Override
    public String toString() {
        return ExceptionConstants.ERROR_SAVING_IN_DATABASE;
    }
}
