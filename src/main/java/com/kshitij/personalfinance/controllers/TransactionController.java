package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Card;
import com.kshitij.personalfinance.entities.Transaction;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.BankAccountRepo;
import com.kshitij.personalfinance.repo.CardRepo;
import com.kshitij.personalfinance.repo.InvestmentRepo;
import com.kshitij.personalfinance.repo.TransactionRepo;
import com.kshitij.personalfinance.repo.UserRepository;
import com.kshitij.personalfinance.services.TransactionService;

@Controller
public class TransactionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountRepo bankAccountRepo;
    
    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private InvestmentRepo investmentRepo;
    
    @PostMapping("/add-transaction")
    public String addTransaction(@ModelAttribute Transaction transaction,
    RedirectAttributes redirectAttributes, Authentication authentication){
        try {
            String email = getCurrentUserEmail(authentication);
            User user = userRepository.findByUserEmail(email);
            // BankAccount bankAccount = bankAccountRepo.findByUser(user);
            // Card card = cardRepo.findSingleCardByUser(user);
            transactionService.addTransaction(transaction);
            redirectAttributes.addFlashAttribute("successMessage", "Transaction added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add Transaction. Please try again.");
        }
        return "redirect:/transactions";
    }


     private String getCurrentUserEmail(Authentication authentication) {
        return authentication.getName();
    }
}
