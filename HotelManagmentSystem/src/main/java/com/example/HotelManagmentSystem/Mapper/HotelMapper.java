package com.example.HotelManagmentSystem.Mapper;

import com.example.HotelManagmentSystem.DTO.HotelDTO;
import com.example.HotelManagmentSystem.Entity.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    public Hotel toEntity(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelDTO.getHotelId());
        hotel.setName(hotelDTO.getName());
        hotel.setLocation(hotelDTO.getLocation());
        hotel.setContactNumber(hotelDTO.getContactNumber());
        hotel.setEmail(hotelDTO.getEmail());
        hotel.setRating(hotelDTO.getRating());
        hotel.setDescription(hotelDTO.getDescription());
        return hotel;
    }

    public HotelDTO toDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotelId(hotel.getHotelId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setLocation(hotel.getLocation());
        hotelDTO.setContactNumber(hotel.getContactNumber());
        hotelDTO.setEmail(hotel.getEmail());
        hotelDTO.setRating(hotel.getRating());
        hotelDTO.setDescription(hotel.getDescription());
        return hotelDTO;
    }

    public List<HotelDTO> toDTOList(List<Hotel> hotels) {
        return hotels.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
