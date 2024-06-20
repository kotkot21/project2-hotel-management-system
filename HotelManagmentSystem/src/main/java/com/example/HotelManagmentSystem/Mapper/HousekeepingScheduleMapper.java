package com.example.HotelManagmentSystem.Mapper;

import com.example.HotelManagmentSystem.DTO.HousekeepingScheduleDTO;
import com.example.HotelManagmentSystem.Entity.Employee;
import com.example.HotelManagmentSystem.Entity.HousekeepingSchedule;
import com.example.HotelManagmentSystem.Entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HousekeepingScheduleMapper {

    public HousekeepingSchedule toEntity(HousekeepingScheduleDTO housekeepingScheduleDTO, Room room, Employee employee) {
        HousekeepingSchedule housekeepingSchedule = new HousekeepingSchedule();
        housekeepingSchedule.setScheduleId(housekeepingScheduleDTO.getScheduleId());
        housekeepingSchedule.setRoom(room);
        housekeepingSchedule.setEmployee(employee);
        housekeepingSchedule.setDate(housekeepingScheduleDTO.getDate());
        housekeepingSchedule.setStatus(HousekeepingSchedule.Status.valueOf(housekeepingScheduleDTO.getStatus()));
        return housekeepingSchedule;
    }

    public HousekeepingScheduleDTO toDTO(HousekeepingSchedule housekeepingSchedule) {
        HousekeepingScheduleDTO housekeepingScheduleDTO = new HousekeepingScheduleDTO();
        housekeepingScheduleDTO.setScheduleId(housekeepingSchedule.getScheduleId());
        housekeepingScheduleDTO.setRoomId(housekeepingSchedule.getRoom().getRoomId());
        housekeepingScheduleDTO.setEmployeeId(housekeepingSchedule.getEmployee().getEmployeeId());
        housekeepingScheduleDTO.setDate(housekeepingSchedule.getDate());
        housekeepingScheduleDTO.setStatus(housekeepingSchedule.getStatus().name());
        return housekeepingScheduleDTO;
    }

    public List<HousekeepingScheduleDTO> toDTOList(List<HousekeepingSchedule> schedules) {
        return schedules.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
