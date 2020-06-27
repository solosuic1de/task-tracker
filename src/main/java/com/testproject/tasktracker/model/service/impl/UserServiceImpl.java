package com.testproject.tasktracker.model.service.impl;

import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.repository.UserRepository;
import com.testproject.tasktracker.model.service.UserService;
import com.testproject.tasktracker.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public boolean create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("user with email '{}' is already exist in db", user.getEmail());
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("try to save user - {} in db", user);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        Optional<User> oldUser = findById(user.getId());
        if (oldUser.isPresent()) {
            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            User result = oldUser.get();
            PropertyUtils.copyNotNullFields(user, result);
            log.info("try to update user with id - {}", result.getId());
            userRepository.save(result);
            return true;
        }
        log.warn("cant update, user with id {} does not exist", user.getId());
        return false;
    }

    @Override
    public void deleteById(long id) {
        log.info("try to delete user with id {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("try to get user with email {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(long id) {
        log.info("try to get user with id {}", id);
        return userRepository.findById(id);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        log.info("try to run getAll function");
        return userRepository.findAll(pageable);
    }
}
