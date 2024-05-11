package org.example.service;

public interface UserService {
    void registerUser(String username, String email, String password, String confirmPassword);

    boolean loginedUser(String email, String password);
}
