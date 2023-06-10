package com.example.geektrust.model;

import com.example.geektrust.enums.PassengerType;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionOperator {
    private final CollectionBook collectionBook;
    private final Map<PassengerType, Integer> passengerCount;
    private final JourneyCharges journeyCharges;

    public TransactionOperator() {
        this.collectionBook = new CollectionBook();
        this.passengerCount = new HashMap<>();
        this.journeyCharges = JourneyCharges.getInstance();
    }

    public void transact(PassengerType passengerType, MetroCard metroCard, boolean isRoundTrip) {
        double singleJourneyCharge = this.journeyCharges.getSingleJourneyCharge(passengerType);
        double discountDueToRoundTrip = isRoundTrip ? this.journeyCharges.getRoundJourneyDiscount() * singleJourneyCharge : 0;
        double journeyCharge = singleJourneyCharge - discountDueToRoundTrip;
        if (!metroCard.hasSufficientBalance(journeyCharge)) {
            double serviceCharge = processRecharge(metroCard, journeyCharge);
            this.collectionBook.updateCollectedServiceCharge(serviceCharge);
        }
        metroCard.debitAmount(journeyCharge);
        this.collectionBook.updateCollectedTravelCharge(journeyCharge);
        this.collectionBook.updateGivenDiscount(discountDueToRoundTrip);
        this.incrementPassengerCount(passengerType);
    }

    private void incrementPassengerCount(PassengerType passengerType) {
        int currentCount = this.passengerCount.getOrDefault(passengerType, 0);
        this.passengerCount.put(passengerType, ++currentCount);
    }

    public double processRecharge(MetroCard metroCard, double journeyCharge) {
        double loadedAmount = metroCard.recharge(journeyCharge);
        return loadedAmount * this.journeyCharges.getAutoRechargeServiceCharge();
    }

    @Override
    public String toString() {
        String newLineChar = "\n";
        String tabLineChar = "\t";
        StringBuffer passengerSummary = new StringBuffer("PASSENGER_TYPE_SUMMARY" + newLineChar);
        this.passengerCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new))
                .forEach((k, v) ->
                        passengerSummary
                                .append(k)
                                .append(tabLineChar)
                                .append(v)
                                .append(newLineChar));
        return this.collectionBook.toString() + newLineChar + passengerSummary;
    }
}
