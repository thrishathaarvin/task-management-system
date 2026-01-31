package com.example.task_management_system.exception;

public class ResourceNotFoundException  extends ApiException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
