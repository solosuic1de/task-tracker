package com.testproject.tasktracker.model.domain.dto;

import com.testproject.tasktracker.model.domain.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    private long id;
    private String title;
    private String description;

}
