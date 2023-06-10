package com.example.geektrust.model;

import com.example.geektrust.enums.PassengerType;

public class Station {
    private final String name;
    private final TransactionOperator transactionOperator;

    public Station(String name) {
        this.name = name;
        this.transactionOperator = new TransactionOperator();
    }

    public void processCheckIn(PassengerType passengerType, MetroCard metroCard) {
        boolean isThisARoundTrip = metroCard.isRoundTrip(this);
        this.transactionOperator.transact(passengerType, metroCard, isThisARoundTrip);
        metroCard.setLastTransactionStation(this);
    }

    @Override
    public String toString() {
        String newLineChar = "\n";
        String tabLineChar = "\t";
        return "TOTAL_COLLECTION" + tabLineChar +
                name + tabLineChar +
                transactionOperator +
                newLineChar;
    }
}
