package com.example.task_management_system.model;

import com.example.task_management_system.enums.ActivityType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ActivityEvent {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID taskId;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @ManyToOne
    private User performedBy;

    private Instant timestamp;
    private String details;
}
