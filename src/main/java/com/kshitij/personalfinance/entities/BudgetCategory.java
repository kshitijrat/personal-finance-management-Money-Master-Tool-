package com.kshitij.personalfinance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "budgetcategory")
@Entity
public class BudgetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int categoryId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "budgetId")
    private Budget budget;
    private String AllocatedAmount;
    private String spentAmount;
}
