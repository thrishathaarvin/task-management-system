package com.example.task_management_system.dto.response.report;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagReportResponse {

    private Map<String, Long> tags;
}