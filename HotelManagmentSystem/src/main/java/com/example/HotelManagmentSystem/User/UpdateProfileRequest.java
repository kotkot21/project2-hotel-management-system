package com.example.HotelManagmentSystem.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {
    private String firstname;
    private String lastname;
    private String email;
}
