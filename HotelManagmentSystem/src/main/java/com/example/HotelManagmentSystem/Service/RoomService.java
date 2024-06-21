package com.example.HotelManagmentSystem.Service;

import com.example.HotelManagmentSystem.DTO.RoomDTO;
import com.example.HotelManagmentSystem.Entity.Hotel;
import com.example.HotelManagmentSystem.Entity.Room;
import com.example.HotelManagmentSystem.Mapper.RoomMapper;
import com.example.HotelManagmentSystem.Repository.HotelRepository;
import com.example.HotelManagmentSystem.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomMapper roomMapper;

    public RoomDTO createRoom(RoomDTO roomDTO) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(roomDTO.getHotelId());
        if (hotelOpt.isPresent()) {
            Room room = roomMapper.toEntity(roomDTO, hotelOpt.get());
            room.setStatus(Room.Status.AVAILABLE);
            room.setLastMaintenanceDate(null);
            room = roomRepository.save(room);
            return roomMapper.toDTO(room);
        } else {
            throw new IllegalArgumentException("Invalid hotel ID");
        }
    }

    public RoomDTO updateRoom(Long roomId, RoomDTO roomDTO) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        if (roomOpt.isPresent()) {
            Room room = roomOpt.get();
            Optional<Hotel> hotelOpt = hotelRepository.findById(roomDTO.getHotelId());
            if (hotelOpt.isPresent()) {
                room.setHotel(hotelOpt.get());
                try {
                    room.setStatus(Room.Status.valueOf(roomDTO.getStatus()));
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid status value: " + roomDTO.getStatus());
                }
                room.setDetails(roomDTO.getDetails());
                room.setPrice(roomDTO.getPrice());
                room.setFacilities(roomDTO.getFacilities());
                room.setCapacity(roomDTO.getCapacity());
                room.setSize(roomDTO.getSize());
                room.setFeatures(roomDTO.getFeatures());
                room.setLastMaintenanceDate(roomDTO.getLastMaintenanceDate() != null ? java.sql.Date.valueOf(roomDTO.getLastMaintenanceDate()) : null);
                room = roomRepository.save(room);
                return roomMapper.toDTO(room);
            } else {
                throw new IllegalArgumentException("Invalid hotel ID");
            }
        }
        return null;
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    public RoomDTO getRoomById(Long roomId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        return roomOpt.map(roomMapper::toDTO).orElse(null);
    }

    public List<RoomDTO> getRoomsByHotelId(Long hotelId) {
        List<Room> rooms = roomRepository.findByHotel_HotelId(hotelId);
        return rooms.stream().map(roomMapper::toDTO).toList();
    }

    public List<RoomDTO> getRoomsByStatus(String status) {
        List<Room> rooms = roomRepository.findByStatus(Room.Status.valueOf(status));
        return rooms.stream().map(roomMapper::toDTO).toList();
    }

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(roomMapper::toDTO).toList();
    }
}
