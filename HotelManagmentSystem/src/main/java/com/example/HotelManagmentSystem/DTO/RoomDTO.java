package com.example.HotelManagmentSystem.DTO;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long roomId;
    private Long hotelId;
    private String details;
    private Double price;
    private String facilities;
    private Integer capacity;
    private Double size;
    private String features;
    private String lastMaintenanceDate;
    private String status;
}
