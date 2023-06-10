package com.example.geektrust.model;

public class MetroCard {

    private String id;
    private double availableAmount;
    private boolean isLastTripComplete;
    private Station lastTransactionStation;

    public MetroCard(String id) {
        this.id = id;
        this.availableAmount = 0;
        this.isLastTripComplete = true;
    }

    public void creditAmount(double amount) {
        this.availableAmount += amount;
    }

    public void debitAmount(double amount) {
        this.availableAmount -= amount;
    }

    public String getId() {
        return id;
    }

    public boolean isRoundTrip(Station fromStation) {
        if (!isLastTripComplete)
            isLastTripComplete = lastTransactionStation != fromStation;
        else
            isLastTripComplete = false;
        return isLastTripComplete;
    }

    public void setLastTransactionStation(Station lastTransactionStation) {
        this.lastTransactionStation = lastTransactionStation;
    }

    public boolean hasSufficientBalance(double travelCharge) {
        return travelCharge <= this.availableAmount;
    }

    public double recharge(double journeyCharge) {
        double requiredCharge = journeyCharge - this.availableAmount;
        creditAmount(requiredCharge);
        return requiredCharge;
    }
}
