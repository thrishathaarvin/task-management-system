package com.example.task_management_system.dto.response.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCompletionTimelineResponse {

    private Map<LocalDate, Long> completedTasksPerDay;
}