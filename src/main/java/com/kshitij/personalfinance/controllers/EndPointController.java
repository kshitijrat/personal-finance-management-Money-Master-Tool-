package com.kshitij.personalfinance.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Budget;
import com.kshitij.personalfinance.entities.Card;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.UserRepository;
import com.kshitij.personalfinance.services.BankAccountService;

@Controller
public class EndPointController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountService bankSevice;

    @GetMapping("/index")
    public String login() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage2() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/bank-accounts")
    public String showBankAccount(Model model) {
        model.addAttribute("bank", new BankAccount());
        return "bank_accounts";
    }

    @GetMapping("/bankstatus")
    public String getUserBanks(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByUserEmail(email);
        List<BankAccount> banklist = bankSevice.getBanksForUser(user);
        model.addAttribute("bank",banklist);
        return "bankstatus";
    }

    @GetMapping("/credit-cards")
    public String showCreditCards(Model model) {
        model.addAttribute("card", new Card());
        return "credit_card";
    }

    @GetMapping("/investments")
    public String showInvestments() {
        return "investments";
    }

    @GetMapping("/transactions")
    public String showTranscation() {
        return "transactions";
    }

    @GetMapping("/budget-detail")
    public String showBudgetDetails(){
        return "budget_details";
    }

    // @GetMapping("/financial-reports")
    // public String showFinancialReports() {
    //     return "financial_reports";
    // }

    @GetMapping("/profile")
    public String showProfile(){
        return "profile";
    }

    @GetMapping("/about")
    public String showAbout(){
        return "about";
    }

    @GetMapping("/contact")
    public String showContact(){
        return "contact";
    }
}
