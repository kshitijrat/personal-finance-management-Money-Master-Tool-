package com.kshitij.personalfinance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.Debts;

public interface DebtRepo extends JpaRepository<Debts, Integer>{

    List<Debts> findByUserEmail(String userEmail);    
    void deleteById(int id);
}
