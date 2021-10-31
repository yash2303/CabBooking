package com.yashasvi.uber.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yashasvi.uber.dao.Dao;
import com.yashasvi.uber.exceptions.UserAlreadyRegisteredException;
import com.yashasvi.uber.exceptions.UserNotRegisteredException;
import com.yashasvi.uber.model.Rider;
import com.yashasvi.uber.model.Trip;

@Component
public class RiderManager {
    private final Dao dao;

    public RiderManager(Dao dao) {
        this.dao = dao;
    }

    public void registerRider(String riderId, String name, String email) throws UserAlreadyRegisteredException {
        if (dao.getRider(riderId) != null) {
            throw new UserAlreadyRegisteredException();
        }
        dao.addRider(riderId, name, email);
    }

    public List<Trip> getTripHistory(String riderId) throws UserNotRegisteredException {
        Rider rider = dao.getRider(riderId);
        if (rider == null) {
            throw new UserNotRegisteredException();
        }
        return rider.getTripHistory();
    }
}
