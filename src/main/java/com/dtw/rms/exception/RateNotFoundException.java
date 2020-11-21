package com.dtw.rms.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Rate ID not found in RMS")
public class RateNotFoundException extends RuntimeException {
        public RateNotFoundException(String message) {
            super(message);
        }

}
