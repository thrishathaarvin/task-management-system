package com.example.task_management_system.dto.request.task;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAssigneeRequest {
    private UUID assigneeId;
}
