package com.example.HotelManagmentSystem.Repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
=======
import com.example.HotelManagmentSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
>>>>>>> 79484c1fd8182c331e865b6f896fa8daaeee2cd7
    Optional<User> findByEmail(String email);
}
