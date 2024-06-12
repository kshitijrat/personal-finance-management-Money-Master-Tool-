package com.kshitij.personalfinance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kshitij.personalfinance.entities.BankAccount;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.BankAccountRepo;
import com.kshitij.personalfinance.repo.UserRepository;

@Service
public class BankAccountService {
    
    @Autowired
    private BankAccountRepo repo;

    @Autowired
    private UserRepository userRepository;

    public void addBankAccount(BankAccount account){
        repo.save(account);
    }

    public List<BankAccount> getBanksForUser(User user){
        return repo.findAllByUser(user);        
    }

    public String getCurrentUser(Authentication authentication){
        return authentication.getName();
    }

    public BankAccount getBankById(int id){
        return repo.findByAccountId(id);
    }
    

}
