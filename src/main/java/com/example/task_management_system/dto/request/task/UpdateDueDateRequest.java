package com.example.task_management_system.dto.request.task;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdateDueDateRequest {
    private LocalDate dueDate;
}
