package com.example.task_management_system.Service.impl;

import com.example.task_management_system.Converter.UserConverter;
import com.example.task_management_system.Repository.UserRepository;
import com.example.task_management_system.Service.UserService;
import com.example.task_management_system.dto.request.user.CreateUserRequest;
import com.example.task_management_system.dto.request.user.UpdateUserRequest;
import com.example.task_management_system.dto.request.user.UserFilterRequest;
import com.example.task_management_system.dto.response.user.UserResponse;
import com.example.task_management_system.exception.ResourceNotFoundException;
import com.example.task_management_system.exception.ValidationException;
import com.example.task_management_system.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserResponse create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new ValidationException("Email already exists");

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(request.getRole())
                .active(true)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return UserConverter.toResponse(userRepository.save(user));
    }

    public UserResponse get(UUID id) {
        return UserConverter.toResponse(
                userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"))
        );
    }

    public List<UserResponse> list(UserFilterRequest filter) {
        return userRepository.findAll().stream()
                .filter(u -> filter.getRole() == null || u.getRole() == filter.getRole())
                .filter(u -> filter.getActiveOnly() == null || u.isActive() == filter.getActiveOnly())
                .map(UserConverter::toResponse)
                .toList();
    }

    public UserResponse update(UUID id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setName(request.getName());
        user.setRole(request.getRole());
        user.setUpdatedAt(Instant.now());
        return UserConverter.toResponse(userRepository.save(user));
    }

    public void delete(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }
}

