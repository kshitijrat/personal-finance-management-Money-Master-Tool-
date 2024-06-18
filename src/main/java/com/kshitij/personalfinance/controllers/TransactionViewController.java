package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Transaction;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.BankAccountRepo;
import com.kshitij.personalfinance.repo.TransactionRepo;
import com.kshitij.personalfinance.repo.UserRepository;
import com.kshitij.personalfinance.services.TransactionService;

@Controller
public class TransactionViewController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountRepo bankAccountRepo;

    @GetMapping("/transactionsview")
    public String getTransactions(Model model, @RequestParam(defaultValue = "0") int page, Authentication authentication, @RequestParam("accountId") int accountId) {
        Page<Transaction> transactionsPage = transactionService.getTransactionsByAccountId(accountId, PageRequest.of(page, 10));
        model.addAttribute("transactionsPage", transactionsPage);

        // Fetch user details
        String email = authentication.getName();
        User user = userRepository.findByUserEmail(email);

        // Fetch bank account details
        BankAccount bank = bankAccountRepo.findByAccountId(accountId);

        // Add attributes to the model
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("email", email);
        model.addAttribute("accNo", bank.getAccountNo());
        model.addAttribute("deposite", bank.getBalance());
        model.addAttribute("lastdate", bank.getDepositeDate());

        return "transactionview";
    }
}