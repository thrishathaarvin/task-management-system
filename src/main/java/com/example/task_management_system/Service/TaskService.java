package com.example.task_management_system.Service;

import com.example.task_management_system.dto.request.task.*;
import com.example.task_management_system.dto.response.task.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse create(CreateTaskRequest request);
    TaskResponse get(UUID id);
    void updateStatus(UUID id, UpdateStatusRequest request);
    void updatePriority(UUID id, UpdatePriorityRequest request);
    void updateAssignee(UUID id, UpdateAssigneeRequest request);
    void updateDueDate(UUID id, UpdateDueDateRequest request);
    void addComment(UUID id, AddCommentRequest request);
    List<TaskResponse> history(UUID id);
}
