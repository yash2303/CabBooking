package com.yashasvi.uber.activity;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yashasvi.uber.controller.RiderManager;
import com.yashasvi.uber.controller.TripManager;
import com.yashasvi.uber.exceptions.NoCabsAvailableException;
import com.yashasvi.uber.exceptions.UserAlreadyRegisteredException;
import com.yashasvi.uber.exceptions.UserNotRegisteredException;
import com.yashasvi.uber.model.Location;
import com.yashasvi.uber.model.request.BookCabRequest;
import com.yashasvi.uber.model.request.RegisterRiderRequest;

@RestController
public class RiderActivity {
    private final RiderManager riderManager;
    private final TripManager tripManager;

    public RiderActivity(RiderManager riderManager, TripManager tripManager) {
        this.riderManager = riderManager;
        this.tripManager = tripManager;
    }

    @RequestMapping(value = "/rider/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerRider(RequestEntity<RegisterRiderRequest> requestEntity) {
        RegisterRiderRequest request = requestEntity.getBody();
        assert request != null;
        try {
            riderManager.registerRider(request.getUserId(), request.getName(), request.getEmail());
        } catch (UserAlreadyRegisteredException e) {
            return ResponseEntity.ok("Rider already registered");
        }
        return ResponseEntity.ok("Rider registered successfully");
    }

    @RequestMapping(value = "/rider/bookCab", method = RequestMethod.POST)
    public ResponseEntity<String> bookCab(RequestEntity<BookCabRequest> requestEntity) {
        BookCabRequest request = requestEntity.getBody();
        assert request != null;
        try {
            tripManager.bookTrip(request.getRiderId(), new Location(request.getSourceX(), request.getSourceY()),
                new Location(request.getDestinationX(), request.getDestinationY()));
        } catch (UserNotRegisteredException e) {
            return ResponseEntity.ok("User not registered");
        } catch (NoCabsAvailableException e) {
            return ResponseEntity.ok("No cabs available");
        }
        return ResponseEntity.ok("Trip booked successfully");
    }

    @RequestMapping(value = "/rider/getBookingHistory", method = RequestMethod.GET)
    public ResponseEntity<String> getBookingHistory(RequestEntity<String> requestEntity)
        throws UserNotRegisteredException {
        return ResponseEntity.ok(riderManager.getTripHistory(requestEntity.getBody()).toString());
    }
}
