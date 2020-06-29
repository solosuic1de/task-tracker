package com.testproject.tasktracker.model.repository;

import com.testproject.tasktracker.model.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
