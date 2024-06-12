package com.kshitij.personalfinance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.Investment;
import com.kshitij.personalfinance.entities.User;

public interface InvestmentRepo extends JpaRepository<Investment, Integer> {
    Investment findByUser(User user);
    Investment findByInvestmentId(int investmentId);
}
