package com.example.task_management_system.dto.request.user;

import com.example.task_management_system.enums.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String name;
    private UserRole role;
}
