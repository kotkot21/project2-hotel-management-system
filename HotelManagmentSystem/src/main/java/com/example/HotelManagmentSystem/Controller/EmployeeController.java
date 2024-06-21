package com.example.HotelManagmentSystem.Controller;

import com.example.HotelManagmentSystem.ChangeRole.ChangeRoleRequest;
import com.example.HotelManagmentSystem.DTO.EmployeeDTO;
import com.example.HotelManagmentSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@PreAuthorize("hasAnyRole('ADMIN')")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }


    @PutMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, employeeDTO);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/hotel/{hotelId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByHotelId(@PathVariable Long hotelId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByHotelId(hotelId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByStatus(@PathVariable String status) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByStatus(status);
        return ResponseEntity.ok(employees);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/terminate/{employeeId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<EmployeeDTO> terminateEmployee(@PathVariable Long employeeId) {
        EmployeeDTO terminatedEmployee = employeeService.terminateEmployee(employeeId);
        if (terminatedEmployee != null) {
            return ResponseEntity.ok(terminatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/activate/{employeeId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<EmployeeDTO> activateEmployee(@PathVariable Long employeeId) {
        EmployeeDTO updatedEmployee = employeeService.activateEmployee(employeeId);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/change-role/{employeeId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<EmployeeDTO> changeEmployeeRole(@PathVariable Long employeeId, @RequestBody ChangeRoleRequest changeRoleRequest) {
        EmployeeDTO updatedEmployee = employeeService.changeEmployeeRole(employeeId, changeRoleRequest.getNewRole());
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }
}
