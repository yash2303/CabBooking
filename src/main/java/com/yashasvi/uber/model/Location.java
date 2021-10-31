package com.yashasvi.uber.model;

import static java.lang.Math.sqrt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Location {
    private double x;
    private double y;

    public double getDistance(Location destination) {
        return sqrt((destination.getX() - this.x) * (destination.getX() - this.x) +
            (destination.getY() - this.y) * (destination.getY() - this.y));
    }
}
