package com.example.task_management_system.dto.request.task;

import com.example.task_management_system.enums.TaskStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest {
    private TaskStatus status;
}
