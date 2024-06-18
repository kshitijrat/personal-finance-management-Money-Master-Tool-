package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kshitij.personalfinance.repo.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Authentication authentication,Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("userName", userRepository.findByUserEmail(authentication.getName()).getUserName());
            return "redirect:/dashboard";
        }

        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, null);
        return "redirect:/index";
    }
}
