package com.kshitij.personalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitij.personalfinance.repo.DebtRepo;

@Service
public class DebtService {
    @Autowired
    private DebtRepo debtRepo;

    

}
