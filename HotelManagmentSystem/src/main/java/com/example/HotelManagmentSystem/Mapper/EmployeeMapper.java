package com.example.HotelManagmentSystem.Mapper;

import com.example.HotelManagmentSystem.DTO.EmployeeDTO;
import com.example.HotelManagmentSystem.Entity.Employee;
import com.example.HotelManagmentSystem.Entity.Hotel;
import com.example.HotelManagmentSystem.User.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDTO employeeDTO, Hotel hotel, User admin) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setHotel(hotel);
        employee.setAdmin(admin);
        employee.setRole(Employee.Role.valueOf(employeeDTO.getRole()));
        employee.setSalary(employeeDTO.getSalary());
        employee.setStartDate(LocalDate.parse(employeeDTO.getStartDate()));
        employee.setEndDate(employeeDTO.getEndDate() != null ? LocalDate.parse(employeeDTO.getEndDate()) : null);
        employee.setStatus(Employee.Status.valueOf(employeeDTO.getStatus()));
        return employee;
    }

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setHotelId(employee.getHotel().getHotelId());
        employeeDTO.setManagedByAdminId(employee.getAdmin().getId());
        employeeDTO.setRole(employee.getRole().name());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setStartDate(employee.getStartDate().toString());
        employeeDTO.setEndDate(employee.getEndDate() != null ? employee.getEndDate().toString() : null);
        employeeDTO.setStatus(employee.getStatus().name());
        return employeeDTO;
    }
}
