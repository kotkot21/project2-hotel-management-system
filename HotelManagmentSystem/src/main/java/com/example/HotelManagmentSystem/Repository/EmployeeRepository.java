package com.example.HotelManagmentSystem.Repository;

import com.example.HotelManagmentSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByHotel_HotelId(Long hotelId);
    List<Employee> findByStatus(Employee.Status status);
}

