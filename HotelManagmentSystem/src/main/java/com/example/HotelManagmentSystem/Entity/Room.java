package com.example.HotelManagmentSystem.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String details;
    private Double price;
    private String facilities;
    private Integer capacity;
    private Double size;
    private String features;

    @Temporal(TemporalType.DATE)
    private Date lastMaintenanceDate;

    public enum Status {
        AVAILABLE, BOOKED, MAINTENANCE
    }
}
