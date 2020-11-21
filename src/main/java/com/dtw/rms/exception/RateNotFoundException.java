package com.dtw.rms.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RateNotFoundException extends RuntimeException {
        public RateNotFoundException(String exception, String message) {
            super(exception);
        }

}
