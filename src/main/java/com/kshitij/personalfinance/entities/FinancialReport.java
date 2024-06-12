package com.kshitij.personalfinance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "financialreport")
@Entity
public class FinancialReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int reportId;
    private String reportName;
    private String reportGeneratedDate;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
