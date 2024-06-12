package com.kshitij.personalfinance.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name = "transaction")
@Entity
public class Transaction {
    @Id
    private int transactionId;
    private String transactionDate;
    private String transactionAmount;
    private String transactionCategory;
    @ManyToOne()
    @JoinColumn(name = "cardId")
    private Card card;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private BankAccount account;
    @ManyToOne
    @JoinColumn(name = "investmentId")
    private Investment investment;
}
