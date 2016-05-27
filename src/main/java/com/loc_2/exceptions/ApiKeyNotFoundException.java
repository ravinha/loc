package com.loc_2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Rafal on 2016-05-12.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ApiKeyNotFoundException extends RuntimeException{
    public ApiKeyNotFoundException() {
    }

    public ApiKeyNotFoundException(Throwable cause) {
        super(cause);
    }
}
