package com.kshitij.personalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitij.personalfinance.entities.Investment;
import com.kshitij.personalfinance.repo.InvestmentRepo;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepo investmentRepo;

    public void addInvestment(Investment investment){
        investmentRepo.save(investment);
    }

    
    
}