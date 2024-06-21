package com.example.HotelManagmentSystem.Mapper;

import com.example.HotelManagmentSystem.DTO.ReservationDTO;
import com.example.HotelManagmentSystem.Entity.Reservation;
import com.example.HotelManagmentSystem.Entity.Room;
import com.example.HotelManagmentSystem.User.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationDTO reservationDTO, User user, Room room) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationDTO.getReservationId());
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setBookingDate(reservationDTO.getBookingDate());
        reservation.setTotalPrice(reservationDTO.getTotalPrice());
        reservation.setStatus(Reservation.Status.valueOf(reservationDTO.getStatus()));
        return reservation;
    }

    public ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setUserId(reservation.getUser().getId());
        reservationDTO.setRoomId(reservation.getRoom().getRoomId());
        reservationDTO.setCheckInDate(reservation.getCheckInDate());
        reservationDTO.setCheckOutDate(reservation.getCheckOutDate());
        reservationDTO.setBookingDate(reservation.getBookingDate());
        reservationDTO.setTotalPrice(reservation.getTotalPrice());
        reservationDTO.setStatus(reservation.getStatus().name());
        return reservationDTO;
    }

    public List<ReservationDTO> toDTOList(List<Reservation> reservations) {
        return reservations.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

