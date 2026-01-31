package com.example.task_management_system.Service.impl;

import com.example.task_management_system.Converter.TaskConverter;
import com.example.task_management_system.Repository.CommentRepository;
import com.example.task_management_system.Repository.TaskRepository;
import com.example.task_management_system.Repository.TaskVersionRepository;
import com.example.task_management_system.Repository.UserRepository;
import com.example.task_management_system.Service.TaskService;
import com.example.task_management_system.dto.request.task.*;
import com.example.task_management_system.dto.response.task.TaskResponse;
import com.example.task_management_system.enums.ActivityType;
import com.example.task_management_system.enums.TaskPriority;
import com.example.task_management_system.enums.TaskStatus;
import com.example.task_management_system.exception.ResourceNotFoundException;
import com.example.task_management_system.model.ActivityEvent;
import com.example.task_management_system.Repository.ActivityEventRepository;
import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.Task;
import com.example.task_management_system.model.User;
import com.example.task_management_system.validation.TaskStateMachine;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskVersionRepository taskVersionRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ActivityEventRepository activityEventRepository;

    @Transactional
    public TaskResponse create(CreateTaskRequest request) {
        User creator = userRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdBy(creator)
                .status(TaskStatus.OPEN)
                .priority(TaskPriority.MEDIUM)
                .version(1)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();


        Task saved = taskRepository.save(task);
        saveVersion(saved);

        logEvent(saved, creator, ActivityType.TASK_CREATED, "Task created");
        return TaskConverter.toResponse(saved);
    }

    public TaskResponse get(UUID id) {
        return TaskConverter.toResponse(getTask(id));
    }

    @Transactional
    public void updateStatus(UUID id, UpdateStatusRequest request) {
        Task task = getTask(id);
        TaskStateMachine.validate(task.getStatus(), request.getStatus());

        TaskStatus old = task.getStatus();
        task.setStatus(request.getStatus());
        update(task);

        logEvent(task, task.getCreatedBy(), ActivityType.STATUS_CHANGED,
                "Status changed from " + old + " to " + task.getStatus());

    }

    @Transactional
    public void updatePriority(UUID id, UpdatePriorityRequest request) {
        Task task = getTask(id);
        TaskPriority old = task.getPriority();
        task.setPriority(request.getPriority());
        update(task);

        logEvent(task, task.getCreatedBy(), ActivityType.PRIORITY_CHANGED,
                "Priority changed from " + old + " to " + task.getPriority());

    }

    @Transactional
    public void updateAssignee(UUID id, UpdateAssigneeRequest request) {
        Task task = getTask(id);

        User user = request.getAssigneeId() == null ? null :
                userRepository.findById(request.getAssigneeId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        User old = task.getAssignedTo();

        task.setAssignedTo(user);
        update(task);

        logEvent(task, task.getCreatedBy(), ActivityType.ASSIGNEE_CHANGED,
                "Assignee changed from " +
                        (old == null ? "none" : old.getId()) +
                        " to " +
                        (user == null ? "none" : user.getId()));
    }


    @Transactional
    public void updateDueDate(UUID id, UpdateDueDateRequest request) {
        Task task = getTask(id);

        var old = task.getDueDate();

        task.setDueDate(request.getDueDate());
        update(task);

        logEvent(task, task.getCreatedBy(), ActivityType.DUE_DATE_CHANGED,
                "Due date changed from " + old + " to " + task.getDueDate());
    }

    public void addComment(UUID id, AddCommentRequest request) {
        Task task = getTask(id);
        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Comment comment = Comment.builder()
                .task(task)
                .author(author)
                .text(request.getText())
                .timestamp(Instant.now())
                .build();

        commentRepository.save(comment);
        logEvent(task, author, ActivityType.COMMENT_ADDED, "Comment added");
    }

    public List<TaskResponse> history(UUID id) {
        return taskVersionRepository.findByTaskIdOrderByVersionDesc(id)
                .stream()
                .map(TaskConverter::fromVersion)
                .toList();
    }

    private Task getTask(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    private void update(Task task) {
        task.setVersion(task.getVersion() + 1);
        task.setUpdatedAt(Instant.now());
        taskRepository.save(task);
        saveVersion(task);
    }

    private void saveVersion(Task task) {
        taskVersionRepository.save(TaskConverter.toVersion(task));
    }

    private void logEvent(Task task, User user, ActivityType type, String details) {
        ActivityEvent event = ActivityEvent.builder()
                .taskId(task.getId())
                .activityType(type)
                .performedBy(user)
                .timestamp(Instant.now())
                .details(details)
                .build();

        activityEventRepository.save(event);
    }


}
