package com.example.task_management_system.Controller;

import com.example.task_management_system.Service.TaskSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.task_management_system.dto.request.task.TaskSearchRequest;
import com.example.task_management_system.dto.response.task.TaskResponse;
import com.example.task_management_system.enums.TaskPriority;
import com.example.task_management_system.enums.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks/search")
@RequiredArgsConstructor
public class TaskSearchController {

    private final TaskSearchService taskSearchService;

    @GetMapping
    public List<TaskResponse> search(TaskSearchRequest request) {
        return taskSearchService.search(request);
    }
}
