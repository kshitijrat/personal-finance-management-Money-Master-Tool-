package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.BankAccountRepo;
import com.kshitij.personalfinance.repo.UserRepository;
import com.kshitij.personalfinance.services.BankAccountService;

@Controller
public class BankStatusController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountRepo bankAccountRepo;

    @Autowired
    private BankAccountService bankService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/checkPassword")
    public String checkPassword(@RequestParam("password") String password, @RequestParam("id") int id, 
                                Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            String email = getCurrentUserEmail(authentication);
            User user = userRepository.findByUserEmail(email);
            BankAccount bankAccount = bankAccountRepo.findById(id).orElse(null);
            if (bankAccount == null || !bankAccount.getUser().equals(user)) {
                redirectAttributes.addFlashAttribute("error", "Bank account not found or does not belong to you!");
                return "redirect:/bankstatus";
            }
            if (passwordEncoder.matches(password, user.getUserPassword())) {
                redirectAttributes.addFlashAttribute("bankInfo", bankAccount);
                return "redirect:/showbankinfo?id=" + id;
            } else {
                redirectAttributes.addFlashAttribute("error", "Incorrect password!");
                return "redirect:/bankstatus";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to load bank information!");
            return "redirect:/bankstatus";
        }
    }

    @GetMapping("/showbankinfo")
    public String showBankInfo(@RequestParam("id") int id, Model model) {
        BankAccount bank = bankService.getBankById(id); // account id
        if (bank == null) {
            model.addAttribute("error", "Bank account not found!");
            return "error";
        }
        model.addAttribute("bank", bank);
        return "showbankinfo";
    }

    private String getCurrentUserEmail(Authentication authentication) {
        return authentication.getName();
    }
}
