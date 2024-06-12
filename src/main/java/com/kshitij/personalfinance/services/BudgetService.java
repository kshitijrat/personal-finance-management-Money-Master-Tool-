package com.kshitij.personalfinance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitij.personalfinance.entities.Budget;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.repo.BudgetRepo;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepo budgetRepository;

    public Budget addBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(int id) {
        return budgetRepository.findById(id).orElse(null);
    }
}
