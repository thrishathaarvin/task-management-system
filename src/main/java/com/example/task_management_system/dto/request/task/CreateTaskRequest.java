package com.example.task_management_system.dto.request.task;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
    private String title;
    private String description;
    private UUID createdBy;
    private UUID assignedTo;
    private LocalDate dueDate;
    private List<String> tags;
}
