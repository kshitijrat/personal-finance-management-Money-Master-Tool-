package com.kshitij.personalfinance.entities;

import jakarta.persistence.Column;
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
@Table(name = "bankaccount")
@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int accountId;
    private String bankName;
    private String accountType;
    @Column(unique =  true)
    private String accountNo;
    private String balance;

    //N:1
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    
    // 1: N
    // 1 acc -> N transaction

}
