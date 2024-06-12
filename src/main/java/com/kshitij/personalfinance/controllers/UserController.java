package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestParam("name") String name,
            @RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("contact") String contact) {
        User user = new User();
        user.setUserName(name);
        user.setUserEmail(email);
        user.setUserPassword(passwordEncoder.encode(password));
        user.setUserPhone(contact);
        userRepository.save(user);
        return "redirect:/index";
    }

}
