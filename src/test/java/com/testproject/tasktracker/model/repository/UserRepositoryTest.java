package com.testproject.tasktracker.model.repository;


import com.testproject.tasktracker.model.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.testproject.tasktracker.constants.TestConstants.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setUp() {
        User user = User.builder()
                .email(TEST_EMAIL)
                .firstName(TEST_NAME)
                .lastName(TEST_LAST_NAME)
                .password(TEST_PASSWORD)
                .build();
        userRepository.save(user);
    }

    @Test
    public void whenFindByEmailThenReturnUser() {
        User user = userRepository.findByEmail(TEST_EMAIL).get();
        assertEquals(user.getEmail(), TEST_EMAIL);
        assertEquals(user.getFirstName(), TEST_NAME);
        assertEquals(user.getLastName(), TEST_LAST_NAME);
    }

    @Test
    public void whenFindByIdThenReturnUser() {
        long id = userRepository.findByEmail(TEST_EMAIL).get().getId();
        User user = userRepository.findById(id).get();
        assertEquals(user.getEmail(), TEST_EMAIL);
        assertEquals(user.getFirstName(), TEST_NAME);
        assertEquals(user.getLastName(), TEST_LAST_NAME);
    }

    @Test
    public void whenDeleteThenReturnEmptyOptional() {
        userRepository.deleteById(TEST_ID);
        assertEquals(Optional.empty(), userRepository.findByEmail(TEST_EMAIL));
    }
}