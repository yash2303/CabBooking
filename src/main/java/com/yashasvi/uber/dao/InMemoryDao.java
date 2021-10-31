package com.yashasvi.uber.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.yashasvi.uber.model.Cab;
import com.yashasvi.uber.model.Location;
import com.yashasvi.uber.model.Rider;

@Component
public class InMemoryDao implements Dao {
    private final Map<String, Rider> userIdToRiderMap;
    private final Map<String, Cab> licenseNumberToCabMap;

    public InMemoryDao() {
        this.userIdToRiderMap = new HashMap<>();
        this.licenseNumberToCabMap = new HashMap<>();
    }

    @Override
    public Rider getRider(String riderId) {
        return userIdToRiderMap.get(riderId);
    }

    @Override
    public void addRider(String riderId, String name, String email) {
        Rider rider = new Rider(riderId, name, email);
        userIdToRiderMap.put(riderId, rider);
        System.out.println(userIdToRiderMap);
    }

    @Override
    public Cab getCab(String licenseNumber) {
        return licenseNumberToCabMap.get(licenseNumber);
    }

    public void addCab(String licenseNumber, Location currentLocation) {
        Cab cab = new Cab(licenseNumber, currentLocation);
        licenseNumberToCabMap.put(licenseNumber, cab);
        System.out.println(licenseNumberToCabMap);
    }

    @Override
    public List<Cab> getAvailableCabs() {
        return licenseNumberToCabMap.values().stream()
            .filter(Cab::isAvailable)
            .collect(Collectors.toList());
    }
}
