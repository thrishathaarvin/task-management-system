package com.example.task_management_system.Converter;

import com.example.task_management_system.dto.response.user.UserResponse;
import com.example.task_management_system.model.User;

public class UserConverter {
    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }
}