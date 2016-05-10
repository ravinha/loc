package com.loc_2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Rafal on 2016-05-10.
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class AccountExistsException extends RuntimeException{
    public AccountExistsException() {
    }

    public AccountExistsException(Throwable cause) {
        super(cause);
    }
}
