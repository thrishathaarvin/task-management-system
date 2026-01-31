package com.example.task_management_system.Controller;

import com.example.task_management_system.Service.ReportService;
import com.example.task_management_system.dto.response.report.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/tasks/statistics")
    public TaskStatisticsResponse taskStatistics() {
        return reportService.taskStatistics();
    }

    @GetMapping("/users/{id}/activity")
    public UserActivityReportResponse userActivity(@PathVariable UUID id) {
        return reportService.userActivity(id);
    }

    @GetMapping("/tasks/completion-timeline")
    public TaskCompletionTimelineResponse completionTimeline() {
        return reportService.completionTimeline();
    }

    @GetMapping("/tags")
    public TagReportResponse tagReport() {
        return reportService.tagReport();
    }

    @GetMapping("/activity-heatmap")
    public HeatMapResponse activityHeatmap() {
        return reportService.heatmap();
    }
}