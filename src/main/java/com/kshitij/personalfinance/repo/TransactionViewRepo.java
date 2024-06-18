package com.kshitij.personalfinance.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.Transaction;
import com.kshitij.personalfinance.entities.TransactionView;

public interface TransactionViewRepo extends JpaRepository<TransactionView, Integer>{
   
}
