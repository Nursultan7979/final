package org.example.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void registerUser(String username, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Пароль и подтверждение пароля не совпадают");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        String sql = "INSERT INTO users (username, email, hash_password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, username, email, hashedPassword);
    }

    public boolean loginedUser(String email, String password) {
        String sql = "SELECT hash_password FROM users WHERE email = ?";
        String hashedPassword = jdbcTemplate.queryForObject(sql, String.class, email);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, hashedPassword);
    }

}
