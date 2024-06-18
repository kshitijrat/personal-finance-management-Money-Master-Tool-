package com.kshitij.personalfinance.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.BankAccountRepo;
import com.kshitij.personalfinance.repo.UserRepository;

@Controller
public class DepositeController {

    @GetMapping("/deposite")
    public String showDeposite() {
        return "deposite";
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountRepo bankAccountRepo;

    @GetMapping("/deposite-form")
    public String depositeForm(@RequestParam("depositAmount") int depositAmount,
            @RequestParam("accountNo") String accountNo, @RequestParam("password") String password,
            Authentication authentication, RedirectAttributes redirectAttributes) {
        String email = authentication.getName();
        User user = new User();
        user = userRepository.findByUserEmail(email);
        
        if (passwordEncoder.matches(password, user.getUserPassword())) {
            BankAccount bank = new BankAccount();
            bank.setBalance(bank.getBankName() + depositAmount);
            bank.setDepositeDate(currDate());
            redirectAttributes.addFlashAttribute("successMessage", "Deposite successful!");
        }else {
            redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong!");
        }
        return "redirect:/deposite";
    }

    private String currDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

}
