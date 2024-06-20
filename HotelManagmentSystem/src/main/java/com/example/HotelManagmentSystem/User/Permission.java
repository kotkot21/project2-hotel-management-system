package com.example.HotelManagmentSystem.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    Employee_READ("Employee:read"),
    Employee_UPDATE("Employee:update"),
    Employee_CREATE("Employee:create"),
    Employee_DELETE("Employee:delete"),
    USER_READ("User:read"),
    USER_UPDATE("User:update"),
    USER_DELETE("User:delete"),
    USER_CREATE("User:create")
    ;

    @Getter
    private final String permission;
}

