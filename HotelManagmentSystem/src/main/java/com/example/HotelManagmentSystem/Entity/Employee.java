package com.example.HotelManagmentSystem.Entity;

import com.example.HotelManagmentSystem.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "managed_by_admin_id", nullable = false)
    private User admin;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Double salary;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Role {
        MANAGER, RECEPTIONIST, HOUSEKEEPER
    }

    public enum Status {
        ACTIVE, TERMINATED
    }
}
