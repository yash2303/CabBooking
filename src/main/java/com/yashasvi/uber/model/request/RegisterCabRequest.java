package com.yashasvi.uber.model.request;

import lombok.Getter;

@Getter
public class RegisterCabRequest {
    private String licenseNumber;
    private double locationX;
    private double locationY;
}
