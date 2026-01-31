package com.example.task_management_system.dto.request.user;

import com.example.task_management_system.enums.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
    private UserRole role;


}
