package com.yashasvi.uber.model.request;

import lombok.Getter;

@Getter
public class BookCabRequest {
    private String riderId;
    private double sourceX;
    private double sourceY;
    private double destinationX;
    private double destinationY;
}
