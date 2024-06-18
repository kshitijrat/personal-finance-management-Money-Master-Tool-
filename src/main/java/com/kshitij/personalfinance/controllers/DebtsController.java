package com.kshitij.personalfinance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Debts;
import com.kshitij.personalfinance.repo.DebtRepo;
import com.kshitij.personalfinance.services.BankAccountService;

@Controller
public class DebtsController {

    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private DebtRepo debtRepo;
    
    @PostMapping("/add-debts")
    public String addDebts(@RequestParam("debtType")String debtType,
    @RequestParam("loanAmount")double loanAmount, @RequestParam("rate")double rate,
    @RequestParam("emiStartTime")int emiStartTime, @RequestParam("bankAccount")String bankAccount,
    @RequestParam("timePeriod")int timePeriod,Model model){
    
        //-------------
        double interest = checkInterest((double)loanAmount, (double)rate, timePeriod);
        double emi = calculateEmi(loanAmount, rate, timePeriod);
       
        //-------------------
        Debts debts = new Debts();
        BankAccount bank = bankAccountService.findBankAccount(bankAccount);
        debts.setBankAccount(bank);
        debts.setDebtType(debtType);
        debts.setEmiStartTime(emiStartTime);
        debts.setLoanAmount(loanAmount);
        debts.setRate(rate);
        debts.setTimePeriod(timePeriod);
        debts.setEmi1(emi);
        debts.setEmi4(emi*4);
        debts.setEmi12(emi*12);
        debts.setInterest(interest);
        debtRepo.save(debts);

        List<Debts> debtlist = debtRepo.findAll();
        model.addAttribute("debtslist", debtlist);
        
        return "debt_status";
    }

    private double checkInterest(double p, double r, int t) {
        double up = p*r*t;
        return up/100d;
    }

    private double calculateEmi(double p, double r, int n){
        double emi = (p*r* Math.pow((1+r), n));
        return emi/(Math.pow((1+r),n)-1);
    }

}
