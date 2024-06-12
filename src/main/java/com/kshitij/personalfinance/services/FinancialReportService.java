package com.kshitij.personalfinance.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kshitij.personalfinance.entities.FinancialReport;
import com.kshitij.personalfinance.repo.FinancialReportRepo;


@Service
public class FinancialReportService {
    
    @Autowired
    private FinancialReportRepo financialReportRepository;

    public List<FinancialReport> getAllReports() {
        return financialReportRepository.findAll();
    }

    public FinancialReport getReportById(int reportId) {
        return financialReportRepository.findById(reportId).orElse(null);
    }

    public FinancialReport createReport(FinancialReport report) {
        return financialReportRepository.save(report);
    }
}
