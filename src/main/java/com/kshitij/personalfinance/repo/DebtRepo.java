package com.kshitij.personalfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.Debts;

public interface DebtRepo extends JpaRepository<Debts, Integer>{
    
}
