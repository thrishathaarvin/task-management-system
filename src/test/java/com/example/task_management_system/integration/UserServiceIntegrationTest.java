package com.example.task_management_system.integration;

import com.example.task_management_system.Service.UserService;
import com.example.task_management_system.dto.request.user.CreateUserRequest;
import com.example.task_management_system.dto.request.user.UpdateUserRequest;
import com.example.task_management_system.dto.request.user.UserFilterRequest;
import com.example.task_management_system.dto.response.user.UserResponse;
import com.example.task_management_system.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    void shouldPerformFullCRUDOperations() {
        CreateUserRequest createRequest = CreateUserRequest.builder()
                .name("Alice")
                .email("alice@example.com")
                .role(UserRole.valueOf("ADMIN"))
                .build();

        UserResponse created = userService.create(createRequest);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getName()).isEqualTo("Alice");

        UserResponse fetched = userService.get(created.getId());
        assertThat(fetched.getEmail()).isEqualTo("alice@example.com");

        UpdateUserRequest updateRequest = UpdateUserRequest.builder()
                .name("Alice Updated")
                .role(UserRole.valueOf("DEVELOPER"))
                .build();

        UserResponse updated = userService.update(created.getId(), updateRequest);
        assertThat(updated.getName()).isEqualTo("Alice Updated");
        assertThat(updated.getRole()).isEqualTo(UserRole.DEVELOPER);

        UserFilterRequest filter = UserFilterRequest.builder()
                .role(UserRole.valueOf("DEVELOPER"))
                .activeOnly(true)
                .build();

        List<UserResponse> filtered = userService.list(filter);
        assertThat(filtered).isNotEmpty();
        assertThat(filtered.get(0).getName()).isEqualTo("Alice Updated");

        userService.delete(created.getId());

        UserResponse afterDelete = userService.get(created.getId());
        assertThat(afterDelete.isActive()).isFalse();
    }
}
