package com.example.task_management_system.validation;

import com.example.task_management_system.enums.TaskStatus;
import com.example.task_management_system.exception.InvalidTaskStateTransitionException;

import java.util.Map;
import java.util.Set;

public class TaskStateMachine {

    private static final Map<TaskStatus, Set<TaskStatus>> TRANSITIONS = Map.of(
            TaskStatus.OPEN, Set.of(TaskStatus.IN_PROGRESS, TaskStatus.CANCELLED),
            TaskStatus.IN_PROGRESS, Set.of(TaskStatus.COMPLETED, TaskStatus.CANCELLED),
            TaskStatus.CANCELLED, Set.of(TaskStatus.OPEN),
            TaskStatus.COMPLETED, Set.of()
    );

    public static void validate(TaskStatus from, TaskStatus to) {
        if (from == to) return;

        Set<TaskStatus> allowed = TRANSITIONS.get(from);

        if (allowed == null || !allowed.contains(to)) {
            throw new InvalidTaskStateTransitionException(from.name(), to.name());
        }
    }
}
