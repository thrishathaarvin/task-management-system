package com.example.task_management_system.Converter;

import com.example.task_management_system.dto.response.task.TaskResponse;
import com.example.task_management_system.model.Task;
import com.example.task_management_system.model.TaskVersion;

public class TaskConverter {
    public static TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .version(task.getVersion())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .assignedTo(task.getAssignedTo() != null ? task.getAssignedTo().getId() : null)
                .dueDate(task.getDueDate())
                .build();
    }

    //task version to task response
    public static TaskResponse fromVersion(TaskVersion v) {
        return TaskResponse.builder()
                .id(v.getTaskId())
                .version(v.getVersion())
                .title(v.getTitle())
                .description(v.getDescription())
                .status(v.getStatus())
                .priority(v.getPriority())
                .createdBy(v.getCreatedBy())
                .assignedTo(v.getAssignedTo())
                .dueDate(v.getDueDate())
                .tags(v.getTags())
                .createdAt(v.getCreatedAt())
                .updatedAt(v.getCreatedAt())
                .build();
    }

    //task to task version
    public static TaskVersion toVersion(Task task) {
        return TaskVersion.builder()
                .taskId(task.getId())
                .version(task.getVersion())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .createdBy(task.getCreatedBy().getId())
                .assignedTo(task.getAssignedTo() != null ? task.getAssignedTo().getId() : null)
                .dueDate(task.getDueDate())
                .tags(task.getTags())
                .createdAt(task.getUpdatedAt())
                .build();
    }



}