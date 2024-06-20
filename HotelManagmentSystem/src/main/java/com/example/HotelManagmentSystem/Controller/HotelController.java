package com.example.HotelManagmentSystem.Controller;

import com.example.HotelManagmentSystem.DTO.HotelDTO;
import com.example.HotelManagmentSystem.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create')")
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        HotelDTO createdHotel = hotelService.createHotel(hotelDTO);
        return ResponseEntity.ok(createdHotel);
    }

    @PutMapping("/{hotelId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long hotelId, @RequestBody HotelDTO hotelDTO) {
        HotelDTO updatedHotel = hotelService.updateHotel(hotelId, hotelDTO);
        if (updatedHotel != null) {
            return ResponseEntity.ok(updatedHotel);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{hotelId}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
        if (hotelService.deleteHotel(hotelId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{hotelId}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long hotelId) {
        HotelDTO hotel = hotelService.getHotelById(hotelId);
        if (hotel != null) {
            return ResponseEntity.ok(hotel);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'CUSTOMER:read')")
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
}
