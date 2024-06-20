package com.example.HotelManagmentSystem.Controller;

import com.example.HotelManagmentSystem.DTO.HousekeepingScheduleDTO;
import com.example.HotelManagmentSystem.Entity.HousekeepingSchedule;
import com.example.HotelManagmentSystem.Service.HousekeepingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housekeeping-schedules")
public class HousekeepingScheduleController {

    @Autowired
    private HousekeepingScheduleService housekeepingScheduleService;

    @PostMapping
    public ResponseEntity<HousekeepingScheduleDTO> createSchedule(@RequestBody HousekeepingScheduleDTO housekeepingScheduleDTO) {
        HousekeepingScheduleDTO createdSchedule = housekeepingScheduleService.createSchedule(housekeepingScheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<HousekeepingScheduleDTO> updateSchedule(@PathVariable Long scheduleId, @RequestBody HousekeepingScheduleDTO housekeepingScheduleDTO) {
        HousekeepingScheduleDTO updatedSchedule = housekeepingScheduleService.updateSchedule(scheduleId, housekeepingScheduleDTO);
        if (updatedSchedule != null) {
            return ResponseEntity.ok(updatedSchedule);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        if (housekeepingScheduleService.deleteSchedule(scheduleId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<HousekeepingScheduleDTO> getScheduleById(@PathVariable Long scheduleId) {
        HousekeepingScheduleDTO schedule = housekeepingScheduleService.getScheduleById(scheduleId);
        if (schedule != null) {
            return ResponseEntity.ok(schedule);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<HousekeepingScheduleDTO>> getAllSchedules() {
        List<HousekeepingScheduleDTO> schedules = housekeepingScheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<HousekeepingScheduleDTO>> getSchedulesByRoomId(@PathVariable Long roomId) {
        List<HousekeepingScheduleDTO> schedules = housekeepingScheduleService.getSchedulesByRoomId(roomId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<HousekeepingScheduleDTO>> getSchedulesByEmployeeId(@PathVariable Long employeeId) {
        List<HousekeepingScheduleDTO> schedules = housekeepingScheduleService.getSchedulesByEmployeeId(employeeId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<HousekeepingScheduleDTO>> getSchedulesByStatus(@PathVariable HousekeepingSchedule.Status status) {
        List<HousekeepingScheduleDTO> schedules = housekeepingScheduleService.getSchedulesByStatus(status);
        return ResponseEntity.ok(schedules);
    }
}
