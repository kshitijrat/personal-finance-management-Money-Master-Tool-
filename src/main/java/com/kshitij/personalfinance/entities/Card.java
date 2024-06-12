package com.kshitij.personalfinance.entities;



import org.hibernate.annotations.Collate;

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
@Table(name = "creditcard")
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int cardId;
    private String cardName;
    private String cardType;
    @Column(unique = true)
    private String cardNumber;
    private String cardCreditLimit;
    private String balance;

    // N:1
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    // 1:N
    // 1 credit -> N transaction
}
