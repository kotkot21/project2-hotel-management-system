package com.example.HotelManagmentSystem.Mapper;

import com.example.HotelManagmentSystem.DTO.RoomDTO;
import com.example.HotelManagmentSystem.Entity.Hotel;
import com.example.HotelManagmentSystem.Entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public Room toEntity(RoomDTO roomDTO, Hotel hotel) {
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setHotel(hotel);
        room.setStatus(Room.Status.valueOf(roomDTO.getStatus()));
        room.setDetails(roomDTO.getDetails());
        room.setPrice(roomDTO.getPrice());
        room.setFacilities(roomDTO.getFacilities());
        room.setCapacity(roomDTO.getCapacity());
        room.setSize(roomDTO.getSize());
        room.setFeatures(roomDTO.getFeatures());
        room.setLastMaintenanceDate(roomDTO.getLastMaintenanceDate() != null ? java.sql.Date.valueOf(roomDTO.getLastMaintenanceDate()) : null);
        return room;
    }

    public RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setHotelId(room.getHotel().getHotelId());
        roomDTO.setStatus(room.getStatus().name());
        roomDTO.setDetails(room.getDetails());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setFacilities(room.getFacilities());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setSize(room.getSize());
        roomDTO.setFeatures(room.getFeatures());
        roomDTO.setLastMaintenanceDate(room.getLastMaintenanceDate() != null ? room.getLastMaintenanceDate().toString() : null);
        return roomDTO;
    }
}

