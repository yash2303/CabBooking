package com.yashasvi.uber.strategies;

import org.springframework.stereotype.Component;

import com.yashasvi.uber.model.Location;

@Component
public class DefaultPricingStrategy implements PricingStrategy {
    private static final double FARE_PER_KM = 10;
    private static final double BASE_FARE = 20;

    @Override
    public double getPrice(final Location source, final Location destination) {
        double distance = source.getDistance(destination);
        return BASE_FARE + FARE_PER_KM * distance;
    }
}
