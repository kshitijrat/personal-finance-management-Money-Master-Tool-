package com.kshitij.personalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Transaction> getAllTransaction(Pageable pageable){
        return transactionRepo.findAll(pageable);
    }

    public Page<Transaction> getTransactionsByAccountId(int accountId, Pageable pageable) {
        return transactionRepo.findByAccount_AccountId(accountId, pageable);
    }

}
