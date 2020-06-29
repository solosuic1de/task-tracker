package com.testproject.tasktracker.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testproject.tasktracker.model.domain.enums.Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Task", description = "A user task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "The unique identifier of the task")
    private long id;

    @NotNull(message = "title can`t be null")
    @ApiModelProperty(value = "Description of the task", required = true)
    private String title;

    @ApiModelProperty(value = "Description of the task")
    private String description;

    @ApiModelProperty(value = "Status of the task", required = true)
    @NotNull(message = "status can`t be null")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
