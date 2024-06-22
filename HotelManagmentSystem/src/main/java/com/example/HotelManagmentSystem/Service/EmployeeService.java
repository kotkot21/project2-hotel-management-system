package com.example.HotelManagmentSystem.Service;

import com.example.HotelManagmentSystem.DTO.EmployeeDTO;
import com.example.HotelManagmentSystem.Entity.Employee;
import com.example.HotelManagmentSystem.Entity.Hotel;
import com.example.HotelManagmentSystem.User.User;
import com.example.HotelManagmentSystem.Mapper.EmployeeMapper;
import com.example.HotelManagmentSystem.Repository.EmployeeRepository;
import com.example.HotelManagmentSystem.Repository.HotelRepository;
import com.example.HotelManagmentSystem.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeMapper employeeMapper;


    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(employeeDTO.getHotelId());
        Optional<User> adminOptional = userRepository.findById(employeeDTO.getManagedByAdminId());

        if (hotelOptional.isPresent() && adminOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            User admin = adminOptional.get();
            Employee employee = employeeMapper.toEntity(employeeDTO, hotel, admin);
            Employee savedEmployee = employeeRepository.save(employee);
            return employeeMapper.toDTO(savedEmployee);
        } else {
            throw new IllegalArgumentException("Hotel or Admin not found");
        }
    }

    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById(employeeId);
        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setRole(Employee.Role.valueOf(employeeDTO.getRole()));
            existingEmployee.setSalary(employeeDTO.getSalary());
            existingEmployee.setStartDate(LocalDate.parse(employeeDTO.getStartDate()));
            existingEmployee.setEndDate(employeeDTO.getEndDate() != null ? LocalDate.parse(employeeDTO.getEndDate()) : null);
            existingEmployee.setStatus(Employee.Status.valueOf(employeeDTO.getStatus()));
            if (!existingEmployee.getAdmin().getId().equals(employeeDTO.getManagedByAdminId())) {
                Optional<User> admin = userRepository.findById(employeeDTO.getManagedByAdminId());
                if (admin.isPresent()) {
                    existingEmployee.setAdmin(admin.get());
                } else {
                    throw new IllegalArgumentException("Admin not found");
                }
            }
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return employeeMapper.toDTO(updatedEmployee);
        } else {
            throw new IllegalArgumentException("Employee not found");
        }
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public EmployeeDTO getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return employeeMapper.toDTO(employee.get());
        } else {
            throw new IllegalArgumentException("Employee not found");
        }
    }

    public List<EmployeeDTO> getEmployeesByHotelId(Long hotelId) {
        List<Employee> employees = employeeRepository.findByHotel_HotelId(hotelId);
        return employees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesByStatus(String status) {
        List<Employee> employees = employeeRepository.findByStatus(Employee.Status.valueOf(status));
        return employees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }

    public EmployeeDTO terminateEmployee(Long employeeId) {
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById(employeeId);
        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setStatus(Employee.Status.TERMINATED);
            existingEmployee.setEndDate(LocalDate.now());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return employeeMapper.toDTO(updatedEmployee);
        } else {
            throw new IllegalArgumentException("Employee not found");
        }
    }

    public EmployeeDTO activateEmployee(Long employeeId) {
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById(employeeId);
        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setStatus(Employee.Status.ACTIVE);
            existingEmployee.setStartDate(LocalDate.now());
            existingEmployee.setEndDate(null);
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return employeeMapper.toDTO(updatedEmployee);
        } else {
            throw new IllegalArgumentException("Employee not found");
        }
    }

    public EmployeeDTO changeEmployeeRole(Long employeeId, String newRole) {
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById(employeeId);
        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setRole(Employee.Role.valueOf(newRole));
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return employeeMapper.toDTO(updatedEmployee);
        } else {
            throw new IllegalArgumentException("Employee not found");
        }
    }
}
