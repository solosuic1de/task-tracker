package com.testproject.tasktracker.model.domain.dto;

import com.testproject.tasktracker.model.domain.enums.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.StatementCreatorUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UpdateTaskStatusRequests", description = "update task status")
public class UpdateTaskStatusRequests {
    @ApiModelProperty(value = "Task id")
    private long taskId;
    @ApiModelProperty(value = "Task status")
    private Status status;
}
