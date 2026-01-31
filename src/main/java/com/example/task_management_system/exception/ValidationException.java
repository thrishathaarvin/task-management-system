package com.example.task_management_system.exception;

public class ValidationException extends ApiException{
    public ValidationException(String message){
        super(message);
    }
}
