package com.testproject.tasktracker.model.service;

import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.domain.exception.UserExistException;
import com.testproject.tasktracker.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static com.testproject.tasktracker.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    private User user;

    @BeforeEach
    public void initUser() {
        user = User.builder().firstName(TEST_NAME)
                .id(TEST_ID)
                .lastName(TEST_LAST_NAME)
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .build();
    }

    @Test
    public void createUserReturnTrueWhenUserAddedToDataBase() {
        Mockito.doReturn(User.builder()
                .email(TEST_EMAIL)
                .lastName(TEST_LAST_NAME)
                .build())
                .when(userRepository)
                .save(ArgumentMatchers.any(User.class));
        User createdUser = userService.create(user);
        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(TEST_EMAIL);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(TEST_PASSWORD);
        assertEquals(TEST_EMAIL, createdUser.getEmail());
        assertEquals(TEST_LAST_NAME, createdUser.getLastName());
    }

    @Test
    public void createUserThrowExceptionWhenUserEmailAlreadyInDatabase() {
        Mockito.doReturn(true).when(userRepository).existsByEmail(TEST_EMAIL);
        assertThrows(UserExistException.class, () -> {
            userService.create(user);
        });

        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(TEST_EMAIL);

    }

    @Test
    public void updateUserReturnTrueAndCallEncodeWhenUpdatePassword() {
        User newUser = User.builder()
                .password(TEST_PASSWORD)
                .id(TEST_ID)
                .firstName(TEST_NAME_UPDATE)
                .build();
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(TEST_ID);
        Mockito.doReturn(User.builder()
                .email(TEST_EMAIL)
                .firstName(TEST_NAME_UPDATE)
                .build())
                .when(userRepository)
                .save(ArgumentMatchers.any(User.class));
        User user = userService.update(newUser);
        Mockito.verify(userRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(User.class));
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(TEST_PASSWORD);

        assertEquals(TEST_EMAIL, user.getEmail());
        assertEquals(TEST_NAME_UPDATE, user.getFirstName());
    }

}