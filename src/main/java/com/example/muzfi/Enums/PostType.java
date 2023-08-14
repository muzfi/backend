package com.example.muzfi.Enums;

public enum PostType {
    PROD_SALE("Listing"),
    PROD_GEAR("Gear"),
    PROD_TOPIC("Topic"),
    PROD_POLL("Poll");

    private final String displayName;

    PostType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
