package com.yashasvi.uber.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yashasvi.uber.dao.Dao;
import com.yashasvi.uber.exceptions.CabAlreadyRegisteredException;
import com.yashasvi.uber.exceptions.CabNotRegisteredException;
import com.yashasvi.uber.model.Cab;
import com.yashasvi.uber.model.CabStatus;
import com.yashasvi.uber.model.Location;
import com.yashasvi.uber.model.Trip;

@Component
public class CabManager {
    private final Dao dao;

    public CabManager(Dao dao) {
        this.dao = dao;
    }

    public void registerCab(String licenseNumber, Location currentLocation) throws CabAlreadyRegisteredException {
        if (dao.getCab(licenseNumber) != null) {
            throw new CabAlreadyRegisteredException();
        }
        dao.addCab(licenseNumber, currentLocation);
        System.out.println("Registration successful");
    }

    public void updateCabLocation(String licenseNumber, Location location) throws CabNotRegisteredException {
        Cab cab = dao.getCab(licenseNumber);
        if (cab == null) {
            throw new CabNotRegisteredException();
        }
        cab.setCurrentLocation(location);
    }

    public void updateCabAvailability(String licenseNumber, boolean availability) throws CabNotRegisteredException {
        Cab cab = dao.getCab(licenseNumber);
        if (cab == null) {
            throw new CabNotRegisteredException();
        }
        cab.setCabStatus(availability ? CabStatus.ONLINE : CabStatus.OFFLINE);
    }

    public List<Cab> getAvailableCabs() {
        return dao.getAvailableCabs();
    }

    public List<Trip> getTripHistory(String licenseNumber) throws CabNotRegisteredException {
        Cab cab = dao.getCab(licenseNumber);
        if (cab == null) {
            throw new CabNotRegisteredException();
        }
        return cab.getTripHistory();
    }
}
