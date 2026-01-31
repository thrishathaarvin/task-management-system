package com.example.task_management_system.Repository;


import com.example.task_management_system.enums.TaskPriority;
import com.example.task_management_system.enums.TaskStatus;
import com.example.task_management_system.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("""
    SELECT t FROM Task t
    WHERE (:statuses IS NULL OR t.status IN :statuses)
      AND (:priorities IS NULL OR t.priority IN :priorities)
      AND (:assigneeId IS NULL OR t.assignedTo.id = :assigneeId)
      AND (:creatorId IS NULL OR t.createdBy.id = :creatorId)
      AND (:overdue IS NULL OR (:overdue = true AND t.dueDate < CURRENT_DATE))
    """)
    Page<Task> search(
            @Param("statuses") List<TaskStatus> statuses,
            @Param("priorities") List<TaskPriority> priorities,
            @Param("assigneeId") UUID assigneeId,
            @Param("creatorId") UUID creatorId,
            @Param("overdue") Boolean overdue,
            Pageable pageable
    );
}