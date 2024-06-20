package com.example.HotelManagmentSystem.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CUSTOMER_READ("CUSTOMER:read"),
    CUSTOMER_UPDATE("CUSTOMER:update"),
    CUSTOMER_DELETE("CUSTOMER:delete"),
    CUSTOMER_CREATE("CUSTOMER:create")
    ;

    private final String permission;
}

