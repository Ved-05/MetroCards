package com.example.geektrust.model;

import com.example.geektrust.exceptions.MetroCardNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class MetroCardRepository {
    private Map<String, MetroCard> metroCards;
    private static MetroCardRepository thisInstance;

    private MetroCardRepository() {
        this.metroCards = new HashMap<>();
    }

    public static MetroCardRepository getInstance() {
        if (thisInstance == null)
            thisInstance = new MetroCardRepository();
        return thisInstance;
    }

    public MetroCard getCard(String metroCardId) throws MetroCardNotFoundException {
        MetroCard card = this.metroCards.get(metroCardId);
        if (card == null)
            throw new MetroCardNotFoundException(String.format("MetroCard %s is not available for journey", metroCardId));
        return card;
    }

    public void addCard(String metroCardNumber, int balance) {
        MetroCard metroCard = new MetroCard(metroCardNumber);
        metroCard.creditAmount(balance);
        this.metroCards.put(metroCard.getId(), metroCard);
    }
}
