package com.dtw.rms.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Servererror. Please contact admin")
public class InternalServerException extends RuntimeException {
        public InternalServerException(String exception) {
            super(exception);
        }

}
