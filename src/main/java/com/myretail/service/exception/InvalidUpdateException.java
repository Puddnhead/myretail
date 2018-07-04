package com.myretail.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Error representing a failed update
 *
 * Created by MVW on 7/4/2018.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUpdateException extends RuntimeException {

    public InvalidUpdateException(String message) {
        super(message);
    }
}
