package com.testproject.tasktracker.model.domain.dto;

import com.testproject.tasktracker.model.domain.entity.Task;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AssignToUserRequest", description = "login parameters")
public class UpdateTaskRequest {
    @ApiModelProperty(value = "Task id")
    private long id;
    @ApiModelProperty(value = "New task title")
    private String title;
    @ApiModelProperty(value = "New task description")
    private String description;

}
