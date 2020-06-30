package com.testproject.tasktracker.model.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User", description = "User model")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ApiModelProperty(value = "The unique identifier of the user")
    private long id;

    @Column(name = "first_name")
    @NotNull(message = "fistName can`t be null")
    @ApiModelProperty(value = "The first name of the user", required = true)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "lastName can`t be null")
    @ApiModelProperty(value = "The last name of the user", required = true)
    private String lastName;

    @Email
    @Column(unique = true)
    @ApiModelProperty(value = "The email of the user", required = true)
    private String email;

    @Size(min = 8, message = "minimum password length 8 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "The password of the user", required = true)
    private String password;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Task> tasks;

}
