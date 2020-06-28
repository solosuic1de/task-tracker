package com.testproject.tasktracker.model.security.jwt;

import com.testproject.tasktracker.model.domain.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                true);
    }
}
