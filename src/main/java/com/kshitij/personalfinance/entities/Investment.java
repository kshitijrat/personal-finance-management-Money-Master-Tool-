package com.kshitij.personalfinance.entities;

import java.math.BigDecimal;
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
@Table(name = "investment")
@Entity
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int investmentId;
    private String investmentType;
    private String investmentName;
    private BigDecimal investmentAmount;
    private BigDecimal investmentReturnRate; 
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
