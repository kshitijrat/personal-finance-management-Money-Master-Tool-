package com.kshitij.personalfinance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.FinancialReport;
import com.kshitij.personalfinance.entities.User;

public interface FinancialReportRepo extends JpaRepository<FinancialReport, Integer>{
    List<FinancialReport> findByUser(User user);
}
