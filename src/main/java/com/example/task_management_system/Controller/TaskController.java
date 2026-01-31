package com.example.task_management_system.Controller;

import com.example.task_management_system.Service.TaskService;
import com.example.task_management_system.dto.request.task.*;
import com.example.task_management_system.dto.response.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public TaskResponse create(@RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }

    @GetMapping("/{id}")
    public TaskResponse get(@PathVariable UUID id) {
        return taskService.get(id);
    }

    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable UUID id, @RequestBody UpdateStatusRequest request) {
        taskService.updateStatus(id, request);
    }

    @PatchMapping("/{id}/priority")
    public void updatePriority(@PathVariable UUID id, @RequestBody UpdatePriorityRequest request) {
        taskService.updatePriority(id, request);
    }

    @PatchMapping("/{id}/assignee")
    public void updateAssignee(@PathVariable UUID id, @RequestBody UpdateAssigneeRequest request) {
        taskService.updateAssignee(id, request);
    }

    @PatchMapping("/{id}/due-date")
    public void updateDueDate(@PathVariable UUID id, @RequestBody UpdateDueDateRequest request) {
        taskService.updateDueDate(id, request);
    }

    @PostMapping("/{id}/comments")
    public void addComment(@PathVariable UUID id, @RequestBody AddCommentRequest request) {
        taskService.addComment(id, request);
    }

    @GetMapping("/{id}/history")
    public List<TaskResponse> history(@PathVariable UUID id) {
        return taskService.history(id);
    }
}