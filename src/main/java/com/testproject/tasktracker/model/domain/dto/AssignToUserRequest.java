package com.testproject.tasktracker.model.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignToUserRequest {
    private String email;
    private long taskId;
}
