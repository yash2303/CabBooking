package com.yashasvi.uber.strategies;

import com.yashasvi.uber.model.Location;

public interface PricingStrategy {
    double getPrice(Location source, Location destination);
}
