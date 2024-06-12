package com.kshitij.personalfinance.repo;

import com.kshitij.personalfinance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserEmail(String email);
}

