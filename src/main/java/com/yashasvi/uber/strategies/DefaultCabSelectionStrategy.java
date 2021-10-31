package com.yashasvi.uber.strategies;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yashasvi.uber.model.Cab;
import com.yashasvi.uber.model.Location;

@Component
public class DefaultCabSelectionStrategy implements CabSelectionStrategy {
    double MAX_CAB_DISTANCE_FROM_RIDER = 10;

    @Override
    public Cab selectCab(final List<Cab> availableCabs, final Location source, final Location destination) {
        for (Cab cab : availableCabs) {
            if (source.getDistance(cab.getCurrentLocation()) <= MAX_CAB_DISTANCE_FROM_RIDER) {
                return cab;
            }
        }
        return null;
    }
}
