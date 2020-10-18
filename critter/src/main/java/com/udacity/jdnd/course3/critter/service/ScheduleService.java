package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitiy.Employee;
import com.udacity.jdnd.course3.critter.entitiy.Pet;
import com.udacity.jdnd.course3.critter.entitiy.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: 18/10/2020 add @Transactional 

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;


    public Long createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        return schedule.getId();
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleByPet(Long petID) throws NotFoundException {
        Pet pet = petService.getPetByID(petID);
        List<Schedule> all = scheduleRepository.findShedulesByPetID(pet);
        return all;
    }

    public List<Schedule> findScheduleByEmployee(Long employeeID) throws NotFoundException {
        Employee employee = employeeService.getEmployeeByID(employeeID);
        List<Schedule> schedules = scheduleRepository.findSchedulesByEmployee(employee);
        return schedules;
    }

    public Schedule findScheduleByOwner(Long customerID){
        return null;
    }
}
