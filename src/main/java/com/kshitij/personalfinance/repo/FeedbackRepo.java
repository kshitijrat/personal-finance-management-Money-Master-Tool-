package com.kshitij.personalfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.Feedback;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{
    
}
