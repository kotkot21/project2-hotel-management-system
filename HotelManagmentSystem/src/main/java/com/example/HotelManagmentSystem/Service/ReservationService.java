package com.example.HotelManagmentSystem.Service;

import com.example.HotelManagmentSystem.DTO.ReservationDTO;
import com.example.HotelManagmentSystem.Entity.Reservation;
import com.example.HotelManagmentSystem.Entity.Room;
import com.example.HotelManagmentSystem.User.User;
import com.example.HotelManagmentSystem.Mapper.ReservationMapper;
import com.example.HotelManagmentSystem.Repository.ReservationRepository;
import com.example.HotelManagmentSystem.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationMapper reservationMapper;
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Optional<User> userOptional = userRepository.findById(reservationDTO.getUserId());
        Optional<Room> roomOptional = roomRepository.findById(reservationDTO.getRoomId());

        if (userOptional.isPresent() && roomOptional.isPresent()) {
            User user = userOptional.get();
            Room room = roomOptional.get();
            Reservation reservation = reservationMapper.toEntity(reservationDTO, user, room);
            Reservation savedReservation = reservationRepository.save(reservation);
            return reservationMapper.toDTO(savedReservation);
        } else {
            throw new IllegalArgumentException("User or Room not found");
        }
    }

    public ReservationDTO updateReservation(Long reservationId, ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setCheckInDate(reservationDTO.getCheckInDate());
            reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
            reservation.setBookingDate(reservationDTO.getBookingDate());
            reservation.setTotalPrice(reservationDTO.getTotalPrice());
            reservation.setStatus(Reservation.Status.valueOf(reservationDTO.getStatus()));
            reservation = reservationRepository.save(reservation);
            return reservationMapper.toDTO(reservation);
        }
        return null;
    }

    public boolean deleteReservation(Long reservationId) {
        if (reservationRepository.existsById(reservationId)) {
            reservationRepository.deleteById(reservationId);
            return true;
        }
        return false;
    }

    public ReservationDTO getReservationById(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        return reservationOpt.map(reservationMapper::toDTO).orElse(null);
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toDTOList(reservations);
    }

    public List<ReservationDTO> getReservationsByUserId(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUser_UserId(userId);
        return reservationMapper.toDTOList(reservations);
    }

    public List<ReservationDTO> getReservationsByRoomId(Long roomId) {
        List<Reservation> reservations = reservationRepository.findByRoom_RoomId(roomId);
        return reservationMapper.toDTOList(reservations);
    }

    public List<ReservationDTO> getReservationsByStatus(Reservation.Status status) {
        List<Reservation> reservations = reservationRepository.findByStatus(status);
        return reservationMapper.toDTOList(reservations);
    }
}
