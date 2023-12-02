package com.livrariamabuko.Livraria.Mabuko.model;

public enum UserRole {
    ROLE_ADMIN("ADMIN"),

    ROLE_EMPLOYEE("Client");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    
}
