package com.yashasvi.uber.model;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Trip {
    private final String tripId;
    private final String riderId;
    private final String cabId;
    private final Location source;
    private final Location destination;
    private final double price;
    private TripStatus tripStatus;

    public Trip(
        String riderId,
        String cabId,
        Location source,
        Location destination,
        double price) {
        this.tripId = UUID.randomUUID().toString();
        this.source = source;
        this.destination = destination;
        this.cabId = cabId;
        this.riderId = riderId;
        this.price = price;
        this.tripStatus = TripStatus.IN_PROGRESS;
    }

    public boolean startTrip() {
        if (this.tripStatus == TripStatus.WAITING) {
            this.tripStatus = TripStatus.IN_PROGRESS;
            return true;
        }
        return false;
    }

    public boolean endTrip() {
        if (this.tripStatus == TripStatus.IN_PROGRESS) {
            this.tripStatus = TripStatus.COMPLETED;
            return true;
        }
        return false;
    }
}
