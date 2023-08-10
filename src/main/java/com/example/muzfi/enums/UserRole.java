package com.example.muzfi.enums;

public enum UserRole {
    Muzfi_Member("Member"),
    Muzfi_Elite("Elite"),
    Muzfi_Admin("Admin");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
