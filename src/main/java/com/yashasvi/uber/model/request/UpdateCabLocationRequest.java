package com.yashasvi.uber.model.request;

import lombok.Getter;

@Getter
public class UpdateCabLocationRequest {
    private String licenseNumber;
    private double locationX;
    private double locationY;
}
