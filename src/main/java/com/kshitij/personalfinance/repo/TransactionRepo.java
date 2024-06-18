package com.kshitij.personalfinance.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.Transaction;
import com.kshitij.personalfinance.entities.User;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{
    
     Page<Transaction> findAll(Pageable pageable);
     Page<Transaction> findByAccount_AccountId(int accountId, Pageable pageable);

}
