package com.testproject.tasktracker.model.service.impl;

import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.domain.exception.UserExistException;
import com.testproject.tasktracker.model.domain.exception.UserNotFoundException;
import com.testproject.tasktracker.model.repository.UserRepository;
import com.testproject.tasktracker.model.service.UserService;
import com.testproject.tasktracker.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("user with email '{}' is already exist in db", user.getEmail());
            throw new UserExistException(user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("try to save user - {} in db", user);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User result = findById(user.getId());
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        PropertyUtils.copyNotNullFields(user, result);
        log.info("try to update user with id - {}", result.getId());
        return userRepository.save(result);
    }

    @Override
    public void deleteById(long id) {
        log.info("try to delete user with id {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        log.info("try to get user with email {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User findById(long id) {
        log.info("try to get user with id {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        log.info("in UserService.getAll function");
        return userRepository.findAll(pageable);
    }
}
