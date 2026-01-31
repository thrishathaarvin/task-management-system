package com.example.task_management_system.Controller;

import com.example.task_management_system.Service.UserService;
import com.example.task_management_system.dto.request.user.CreateUserRequest;
import com.example.task_management_system.dto.request.user.UpdateUserRequest;
import com.example.task_management_system.dto.request.user.UserFilterRequest;
import com.example.task_management_system.dto.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable UUID id) {
        return userService.get(id);
    }

    @GetMapping
    public List<UserResponse> list(UserFilterRequest filter) {
        return userService.list(filter);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable UUID id, @RequestBody UpdateUserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}