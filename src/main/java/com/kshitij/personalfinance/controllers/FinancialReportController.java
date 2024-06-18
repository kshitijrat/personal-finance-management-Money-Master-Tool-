package com.kshitij.personalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.kshitij.personalfinance.entities.FinancialReport;
import com.kshitij.personalfinance.services.FinancialReportService;

@Controller
public class FinancialReportController {

    @Autowired
    private FinancialReportService financialReportService;

    @GetMapping("/financial-reports")
    public String getAllReports(Model model) {
        model.addAttribute("reports", financialReportService.getAllReports());
        return "financial";
    }

    @GetMapping("/financial-report/{id}")
    public String getReportById(@PathVariable int id, Model model) {
        model.addAttribute("report", financialReportService.getReportById(id));
        return "report";
    }

    @GetMapping("/create-financial-report")
    public String showCreateReportForm(Model model) {
        model.addAttribute("report", new FinancialReport());
        return "create-financial-report";
    }

    @PostMapping("/financial-report")
    public String createReport(FinancialReport report) {
        financialReportService.createReport(report);
        return "redirect:/financial-reports";
    }

    

}
