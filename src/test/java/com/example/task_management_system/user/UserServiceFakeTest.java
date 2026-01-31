package com.example.task_management_system.user;

import com.example.task_management_system.dto.response.user.UserResponse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceFakeTest {
    class FakeUserService{
        private final Map<UUID, UserResponse> store=new HashMap<>();

        UserResponse create(UserResponse user){
            store.put(user.getId(), user);
            return user;
        }

        UserResponse get(UUID id){
            return store.get(id);
        }
    }

    @Test
    void shouldStoreAndRetrieveUser(){
        FakeUserService service=new FakeUserService();

        UUID id=UUID.randomUUID();
        UserResponse user=UserResponse.builder()
                .id(id)
                .name("Fake User")
                .email("fake@test.com")
                .active(true)
                .build();
        service.create(user);

        UserResponse fetch=service.get(id);
        assertEquals("Fake User", fetch.getName());
    }
}
