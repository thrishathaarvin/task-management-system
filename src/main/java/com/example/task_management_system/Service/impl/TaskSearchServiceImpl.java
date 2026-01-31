package com.example.task_management_system.Service.impl;

import com.example.task_management_system.Converter.TaskConverter;
import com.example.task_management_system.Repository.TaskRepository;
import com.example.task_management_system.Service.TaskSearchService;
import com.example.task_management_system.dto.request.task.TaskSearchRequest;
import com.example.task_management_system.dto.response.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskSearchServiceImpl implements TaskSearchService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskResponse> search(TaskSearchRequest request) {

        Sort sort = Sort.unsorted();
        if (request.getSortBy() != null) {
            sort = Sort.by(
                    "desc".equalsIgnoreCase(request.getSortDir())
                            ? Sort.Direction.DESC
                            : Sort.Direction.ASC,
                    request.getSortBy()
            );
        }

        Pageable pageable = PageRequest.of(0, 1000, sort);

        return taskRepository.search(
                request.getStatuses(),
                request.getPriorities(),
                request.getAssigneeId(),
                request.getCreatorId(),
                request.getOverdue(),
                pageable
        ).stream().map(TaskConverter::toResponse).toList();
    }
}