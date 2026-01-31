package com.example.task_management_system.Service;

import com.example.task_management_system.dto.response.report.*;

import java.util.UUID;

public interface ReportService {
    TaskStatisticsResponse taskStatistics();
    UserActivityReportResponse userActivity(UUID userId);
    TaskCompletionTimelineResponse completionTimeline();
    TagReportResponse tagReport();
    HeatMapResponse heatmap();
}
