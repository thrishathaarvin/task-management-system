package com.example.task_management_system.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
