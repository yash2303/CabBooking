package com.yashasvi.uber.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import com.yashasvi.uber.dao.Dao;
import com.yashasvi.uber.exceptions.NoCabsAvailableException;
import com.yashasvi.uber.exceptions.NoCurrentTripFoundException;
import com.yashasvi.uber.exceptions.UserNotRegisteredException;
import com.yashasvi.uber.model.Cab;
import com.yashasvi.uber.model.Location;
import com.yashasvi.uber.model.Rider;
import com.yashasvi.uber.model.Trip;
import com.yashasvi.uber.strategies.CabSelectionStrategy;
import com.yashasvi.uber.strategies.PricingStrategy;

@Component
@AllArgsConstructor
public class TripManager {
    private CabManager cabManager;
    private Dao dao;
    private CabSelectionStrategy cabSelectionStrategy;
    private PricingStrategy pricingStrategy;

    public void endTrip(String licenseNumber) throws NoCurrentTripFoundException {
        Cab cab = dao.getCab(licenseNumber);
        Trip trip = cab.getCurrentTrip();
        if (trip == null) {
            throw new NoCurrentTripFoundException();
        }
        String riderId = trip.getRiderId();
        Rider rider = dao.getRider(riderId);
        trip.endTrip();
        cab.setCurrentTrip(null);
        rider.setCurrentTrip(null);
    }

    public void bookTrip(String riderId, Location source, Location destination)
        throws UserNotRegisteredException, NoCabsAvailableException {
        Rider rider = dao.getRider(riderId);
        if (rider == null) {
            throw new UserNotRegisteredException();
        }
        List<Cab> availableCabs = cabManager.getAvailableCabs();
        Cab selectedCab = cabSelectionStrategy.selectCab(availableCabs, source, destination);
        if (selectedCab == null) {
            throw new NoCabsAvailableException();
        }
        double price = pricingStrategy.getPrice(source, destination);
        Trip trip = new Trip(rider.getRiderId(), selectedCab.getLicenseNumber(), source, destination, price);
        trip.startTrip();
        selectedCab.setCurrentTrip(trip);
        rider.setCurrentTrip(trip);
        selectedCab.addTripToTripHistory(trip);
        rider.addTripToTripHistory(trip);
    }
}
