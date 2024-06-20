package com.example.HotelManagmentSystem.Repository;

import com.example.HotelManagmentSystem.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotel_HotelId(Long hotelId);
    List<Room> findByStatus(Room.Status status);
}
