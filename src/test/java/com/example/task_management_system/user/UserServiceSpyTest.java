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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceSpyTest {

    @Mock
    private UserRepository repository;

    @Spy
    @InjectMocks
    private UserServiceImpl service;

    @Test
    void shouldUseRealMethod_exceptStubbedOne(){
        UUID id=UUID.randomUUID();
        User user=User.builder().id(id).name("Spy User").build();

        doReturn(Optional.of(user))
                .when(repository).findById(id);

        UserResponse result=service.get(id);

        assertEquals("Spy User", result.getName());

        verify(service).get(id);
        verify(repository).findById(id);
    }
}
