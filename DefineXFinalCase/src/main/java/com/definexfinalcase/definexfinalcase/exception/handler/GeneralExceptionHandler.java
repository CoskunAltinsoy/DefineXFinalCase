package com.definexfinalcase.definexfinalcase.exception.handler;

import com.definexfinalcase.definexfinalcase.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handle(BaseException baseException){
        return new ResponseEntity<>(baseException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
