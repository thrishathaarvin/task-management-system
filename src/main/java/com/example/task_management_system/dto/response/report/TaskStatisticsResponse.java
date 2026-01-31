package com.example.task_management_system.dto.response.report;

import com.example.task_management_system.enums.TaskPriority;
import com.example.task_management_system.enums.TaskStatus;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatisticsResponse {

    private Map<TaskStatus, Long> tasksByStatus;
    private Map<TaskPriority, Long> tasksByPriority;

    private long overdueCount;
    private double overduePercentage;

    private double averageCompletionTimeInDays;

    private Map<String, Long> workloadDistribution;

    private long unassignedTasks;
}