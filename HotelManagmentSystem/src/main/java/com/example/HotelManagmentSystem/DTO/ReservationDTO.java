package com.example.HotelManagmentSystem.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long reservationId;
    private Long userId;
    private Long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private Date bookingDate;
    private BigDecimal totalPrice;
    private String status;
}
