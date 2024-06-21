package com.example.HotelManagmentSystem.Controller;

import com.example.HotelManagmentSystem.DTO.HousekeepingScheduleDTO;
import com.example.HotelManagmentSystem.Entity.HousekeepingSchedule;
import com.example.HotelManagmentSystem.Service.HousekeepingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housekeeping-schedules")
@PreAuthorize("hasAnyRole('ADMIN')")
public class HousekeepingScheduleController {

    @Autowired
    private HousekeepingScheduleService housekeepingScheduleService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<HousekeepingScheduleDTO> createSchedule(@RequestBody HousekeepingScheduleDTO housekeepingScheduleDTO) {
        HousekeepingScheduleDTO createdSchedule = housekeepingScheduleService.createSchedule(housekeepingScheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }

    @PutMapping("/{scheduleId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<HousekeepingScheduleDTO> updateSchedule(@PathVariable Long scheduleId, @RequestBody HousekeepingScheduleDTO housekeepingScheduleDTO) {
        HousekeepingScheduleDTO updatedSchedule = housekeepingScheduleService.updateSchedule(scheduleId, housekeepingScheduleDTO);
        if (updatedSchedule != null) {
            return ResponseEntity.ok(updatedSchedule);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/status/{roomId}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<HousekeepingScheduleDTO> updateStatusByRoomId(@PathVariable Long roomId, @RequestParam HousekeepingSchedule.Status newStatus) {
        HousekeepingScheduleDTO updatedSchedule = housekeepingScheduleService.updateStatusByRoomId(roomId, newStatus);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{scheduleId}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        if (housekeepingScheduleService.deleteSchedule(scheduleId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{scheduleId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<HousekeepingScheduleDTO> getScheduleById(@PathVariable Long scheduleId) {
        HousekeepingScheduleDTO schedule = housekeepingScheduleService.getScheduleById(scheduleId);
        if (schedule != null) {
            return ResponseEntity.ok(schedule);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<HousekeepingScheduleDTO>> getAllSchedules() {
        List<HousekeepingScheduleDTO> schedules = housekeepingScheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/room/{roomId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<HousekeepingScheduleDTO>> getSchedulesByRoomId(@PathVariable Long roomId) {
        List<HousekeepingScheduleDTO> schedules = housekeepingScheduleService.getSchedulesByRoomId(roomId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<HousekeepingScheduleDTO>> getSchedulesByEmployeeId(@PathVariable Long employeeId) {
        List<HousekeepingScheduleDTO> schedules = housekeepingScheduleService.getSchedulesByEmployeeId(employeeId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<HousekeepingScheduleDTO>> getSchedulesByStatus(@PathVariable HousekeepingSchedule.Status status) {
        List<HousekeepingScheduleDTO> schedules = housekeepingScheduleService.getSchedulesByStatus(status);
        return ResponseEntity.ok(schedules);
    }
}
