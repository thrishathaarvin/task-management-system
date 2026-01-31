package com.example.task_management_system.dto.request.task;

import com.example.task_management_system.enums.TaskPriority;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePriorityRequest {
    private TaskPriority priority;
}
