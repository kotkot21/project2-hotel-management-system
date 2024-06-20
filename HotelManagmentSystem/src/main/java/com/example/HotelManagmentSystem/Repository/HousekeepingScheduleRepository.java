package com.example.HotelManagmentSystem.Repository;

import com.example.HotelManagmentSystem.Entity.HousekeepingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousekeepingScheduleRepository extends JpaRepository<HousekeepingSchedule, Long> {
    List<HousekeepingSchedule> findByRoom_RoomId(Long roomId);
    List<HousekeepingSchedule> findByEmployee_EmployeeId(Long employeeId);
    List<HousekeepingSchedule> findByStatus(HousekeepingSchedule.Status status);
}
