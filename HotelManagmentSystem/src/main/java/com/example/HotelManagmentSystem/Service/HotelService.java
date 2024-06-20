package com.example.HotelManagmentSystem.Service;

import com.example.HotelManagmentSystem.DTO.HotelDTO;
import com.example.HotelManagmentSystem.Entity.Hotel;
import com.example.HotelManagmentSystem.Mapper.HotelMapper;
import com.example.HotelManagmentSystem.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.toEntity(hotelDTO);
        hotel = hotelRepository.save(hotel);
        return hotelMapper.toDTO(hotel);
    }

    public HotelDTO updateHotel(Long hotelId, HotelDTO hotelDTO) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        if (hotelOpt.isPresent()) {
            Hotel hotel = hotelOpt.get();
            hotel.setName(hotelDTO.getName());
            hotel.setLocation(hotelDTO.getLocation());
            hotel.setContactNumber(hotelDTO.getContactNumber());
            hotel.setEmail(hotelDTO.getEmail());
            hotel.setRating(hotelDTO.getRating());
            hotel.setDescription(hotelDTO.getDescription());
            hotel = hotelRepository.save(hotel);
            return hotelMapper.toDTO(hotel);
        }
        return null;
    }

    public boolean deleteHotel(Long hotelId) {
        if (hotelRepository.existsById(hotelId)) {
            hotelRepository.deleteById(hotelId);
            return true;
        }
        return false;
    }

    public HotelDTO getHotelById(Long hotelId) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
        return hotelOpt.map(hotelMapper::toDTO).orElse(null);
    }

    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotelMapper.toDTOList(hotels);
    }
}
