package com.example.HotelManagmentSystem.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileRequest {
    private String firstname;
    private String lastname;
    private String email;
    // Add other fields as necessary
}
