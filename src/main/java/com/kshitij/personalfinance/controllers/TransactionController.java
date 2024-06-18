package com.kshitij.personalfinance.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Card;
import com.kshitij.personalfinance.entities.Transaction;
import com.kshitij.personalfinance.entities.TransactionView;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.BankAccountRepo;
import com.kshitij.personalfinance.repo.CardRepo;
import com.kshitij.personalfinance.repo.InvestmentRepo;
import com.kshitij.personalfinance.repo.TransactionRepo;
import com.kshitij.personalfinance.repo.TransactionViewRepo;
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

    @Autowired
    private TransactionViewRepo transactionViewRepo;

    @PostMapping("/add-transaction")
    public String addTransaction(@ModelAttribute Transaction transaction, @RequestParam("accountId") int accountId,
            @RequestParam("cardId") int cardId,
            RedirectAttributes redirectAttributes, Authentication authentication) {
        try {
            String email = getCurrentUserEmail(authentication);
            User user = userRepository.findByUserEmail(email);
            BankAccount bankAccount = bankAccountRepo.findByAccountId(accountId);
            Card card = cardRepo.findByCardId(cardId);
            transaction.setTransactionDate(currDate());
            transaction.setAccount(bankAccount);
            transaction.setCard(card);
            transaction.setTransactionId(generateTransactionId());

            if (transaction.getTransactionType().equals("Debit")) {
                Double bankBalance = Double.parseDouble(bankAccount.getBalance());
                Double remain = bankBalance - transaction.getTransactionAmount();
                bankAccount.setBalance(String.valueOf(remain));
                bankAccountRepo.save(bankAccount);
            } else {
                Double bankBalance = Double.parseDouble(bankAccount.getBalance());
                Double remain = bankBalance + transaction.getTransactionAmount();
                bankAccount.setBalance(String.valueOf(remain));
                bankAccountRepo.save(bankAccount);
            }

            transactionService.addTransaction(transaction);
            TransactionView tView = new TransactionView();
            tView.setAmount(transaction.getTransactionAmount());
            tView.setBankAccount(bankAccount);
            tView.setCurrDate(currDate());
            transactionViewRepo.save(tView);

            redirectAttributes.addFlashAttribute("successMessage", "Transaction added successfully!");
            System.out.println("success***********");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add Transaction. Please try again.");
        }
        return "redirect:/transactions";
    }

    private String getCurrentUserEmail(Authentication authentication) {
        return authentication.getName();
    }

    private String currDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

    private String generateTransactionId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
