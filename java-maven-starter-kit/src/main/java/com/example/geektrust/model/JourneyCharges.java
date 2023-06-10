package com.example.geektrust.model;

import com.example.geektrust.enums.PassengerType;

import java.util.HashMap;
import java.util.Map;

public class JourneyCharges {
    private final Map<PassengerType, Integer> metroCardCharges;
    private final double discountOnRoundTrip;
    private final double autoRechargeServiceCharge;

    private static JourneyCharges thisInstance;

    private JourneyCharges() {
        this.metroCardCharges = new HashMap<>();
        this.metroCardCharges.put(PassengerType.ADULT, 200);
        this.metroCardCharges.put(PassengerType.SENIOR_CITIZEN, 100);
        this.metroCardCharges.put(PassengerType.KID, 50);
        this.discountOnRoundTrip = 0.50;
        this.autoRechargeServiceCharge = 0.02;
    }

    public static JourneyCharges getInstance() {
        if (thisInstance == null)
            thisInstance = new JourneyCharges();
        return thisInstance;
    }

    public double getSingleJourneyCharge(PassengerType passengerType) {
        return this.metroCardCharges.get(passengerType);
    }

    public double getRoundJourneyDiscount() {
        return this.discountOnRoundTrip;
    }

    public double getAutoRechargeServiceCharge() {
        return this.autoRechargeServiceCharge;
    }
}
