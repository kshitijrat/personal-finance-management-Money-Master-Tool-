package com.kshitij.personalfinance.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "debts")
@Entity
public class Debts {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE) 
   private int id;
   private String debtType;
   private double loanAmount;
    private double rate;
    private int timePeriod;
    private int emiStartTime;
    @ManyToOne
    private BankAccount bankAccount;
    @Transient
    private double emi1;
    @Transient
    private double emi4;
    @Transient
    private double emi12;
    @Transient
    private double interest;
    @Transient
    private double totalCost;
    
}
