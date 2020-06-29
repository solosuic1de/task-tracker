package com.testproject.tasktracker.model.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AssignToUserRequest")
public class AssignToUserRequest {
    @ApiModelProperty(value = "email of the user to whom you want to assign the task")
    private String email;
    @ApiModelProperty(value = "Identifier of task")
    private long taskId;
}
