package com.example.task_management_system.dto.response.report;


import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityReportResponse {

    private UUID userId;

    private long tasksCreated;
    private long tasksAssigned;
    private long tasksCompleted;

    private double completionRate;

    private Map<String, Long> activityBreakdown;

    private Object recentActivityTimeline;
}
