package com.kshitij.personalfinance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.Budget;
import com.kshitij.personalfinance.entities.BudgetCategory;

public interface BudgetCategoryRepo extends JpaRepository<BudgetCategory, Integer>{
    List<BudgetCategory> findByBudget(Budget budget);
}
