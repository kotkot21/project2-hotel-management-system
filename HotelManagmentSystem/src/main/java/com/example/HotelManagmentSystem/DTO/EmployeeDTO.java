package com.example.HotelManagmentSystem.DTO;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private Long hotelId;
    private Long managedByAdminId;
    private String role;
    private Double salary;
    private String startDate;
    private String endDate;
    private String status;
}
