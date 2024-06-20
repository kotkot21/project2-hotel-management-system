package com.example.HotelManagmentSystem.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private Long hotelId;
    private String name;
    private String location;
    private String contactNumber;
    private String email;
    private BigDecimal rating;
    private String description;
}
