package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.servicec.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userPage(Authentication authentication, Model model) {
        // User уже загружен с ролями в SuccessUserHandler
        model.addAttribute("user", authentication.getPrincipal());
        return "user";
    }

    @GetMapping("/list")
    public String userListPage(Authentication authentication, Model model) {
        // Получаем текущего аутентифицированного пользователя
        String username = authentication.getName();
        var user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "user-info";
    }
}