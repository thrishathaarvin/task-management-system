package com.example.task_management_system.dto.request.task;


import com.example.task_management_system.enums.TaskPriority;
import com.example.task_management_system.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchRequest {

    private List<TaskStatus> statuses;
    private List<TaskPriority> priorities;

    private UUID assigneeId;
    private UUID creatorId;

    private Boolean overdue;

    private LocalDate createdFrom;
    private LocalDate createdTo;

    private LocalDate updatedFrom;
    private LocalDate updatedTo;

    private LocalDate completedFrom;
    private LocalDate completedTo;

    private List<String> tags;

    private String sortBy;
    private String sortDir;
}