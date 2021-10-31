package com.yashasvi.uber.model.request;

import lombok.Getter;

@Getter
public class RegisterRiderRequest {
    private String userId;
    private String name;
    private String email;
}
