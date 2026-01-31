package com.example.task_management_system.model;

import com.example.task_management_system.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="users")
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(unique=true, nullable = false, updatable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
}
