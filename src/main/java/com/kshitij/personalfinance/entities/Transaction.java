package com.kshitij.personalfinance.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Ensure your ID field matches the primary key type in your database

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount account;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Column(name = "transaction_category")
    private String transactionCategory;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_id")
    private String transactionId;

}