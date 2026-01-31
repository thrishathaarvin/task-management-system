package com.example.task_management_system.dto.response.user;

import com.example.task_management_system.enums.UserRole;
import com.example.task_management_system.model.User;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private UserRole role;
    private boolean active;
}
