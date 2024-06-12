package com.kshitij.personalfinance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kshitij.personalfinance.entities.Card;
import com.kshitij.personalfinance.entities.User;

import java.util.List;


public interface CardRepo extends JpaRepository<Card,Integer>{
    Card findSingleCardByUser(User user);
    List<Card> findAllCardByUser(User user);
    Card findByCardId(int cardId);
}
