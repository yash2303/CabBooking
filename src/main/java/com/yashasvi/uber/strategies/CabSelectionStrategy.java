package com.yashasvi.uber.strategies;

import java.util.List;

import com.yashasvi.uber.model.Cab;
import com.yashasvi.uber.model.Location;

public interface CabSelectionStrategy {
    Cab selectCab(final List<Cab> availableCabs, final Location source, final Location destination);
}
