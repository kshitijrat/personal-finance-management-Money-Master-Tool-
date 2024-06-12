package com.kshitij.personalfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.User;

import java.util.List;


public interface BankAccountRepo extends JpaRepository<BankAccount,Integer>{
    BankAccount findByAccountNo(String accountNo);
    BankAccount findByUser(User user);
    List<BankAccount> findAllByUser(User user);
    BankAccount  findByAccountId(int accountId);

}
