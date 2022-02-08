package com.example.survey.exception.handler;

import com.example.survey.exception.DataNotFoundException;
import com.example.survey.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> exception(DataNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), "");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
