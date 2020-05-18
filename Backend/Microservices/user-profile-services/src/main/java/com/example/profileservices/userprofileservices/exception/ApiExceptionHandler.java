package com.example.profileservices.userprofileservices.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        //1.Create Payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException= new ApiException(e.getMessage(),
                badRequest,
                                        ZonedDateTime.now(ZoneId.of("Z")));
        //2.Return ResponseEntity
        return new ResponseEntity<>(apiException, badRequest);

    }

    @ExceptionHandler(value = {ApiResourceNotFound.class})
    public ResponseEntity<Object> handleApiResourceNotFoundException(ApiResourceNotFound e){
        //1.Create Payload containing exception details
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        ApiException apiException= new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        //2.Return ResponseEntity
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleApiResourceNotFoundException(RuntimeException e){
        //1.Create Payload containing exception details
        HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException= new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        //2.Return ResponseEntity
        return new ResponseEntity<>(apiException, badRequest);
    }
}
