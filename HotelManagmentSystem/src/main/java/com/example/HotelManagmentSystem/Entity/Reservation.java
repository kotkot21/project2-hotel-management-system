package com.example.HotelManagmentSystem.Entity;

import com.example.HotelManagmentSystem.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Temporal(TemporalType.DATE)
    private Date checkInDate;

    @Temporal(TemporalType.DATE)
    private Date checkOutDate;

    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        CONFIRMED, CANCELLED
    }
}
