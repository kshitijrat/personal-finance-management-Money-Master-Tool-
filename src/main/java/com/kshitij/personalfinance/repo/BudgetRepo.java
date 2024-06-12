package com.kshitij.personalfinance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.Budget;
import com.kshitij.personalfinance.entities.User;

public interface BudgetRepo extends JpaRepository<Budget, Integer>{
    List<Budget> findByUser(User user);
}
