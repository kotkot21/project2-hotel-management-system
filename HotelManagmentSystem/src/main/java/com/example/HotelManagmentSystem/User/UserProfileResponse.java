package com.example.HotelManagmentSystem.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfileResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    // Add other fields as necessary
}
