package com.avoris.challenge.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.avoris.challenge.exception.InvalidParametersException;

@ControllerAdvice
public class AppExceptionHandler 
  extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = { InvalidParametersException.class })
    protected ResponseEntity<Object> handleInvalidIdException(RuntimeException ex, WebRequest request) {
    	
    	String errorMessage = ex.getMessage();
        
        return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}