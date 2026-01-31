package com.example.task_management_system.user;

import com.example.task_management_system.Repository.UserRepository;
import com.example.task_management_system.Service.UserService;
import com.example.task_management_system.Service.impl.UserServiceImpl;
import com.example.task_management_system.dto.response.user.UserResponse;
import com.example.task_management_system.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceMockTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void shouldReturnUser_whenUserExists(){
        UUID id=UUID.randomUUID();
        User user=User.builder().id(id).name("Mock User").build();

        when(repository.findById(id))
                .thenReturn(Optional.of(user));

        UserResponse result=service.get(id);
        assertEquals("Mock User",result.getName());
        verify(repository).findById(id);


    }
}
