package com.example.muzfi.response;

import com.example.muzfi.Model.Gear;

public class GearCreationResponse {
    private String message;
    private Gear createdGear;

    public GearCreationResponse(String message, Gear createdGear) {
        this.message = message;
        this.createdGear = createdGear;
    }

    public String getMessage() {
        return message;
    }

    public Gear getCreatedGear() {
        return createdGear;
    }
}
