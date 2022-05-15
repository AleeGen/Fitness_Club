package com.example.fitnessclub.model.entity;

public enum UserRole {
    ADMIN("admin"),
    TRAINER("trainer"),
    CLIENT("client");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public static UserRole getRole(String role) {
        for (UserRole r : UserRole.values()) {
            if (role.equals(r.role)) {
                return r;
            }
        }
        return CLIENT;
    }
}
