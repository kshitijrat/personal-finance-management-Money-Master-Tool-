package com.kshitij.personalfinance.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userPhone;
    
    @OneToMany(cascade =  CascadeType.ALL)
    @Transient
    private List<BankAccount> bankAccount;

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private List<Card> card;

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private List<Investment> investment;

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private List<Transaction> transaction;

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private List<Budget> budgets;

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private List<FinancialReport> financialReports;
    
}
