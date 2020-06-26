package com.testproject.tasktracker.model.domain.entity;

import com.testproject.tasktracker.model.domain.enums.Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String title;
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
