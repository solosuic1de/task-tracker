package com.testproject.tasktracker.model.repository;

import com.testproject.tasktracker.model.domain.entity.Task;
import com.testproject.tasktracker.model.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByStatus(Status status, Pageable pageable);

    @Query(value = "SELECT * FROM tasks ORDER BY user_id DESC", nativeQuery = true)
    List<Task> getAllByUserDesc();

    @Query(value = "SELECT * FROM tasks ORDER BY user_id ASC", nativeQuery = true)
    List<Task> getAllByUserAsc();
}
