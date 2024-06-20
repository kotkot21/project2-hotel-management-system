package com.example.HotelManagmentSystem.Service;

import com.example.HotelManagmentSystem.DTO.HotelDTO;
import com.example.HotelManagmentSystem.DTO.UserDTO;
import com.example.HotelManagmentSystem.Entity.Hotel;
import com.example.HotelManagmentSystem.Entity.User;
import com.example.HotelManagmentSystem.Mapper.UserMapper;
import com.example.HotelManagmentSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userMapper.toDTO(userOpt.get());
        }
        return null;
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setPassword(userDTO.getPassword()); // Ensure password is updated if provided
            user = userRepository.save(user);
            return userMapper.toDTO(user);
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTOList(users);
    }
}
