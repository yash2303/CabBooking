package com.yashasvi.uber.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yashasvi.uber.model.Cab;
import com.yashasvi.uber.model.Location;
import com.yashasvi.uber.model.Rider;

@Component
public interface Dao {
    Rider getRider(String riderId);
    void addRider(String riderId, String name, String email);
    Cab getCab(String licenseNumber);
    void addCab(String licenseNumber, Location currentLocation);
    List<Cab> getAvailableCabs();
}
