package com.kshitij.personalfinance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Debts;
import com.kshitij.personalfinance.repo.BankAccountRepo;
import com.kshitij.personalfinance.repo.DebtRepo;

@Service
public class DebtService {
    @Autowired
    private DebtRepo debtRepository;
    @Autowired
    private BankAccountRepo bankAccountRepository;

    public String calculateEmi(int loanAmount, int rate, int timePeriod) {
        double monthlyRate = rate / 12.0 / 100.0;
        int months = timePeriod * 12;
        double emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
        return String.format("%.2f", emi);
    }

    public void addDebt(Debts debt) throws Exception {
        BankAccount bankAccount = bankAccountRepository.findBankByAccountId(debt.getBankAccount());
        debt.setBankAccount(bankAccount);
        debtRepository.save(debt);
    }

    public List<Debts> getAllDebtsByUser(String userEmail) {
        return debtRepository.findByUserEmail(userEmail);
    }

    
}
