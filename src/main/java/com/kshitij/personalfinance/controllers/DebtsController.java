package com.kshitij.personalfinance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Debts;
import com.kshitij.personalfinance.repo.DebtRepo;
import com.kshitij.personalfinance.services.BankAccountService;
import com.kshitij.personalfinance.services.DebtService;

@Controller
public class DebtsController {

    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private DebtRepo debtRepo;
    @Autowired
    private DebtService debtService;

    @PostMapping("/add-debts")
    public String addDebts(@RequestParam("debtType") String debtType,
            @RequestParam("loanAmount") double loanAmount,
            @RequestParam("rate") double rate,
            @RequestParam("emiStartTime") int emiStartTime,
            @RequestParam("bankAccount") String bankAccount,
            @RequestParam("timePeriod") int timePeriod,
            Model model,
            Authentication authentication) {

        // Calculate interest
        double interest = checkInterest(loanAmount, rate, timePeriod);

        // Create new Debts object and set properties
        Debts debts = new Debts();
        BankAccount bank = bankAccountService.findBankAccount(bankAccount);
        debts.setBankAccount(bank);
        debts.setDebtType(debtType);
        debts.setEmiStartTime(emiStartTime);
        debts.setLoanAmount(loanAmount);
        debts.setRate(rate);
        debts.setTimePeriod(timePeriod);

        // Calculate EMI
        String emi = debtService.calculateEmi((int) loanAmount, (int) rate, (int) timePeriod);

        debts.setEmi1(emi);
        debts.setEmi4(emi + "*4");
        debts.setEmi12(emi + "*12");

        // Get user email from authentication
        String email = authentication.getName();

        // Get all debts for the user and add to the model
        List<Debts> debtlist = debtService.getAllDebtsByUser(email);
        debts.setUserEmail(email);
        // // Save debt object
        debtRepo.save(debts);
        // model.addAttribute("debtslist", debtlist);
        model.addAttribute("debtslist", debtlist);
        model.addAttribute("emi1", debts.getEmi1());

        // // // Redirect to debt status page
        return "debts_status"; // Ensure debt_status.html is properly set up to display the data
    }

    private double checkInterest(double p, double r, int t) {
        double up = p * r * t;
        return up / 100d;
    }

    // @DeleteMapping("/delete-debt/{id}")
    // public String deleteDebt(@PathVariable("id") int id, Model model, Authentication authentication) {
    //     debtRepo.deleteById(id);

    //     String email = authentication.getName();
    //     List<Debts> debtlist = debtService.getAllDebtsByUser(email);
    //     model.addAttribute("debtslist", debtlist);

    //     return "debts_status";
    // }


    
    
}