package com.kshitij.personalfinance.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.BankAccountRepo;
import com.kshitij.personalfinance.repo.UserRepository;
import com.kshitij.personalfinance.services.BankAccountService;
import com.kshitij.personalfinance.services.CustomUserDetails;

@Controller
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-bank-account")
    public String addBankAccount(@ModelAttribute BankAccount bankAccount, RedirectAttributes redirectAttributes, Authentication authentication) {
        
        try {
            String userEmail = getCurrentUserEmail(authentication);
            User user = userRepository.findByUserEmail(userEmail);
            bankAccount.setUser(user);
            String upperCaseName = bankAccount.getBankName().toUpperCase();
            bankAccount.setBankName(upperCaseName);
            bankAccountService.addBankAccount(bankAccount);
            redirectAttributes.addFlashAttribute("successMessage", "Bank account added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add bank account. Please try again.");
        }
        return "redirect:/bank-accounts";
    }

    private String getCurrentUserEmail(Authentication authentication) {
        return authentication.getName();
    }
}
