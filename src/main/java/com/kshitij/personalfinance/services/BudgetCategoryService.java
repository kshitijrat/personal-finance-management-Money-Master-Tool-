package com.kshitij.personalfinance.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitij.personalfinance.entities.Budget;
import com.kshitij.personalfinance.entities.BudgetCategory;
import com.kshitij.personalfinance.repo.BudgetCategoryRepo;

@Service
public class BudgetCategoryService {
   @Autowired
    private BudgetCategoryRepo budgetCategoryRepository;

    public List<BudgetCategory> getCategoriesByBudget(Budget budget) {
        return budgetCategoryRepository.findByBudget(budget);
    }

    public BudgetCategory saveBudgetCategory(BudgetCategory budgetCategory) {
        return budgetCategoryRepository.save(budgetCategory);
    }

    public void deleteBudgetCategory(int id) {
        budgetCategoryRepository.deleteById(id);
    }

    public void deleteCategory(int id) {
        budgetCategoryRepository.deleteById(id);
    }
}