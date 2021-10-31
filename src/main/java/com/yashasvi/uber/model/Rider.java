package com.yashasvi.uber.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Rider {
    private final String riderId;
    private final String name;
    private final String email;
    private List<Trip> tripHistory;
    private Trip currentTrip;

    public Rider(String riderId, String name, String email) {
        this.riderId = riderId;
        this.name = name;
        this.email = email;
        this.currentTrip = null;
        this.tripHistory = new ArrayList<>();
    }

    public void addTripToTripHistory(Trip trip) {
        this.tripHistory.add(trip);
    }
}
