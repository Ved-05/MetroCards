package com.example.geektrust;

import com.example.geektrust.enums.CommandType;
import com.example.geektrust.enums.PassengerType;
import com.example.geektrust.exceptions.MetroCardNotFoundException;
import com.example.geektrust.model.MetroCard;
import com.example.geektrust.model.MetroCardRepository;
import com.example.geektrust.model.Station;
import com.example.geektrust.model.StationRepository;
import com.example.geektrust.process.ProcessCommand;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide input file path.");
            return;
        }

        ProcessCommand.initialiseStations();

        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                try {
                    process(input);
                } catch (MetroCardNotFoundException exception) {
                    System.out.println("Exception occurred while processing input " + input + " :: " +
                            exception.getMessage());
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            System.out.println("Exception occurred while reading input " + args[0] + " :: " +
                    e.getMessage());
        }
    }

    private static void process(String input) throws MetroCardNotFoundException {
        String[] inputs = input.split(" ");
        CommandType command = CommandType.valueOf(inputs[0]);

        switch (command) {
            case BALANCE:
                String metroCardNumber = inputs[1];
                int balance = Integer.parseInt(inputs[2]);
                ProcessCommand.balance(metroCardNumber, balance);
                break;
            case CHECK_IN:
                MetroCard metroCard = MetroCardRepository.getInstance().getCard(inputs[1]);
                PassengerType passengerType = PassengerType.valueOf(inputs[2]);
                Station fromStation = StationRepository.getInstance().getStation(inputs[3]);
                ProcessCommand.checkIn(metroCard, passengerType, fromStation);
                break;
            case PRINT_SUMMARY:
                ProcessCommand.printSummary();
        }
    }
}
