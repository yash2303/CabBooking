package com.yashasvi.uber.model.request;

import lombok.Getter;

@Getter
public class UpdateAvailabilityRequest {
    private String licenseNumber;
    private boolean availability;
}
