package com.example.task_management_system.model;

import com.example.task_management_system.enums.TaskPriority;
import com.example.task_management_system.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskVersion {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID taskId;
    private int version;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private UUID assignedTo;
    private LocalDate dueDate;
    private Instant createdAt;
    private Instant updatedAt;

    private UUID createdBy;

    private List<String> tags;

}
