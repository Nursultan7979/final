package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    void registerUser(String username, String email, String password, String confirmPassword);
    boolean loginedUser(String email, String password);
    void logout(HttpServletRequest request, HttpServletResponse response);
}
