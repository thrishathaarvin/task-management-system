package com.example.task_management_system.Repository;

import com.example.task_management_system.model.TaskVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskVersionRepository extends JpaRepository<TaskVersion, UUID> {
    List<TaskVersion> findByTaskIdOrderByVersionDesc(UUID taskId);
}
