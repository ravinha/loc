package com.loc_2.exceptions;

/**
 * Created by Rafal on 2016-05-12.
 */
public class RiotLimitException extends RuntimeException{
    public RiotLimitException() {
    }

    public RiotLimitException(Throwable cause) {
        super(cause);
    }
}
