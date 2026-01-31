package com.example.task_management_system.Service;

import com.example.task_management_system.dto.request.user.CreateUserRequest;
import com.example.task_management_system.dto.request.user.UpdateUserRequest;
import com.example.task_management_system.dto.request.user.UserFilterRequest;
import com.example.task_management_system.dto.response.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse create(CreateUserRequest request);
    UserResponse get(UUID id);
    List<UserResponse> list(UserFilterRequest filter);
    UserResponse update(UUID id, UpdateUserRequest request);
    void delete(UUID id);
}
