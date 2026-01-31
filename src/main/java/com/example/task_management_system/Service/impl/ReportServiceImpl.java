package com.example.task_management_system.Service.impl;

import com.example.task_management_system.Repository.ActivityEventRepository;
import com.example.task_management_system.Repository.TaskRepository;
import com.example.task_management_system.Service.ReportService;
import com.example.task_management_system.dto.response.report.*;
import com.example.task_management_system.enums.TaskPriority;
import com.example.task_management_system.enums.TaskStatus;
import com.example.task_management_system.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final TaskRepository taskRepository;
    private final ActivityEventRepository activityEventRepository;

    public TaskStatisticsResponse taskStatistics() {
        List<Task> tasks = taskRepository.findAll();

        Map<TaskStatus, Long> byStatus = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));

        Map<TaskPriority, Long> byPriority = tasks.stream()
                .collect(Collectors.groupingBy(Task::getPriority, Collectors.counting()));

        long unassigned = tasks.stream().filter(t -> t.getAssignedTo() == null).count();

        long overdue = tasks.stream()
                .filter(t -> t.getDueDate() != null && t.getDueDate().isBefore(LocalDate.now()))
                .count();

        double overduePct = tasks.isEmpty() ? 0 : (overdue * 100.0 / tasks.size());

        return TaskStatisticsResponse.builder()
                .tasksByStatus(byStatus)
                .tasksByPriority(byPriority)
                .unassignedTasks(unassigned)
                .overdueCount(overdue)
                .overduePercentage(overduePct)
                .averageCompletionTimeInDays(0)
                .workloadDistribution(Map.of())
                .build();
    }

    public UserActivityReportResponse userActivity(UUID userId) {
        return UserActivityReportResponse.builder().build();
    }

    public TaskCompletionTimelineResponse completionTimeline() {
        return TaskCompletionTimelineResponse.builder().completedTasksPerDay(Map.of()).build();
    }

    public TagReportResponse tagReport() {
        return TagReportResponse.builder().tags(Map.of()).build();
    }

    public HeatMapResponse heatmap() {
        return HeatMapResponse.builder().activity(Map.of()).build();
    }
}

