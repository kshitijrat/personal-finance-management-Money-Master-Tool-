package com.kshitij.personalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitij.personalfinance.entities.Transaction;
import com.kshitij.personalfinance.repo.TransactionRepo;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepo transactionRepo;

    public void addTransaction(Transaction transaction){
        transactionRepo.save(transaction);
    }

}
