package com.stackroute.exceptions;

import com.stackroute.response.ResponseForError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class TrackGlobalExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {TrackAlreadyExistsException.class})
    public ResponseEntity<object> exception(TrackNotFoundException ex){{
        return new ResponseEntity<>("track not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TrackAlreadyExistexception.class})
    public ResponseEntity<object> exception(TrackAlreadyException ex){
        return new ResponseEntity<>("track already exists", HttpStatus.NOT_FOUND);
    }
}
