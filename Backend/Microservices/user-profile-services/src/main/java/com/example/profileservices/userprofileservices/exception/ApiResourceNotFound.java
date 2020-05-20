package com.example.profileservices.userprofileservices.exception;

public class ApiResourceNotFound extends Exception{
    public ApiResourceNotFound(String message) {
        super(message);
    }

    public ApiResourceNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
