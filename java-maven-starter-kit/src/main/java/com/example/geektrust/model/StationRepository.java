package com.example.geektrust.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StationRepository {
    private final Map<String, Station> stationsMap;
    private static StationRepository thisInstance;

    private StationRepository() {
        this.stationsMap = new HashMap<>();
    }

    public static StationRepository getInstance() {
        if (thisInstance == null)
            thisInstance = new StationRepository();
        return thisInstance;
    }

    public void addStation(String stationName) {
        this.stationsMap.put(stationName, new Station(stationName));
    }

    public Station getStation(String stationName) {
        return this.stationsMap.get(stationName);
    }

    public void printSummary() {
        this.stationsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new))
                .values().forEach(station -> System.out.println(station.toString()));
    }
}
