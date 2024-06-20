package com.example.HotelManagmentSystem.Service;

import com.example.HotelManagmentSystem.DTO.HousekeepingScheduleDTO;
import com.example.HotelManagmentSystem.Entity.Employee;
import com.example.HotelManagmentSystem.Entity.HousekeepingSchedule;
import com.example.HotelManagmentSystem.Entity.Room;
import com.example.HotelManagmentSystem.Mapper.HousekeepingScheduleMapper;
import com.example.HotelManagmentSystem.Repository.EmployeeRepository;
import com.example.HotelManagmentSystem.Repository.HousekeepingScheduleRepository;
import com.example.HotelManagmentSystem.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HousekeepingScheduleService {

    @Autowired
    private HousekeepingScheduleRepository housekeepingScheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HousekeepingScheduleMapper housekeepingScheduleMapper;

    public HousekeepingScheduleDTO createSchedule(HousekeepingScheduleDTO housekeepingScheduleDTO) {
        Optional<Room> roomOptional = roomRepository.findById(housekeepingScheduleDTO.getRoomId());
        Optional<Employee> employeeOptional = employeeRepository.findById(housekeepingScheduleDTO.getEmployeeId());

        if (roomOptional.isPresent() && employeeOptional.isPresent()) {
            Room room = roomOptional.get();
            Employee employee = employeeOptional.get();
            HousekeepingSchedule housekeepingSchedule = housekeepingScheduleMapper.toEntity(housekeepingScheduleDTO, room, employee);
            HousekeepingSchedule savedSchedule = housekeepingScheduleRepository.save(housekeepingSchedule);
            return housekeepingScheduleMapper.toDTO(savedSchedule);
        } else {
            throw new IllegalArgumentException("Room or Employee not found");
        }
    }

    public HousekeepingScheduleDTO updateSchedule(Long scheduleId, HousekeepingScheduleDTO housekeepingScheduleDTO) {
        Optional<HousekeepingSchedule> existingScheduleOptional = housekeepingScheduleRepository.findById(scheduleId);
        if (existingScheduleOptional.isPresent()) {
            HousekeepingSchedule existingSchedule = existingScheduleOptional.get();
            existingSchedule.setDate(housekeepingScheduleDTO.getDate());
            existingSchedule.setStatus(HousekeepingSchedule.Status.valueOf(housekeepingScheduleDTO.getStatus()));
            HousekeepingSchedule updatedSchedule = housekeepingScheduleRepository.save(existingSchedule);
            return housekeepingScheduleMapper.toDTO(updatedSchedule);
        } else {
            throw new IllegalArgumentException("Schedule not found");
        }
    }

    public boolean deleteSchedule(Long scheduleId) {
        if (housekeepingScheduleRepository.existsById(scheduleId)) {
            housekeepingScheduleRepository.deleteById(scheduleId);
            return true;
        }
        return false;
    }

    public HousekeepingScheduleDTO getScheduleById(Long scheduleId) {
        Optional<HousekeepingSchedule> scheduleOptional = housekeepingScheduleRepository.findById(scheduleId);
        return scheduleOptional.map(housekeepingScheduleMapper::toDTO).orElse(null);
    }

    public List<HousekeepingScheduleDTO> getAllSchedules() {
        List<HousekeepingSchedule> schedules = housekeepingScheduleRepository.findAll();
        return housekeepingScheduleMapper.toDTOList(schedules);
    }

    public List<HousekeepingScheduleDTO> getSchedulesByRoomId(Long roomId) {
        List<HousekeepingSchedule> schedules = housekeepingScheduleRepository.findByRoom_RoomId(roomId);
        return housekeepingScheduleMapper.toDTOList(schedules);
    }

    public List<HousekeepingScheduleDTO> getSchedulesByEmployeeId(Long employeeId) {
        List<HousekeepingSchedule> schedules = housekeepingScheduleRepository.findByEmployee_EmployeeId(employeeId);
        return housekeepingScheduleMapper.toDTOList(schedules);
    }

    public List<HousekeepingScheduleDTO> getSchedulesByStatus(HousekeepingSchedule.Status status) {
        List<HousekeepingSchedule> schedules = housekeepingScheduleRepository.findByStatus(status);
        return housekeepingScheduleMapper.toDTOList(schedules);
    }
}
