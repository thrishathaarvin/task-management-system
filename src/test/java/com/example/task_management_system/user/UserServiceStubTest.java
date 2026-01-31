package com.example.task_management_system.user;

import com.example.task_management_system.dto.response.user.UserResponse;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceStubTest {
    class UserServiceStub{
        UserResponse get(UUID id){
            return UserResponse.builder()
                    .id(id)
                    .name("Stub User")
                    .email("abc@test.com")
                    .active(true)
                    .build();
        }
    }

    @Test
    void shouldReturnStubUser(){
        UserServiceStub service=new UserServiceStub();

        UUID id=UUID.randomUUID();
        UserResponse response=service.get(id);

        assertEquals("Stub User",response.getName());
        assertEquals("abc@test.com",response.getEmail());
    }
}
