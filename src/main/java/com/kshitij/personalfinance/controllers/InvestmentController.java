package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kshitij.personalfinance.entities.Investment;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.UserRepository;
import com.kshitij.personalfinance.services.InvestmentService;

@Controller
public class InvestmentController {
    
    @Autowired
    private InvestmentService investmentService;

    @Autowired 
    private UserRepository userRepository;


    @PostMapping("/add-investment")
    public String addInvestment(@ModelAttribute Investment investment, 
    RedirectAttributes redirectAttributes, Authentication authentication){
        try {
            String email = getCurrentUserEmail(authentication);
            User user = userRepository.findByUserEmail(email);
            investment.setUser(user);
            investmentService.addInvestment(investment);
            redirectAttributes.addFlashAttribute("successMessage", "Investment added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add Investment. Please try again.");
        }
        return "redirect:/investments";
    }

    private String getCurrentUserEmail(Authentication authentication) {
        return authentication.getName();
    }
}
