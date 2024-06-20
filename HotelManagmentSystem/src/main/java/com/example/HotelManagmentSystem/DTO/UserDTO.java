package com.example.HotelManagmentSystem.DTO;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
