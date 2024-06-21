package com.example.HotelManagmentSystem.Controller;

import com.example.HotelManagmentSystem.DTO.RoomDTO;
import com.example.HotelManagmentSystem.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create')")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO createdRoom = roomService.createRoom(roomDTO);
        return ResponseEntity.ok(createdRoom);
    }

    @PutMapping("/{roomId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long roomId, @RequestBody RoomDTO roomDTO) {
        RoomDTO updatedRoom = roomService.updateRoom(roomId, roomDTO);
        if (updatedRoom != null) {
            return ResponseEntity.ok(updatedRoom);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{roomId}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{roomId}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long roomId) {
        RoomDTO room = roomService.getRoomById(roomId);
        if (room != null) {
            return ResponseEntity.ok(room);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/hotel/{hotelId}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<List<RoomDTO>> getRoomsByHotelId(@PathVariable Long hotelId) {
        List<RoomDTO> rooms = roomService.getRoomsByHotelId(hotelId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<List<RoomDTO>> getRoomsByStatus(@PathVariable String status) {
        List<RoomDTO> rooms = roomService.getRoomsByStatus(status);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
}


