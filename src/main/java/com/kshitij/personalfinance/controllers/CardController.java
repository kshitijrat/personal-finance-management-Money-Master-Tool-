package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Card;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.UserRepository;
import com.kshitij.personalfinance.services.CardServices;

@Controller
public class CardController {

    @Autowired
    private CardServices cardServices;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-credit-card")
    public String addCard(@ModelAttribute Card card, RedirectAttributes redirectAttributes,
            Authentication authentication) {
        try {
            String email = getCurrentUserEmail(authentication);
            User user = userRepository.findByUserEmail(email);
            card.setUser(user);
            cardServices.addCard(card);
            redirectAttributes.addFlashAttribute("successMessage", "Card added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add Card. Please try again.");
        }
        return "redirect:/credit-cards";
    }

    private String getCurrentUserEmail(Authentication authentication) {
        return authentication.getName();
    }
}
