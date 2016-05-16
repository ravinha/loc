package com.loc_2.exceptions;

/**
 * Created by Rafal on 2016-05-12.
 */
public class ApiKeyNotFoundException extends RuntimeException{
    public ApiKeyNotFoundException() {
    }

    public ApiKeyNotFoundException(Throwable cause) {
        super(cause);
    }
}
