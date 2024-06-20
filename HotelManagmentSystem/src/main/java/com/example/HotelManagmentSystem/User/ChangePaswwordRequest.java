package com.example.HotelManagmentSystem.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePaswwordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
