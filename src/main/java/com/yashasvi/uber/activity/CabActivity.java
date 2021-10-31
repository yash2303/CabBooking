package com.yashasvi.uber.activity;

import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import com.yashasvi.uber.controller.CabManager;
import com.yashasvi.uber.controller.TripManager;
import com.yashasvi.uber.exceptions.CabAlreadyRegisteredException;
import com.yashasvi.uber.exceptions.CabNotRegisteredException;
import com.yashasvi.uber.exceptions.NoCurrentTripFoundException;
import com.yashasvi.uber.model.Location;
import com.yashasvi.uber.model.Trip;
import com.yashasvi.uber.model.request.RegisterCabRequest;
import com.yashasvi.uber.model.request.UpdateAvailabilityRequest;
import com.yashasvi.uber.model.request.UpdateCabLocationRequest;

@RestController
@AllArgsConstructor
public class CabActivity {
    private final CabManager cabManager;
    private final TripManager tripManager;

    @RequestMapping(value = "/cab/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerCab(RequestEntity<RegisterCabRequest> requestEntity) {
        RegisterCabRequest request = requestEntity.getBody();
        assert request != null;
        String licenseNumber = request.getLicenseNumber();
        double locationX = request.getLocationX();
        double locationY = request.getLocationY();
        try {
            cabManager.registerCab(licenseNumber, new Location(locationX, locationY));
        } catch (CabAlreadyRegisteredException e) {
            return ResponseEntity.ok("Cab already registered exception");
        }
        return ResponseEntity.ok("Cab registration successful");
    }

    @RequestMapping(value = "/cab/updateLocation", method = RequestMethod.POST)
    public ResponseEntity<String> updateCabLocation(RequestEntity<UpdateCabLocationRequest> requestEntity) {
        UpdateCabLocationRequest request = requestEntity.getBody();
        assert request != null;
        String licenseNumber = request.getLicenseNumber();
        double locationX = request.getLocationX();
        double locationY = request.getLocationY();
        try {
            cabManager.updateCabLocation(licenseNumber, new Location(locationX, locationY));
        } catch (CabNotRegisteredException e) {
            return ResponseEntity.ok("Cab not registered");
        }
        return ResponseEntity.ok("Cab location updated successfully");
    }

    @RequestMapping(value = "/cab/updateAvailability", method = RequestMethod.POST)
    public ResponseEntity<String> updateAvailability(RequestEntity<UpdateAvailabilityRequest> requestEntity) {
        UpdateAvailabilityRequest request = requestEntity.getBody();
        assert request != null;
        try {
            cabManager.updateCabAvailability(request.getLicenseNumber(), request.isAvailability());
        } catch (CabNotRegisteredException e) {
            ResponseEntity.ok("Cab not registered");
        }
        return ResponseEntity.ok("Cab availability updated");
    }

    @RequestMapping(value = "/cab/endTrip", method = RequestMethod.POST)
    public ResponseEntity<String> endTrip(RequestEntity<String> requestEntity) {
        try {
            tripManager.endTrip(requestEntity.getBody());
        } catch (NoCurrentTripFoundException e) {
            return ResponseEntity.ok("No current trip to end");
        }
        return ResponseEntity.ok("Trip ended successfully");
    }

    @RequestMapping(value = "/cab/getBookingHistory", method = RequestMethod.GET)
    public ResponseEntity<List<Trip>> getBookingHistory(RequestEntity<String> requestEntity)
        throws CabNotRegisteredException {
        return ResponseEntity.ok(cabManager.getTripHistory(requestEntity.getBody()));
    }
}
