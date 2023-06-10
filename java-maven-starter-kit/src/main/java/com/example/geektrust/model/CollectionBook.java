package com.example.geektrust.model;

public class CollectionBook {
    private double collectedTravelCharges;
    private double givenDiscounts;
    private double collectedServiceCharges;

    public CollectionBook() {
        this.collectedTravelCharges = 0;
        this.givenDiscounts = 0;
        this.collectedServiceCharges = 0;
    }

    public double getTotalCollection() {
        return this.collectedServiceCharges + this.collectedTravelCharges;
    }

    public void updateCollectedServiceCharge(double serviceCharge) {
        this.collectedServiceCharges += serviceCharge;
    }

    public void updateCollectedTravelCharge(double travelCharge) {
        this.collectedTravelCharges += travelCharge;
    }

    public void updateGivenDiscount(double discount) {
        this.givenDiscounts += discount;
    }

    @Override
    public String toString() {
        return (int) getTotalCollection() + "\t" + (int) this.givenDiscounts;
    }
}
