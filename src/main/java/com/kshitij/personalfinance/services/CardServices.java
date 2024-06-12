package com.kshitij.personalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitij.personalfinance.entities.Card;
import com.kshitij.personalfinance.repo.CardRepo;

@Service
public class CardServices {
    @Autowired
    private CardRepo repo;

    public void addCard(Card card){
        repo.save(card);
    }

}
