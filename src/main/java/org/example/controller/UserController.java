package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.Entity.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        userService.registerUser(username, email, password, confirmPassword);

        return "redirect:/index";
    }

    @PostMapping("/logined")
    public String loginedUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean loggedIn = userService.loginedUser(email, password);
        if (loggedIn) {
            return "redirect:/index";
        } else {
            return "redirect:/login?error=true";
        }
    }
}