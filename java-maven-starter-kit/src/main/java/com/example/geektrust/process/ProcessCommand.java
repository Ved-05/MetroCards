package com.example.geektrust.process;

import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.enums.StationName;
import com.example.geektrust.model.*;

public class ProcessCommand {
    public static void balance(String metroCardNumber, int balance) {
        MetroCardRepository.getInstance().addCard(metroCardNumber, balance);
    }

    public static void checkIn(MetroCard metroCard, PassengerType passengerType, Station fromStation) {
        fromStation.processCheckIn(passengerType, metroCard);
    }

    public static void printSummary() {
        StationRepository.getInstance().printSummary();
    }

    public static void initialiseStations() {
        StationRepository stationRepository = StationRepository.getInstance();
        for (StationName name: StationName.values()) {
            stationRepository.addStation(name.name());
        }
    }
}
