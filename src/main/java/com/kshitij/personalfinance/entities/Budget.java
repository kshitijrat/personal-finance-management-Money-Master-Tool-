package com.kshitij.personalfinance.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "budget")
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int budgetId;
    private String budgetName;
    private String budgetAmount;
    private String budgetStartDate;
    private String budgetEndDate;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(cascade =  CascadeType.ALL)
    @Transient
    private List<BudgetCategory> budgetCategories;
}
