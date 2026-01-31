package com.example.task_management_system.Repository;

import com.example.task_management_system.model.ActivityEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActivityEventRepository extends JpaRepository<ActivityEvent, UUID> {
}
