package com.yashasvi.uber.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Cab {
    private final String licenseNumber; // Unique Id
    private Location currentLocation;
    private CabStatus cabStatus;
    private Trip currentTrip;
    private List<Trip> tripHistory;

    public Cab(String licenseNumber, Location currentLocation) {
        this.licenseNumber = licenseNumber;
        this.currentLocation = currentLocation;
        this.cabStatus = CabStatus.OFFLINE;
        this.currentTrip = null;
        this.tripHistory = new ArrayList<>();
    }

    public void addTripToTripHistory(Trip trip) {
        this.tripHistory.add(trip);
    }

    public boolean isAvailable() {
        return CabStatus.ONLINE.equals(cabStatus) && currentTrip == null;
    }
}
