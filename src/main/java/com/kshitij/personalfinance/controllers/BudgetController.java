package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kshitij.personalfinance.entities.Budget;
import com.kshitij.personalfinance.entities.BudgetCategory;
import com.kshitij.personalfinance.entities.User;
import com.kshitij.personalfinance.services.BudgetCategoryService;
import com.kshitij.personalfinance.services.BudgetService;
import com.kshitij.personalfinance.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/budgets")
    public String getBudgets(Model model) {
        List<Budget> budgets = budgetService.getAllBudgets();
        model.addAttribute("bud", budgets);
        return "budgets";
    }

    @PostMapping("/add-budget")
    public String addBudget(@ModelAttribute Budget budget) {
        budgetService.addBudget(budget);
        return "redirect:/budgets";
    }

    @GetMapping("/budget/{id}")
    public String getBudget(@PathVariable int id, Model model) {
        Budget budget = budgetService.getBudgetById(id);
        model.addAttribute("budget", budget);
        return "budget-detail";
    }
    
}
