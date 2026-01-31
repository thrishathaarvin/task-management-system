package com.example.task_management_system.exception;

public class InvalidTaskStateTransitionException extends ApiException{
    public InvalidTaskStateTransitionException(String from, String to) {
        super("Invalid task state transition: " + from + " -> " + to);
    }
}
