package com.example.task_management_system.dto.request.task;

import com.example.task_management_system.model.User;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequest {
    private String text;
    private UUID authorId;
}
