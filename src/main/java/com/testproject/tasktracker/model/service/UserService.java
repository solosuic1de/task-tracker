package com.testproject.tasktracker.model.service;

import com.testproject.tasktracker.model.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public interface UserService {
    boolean create(User user);

    boolean update(User user);

    void deleteById(long id);

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);

    Page<User> getAll(Pageable pageable);

}
