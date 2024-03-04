package com.blog.Exception;

import com.blog.Payload.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice // any Exception that occurs in (Controller layer) that it advise to Handle here
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Below method acts like (catch block) whatever exception occurred it handles....
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> HandleException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ){
        ErrorDetail errorDetail = new ErrorDetail(new Date(),exception.getMessage(),webRequest.getDescription(true));

        return new ResponseEntity<> (errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Handles  any Exception occurred in the Project
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> HandleException(
            Exception exception,
            WebRequest webRequest
    ){
        ErrorDetail errorDetail = new ErrorDetail(new Date(),exception.getMessage(),webRequest.getDescription(true));

        return new ResponseEntity<> (errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
