package com.example.task_management_system.dto.response.task;

import com.example.task_management_system.enums.TaskPriority;
import com.example.task_management_system.enums.TaskStatus;
import com.example.task_management_system.model.User;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private UUID id;
    private int version;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private UUID assignedTo;
    private LocalDate dueDate;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID createdBy;
    private List<String> tags;
}