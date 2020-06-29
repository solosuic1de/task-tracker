package com.testproject.tasktracker.model.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AssignToUserRequest", description = "login parameters")
public class AuthRequest {
    @ApiModelProperty(value = "Users Email")
    private String email;
    @ApiModelProperty(value = "Users password")
    private String password;
}
