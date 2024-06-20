package com.example.HotelManagmentSystem.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HousekeepingScheduleDTO {
    private Long scheduleId;
    private Long roomId;
    private Long employeeId;
    private Date date;
    private String status;
}
