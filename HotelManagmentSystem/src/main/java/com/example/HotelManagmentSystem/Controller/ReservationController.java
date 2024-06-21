package com.example.HotelManagmentSystem.Controller;

import com.example.HotelManagmentSystem.DTO.ReservationDTO;
import com.example.HotelManagmentSystem.Entity.Reservation;
import com.example.HotelManagmentSystem.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER:create')")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/{reservationId}")
    @PreAuthorize("hasAnyAuthority('admin:update','CUSTOMER:update')")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long reservationId, @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(reservationId, reservationDTO);
        if (updatedReservation != null) {
            return ResponseEntity.ok(updatedReservation);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{reservationId}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        if (reservationService.deleteReservation(reservationId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{reservationId}")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long reservationId) {
        ReservationDTO reservation = reservationService.getReservationById(reservationId);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUserId(@PathVariable Long userId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/room/{roomId}")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public ResponseEntity<List<ReservationDTO>> getReservationsByRoomId(@PathVariable Long roomId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByRoomId(roomId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<List<ReservationDTO>> getReservationsByStatus(@PathVariable Reservation.Status status) {
        List<ReservationDTO> reservations = reservationService.getReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }
}

