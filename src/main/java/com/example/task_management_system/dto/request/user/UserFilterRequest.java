package com.example.task_management_system.dto.request.user;

import com.example.task_management_system.enums.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterRequest {

    private UserRole role;
    private Boolean activeOnly;
    private String sortBy;
    private String sortDir;
}
