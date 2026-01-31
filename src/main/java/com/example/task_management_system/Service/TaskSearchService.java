package com.example.task_management_system.Service;

import com.example.task_management_system.dto.request.task.TaskSearchRequest;
import com.example.task_management_system.dto.response.task.TaskResponse;

import java.util.List;

public interface TaskSearchService {
    List<TaskResponse> search(TaskSearchRequest request);
}
