package com.testproject.tasktracker.model.domain.dto;

import com.testproject.tasktracker.model.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.StatementCreatorUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskStatusRequests {
    private long taskId;
    private Status status;
}
