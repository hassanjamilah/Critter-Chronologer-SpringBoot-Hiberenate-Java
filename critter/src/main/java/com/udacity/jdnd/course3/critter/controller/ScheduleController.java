package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entitiy.Activity;
import com.udacity.jdnd.course3.critter.entitiy.Employee;
import com.udacity.jdnd.course3.critter.entitiy.Pet;
import com.udacity.jdnd.course3.critter.entitiy.Schedule;
import com.udacity.jdnd.course3.critter.entitiy.types.EmployeeSkill;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.view.ScheduleDTO;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertSchedultDTOToSchedule(scheduleDTO);
        scheduleService.createSchedule(schedule);
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> allSchedules = scheduleService.getAllSchedules();
        return convertScheduleListToScheduleDTOList(allSchedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) throws NotFoundException {
        List<Schedule> schedules = scheduleService.findScheduleByPet(petId);
        return convertScheduleListToScheduleDTOList( schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) throws NotFoundException {
        List<Schedule> schedules = scheduleService.findScheduleByEmployee(employeeId);
        return convertScheduleListToScheduleDTOList(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) throws NotFoundException {
        List<Schedule> schedules = scheduleService.findScheduleByOwner(customerId);
        return convertScheduleListToScheduleDTOList(schedules);
    }

    public ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setEmployeeIds(new ArrayList<>());
        for (Employee emp:
             schedule.getEmployees()) {
            scheduleDTO.getEmployeeIds().add(emp.getId());
        }

        scheduleDTO.setPetIds(new ArrayList<>());
        for (Pet pet:
                schedule.getPets()) {
            scheduleDTO.getPetIds().add(pet.getId());
        }

        scheduleDTO.setActivities(new HashSet<>());
        Set<Activity> activities = schedule.getActivities();
        for (Activity activity:
                activities) {

            scheduleDTO.getPetIds().add(activity.getId()-1);
        }

        BeanUtils.copyProperties(schedule, scheduleDTO, "scheduleDate");
        scheduleDTO.setDate(schedule.getScheduleDate());
        return scheduleDTO;
    }

    public Schedule convertSchedultDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

    public List<ScheduleDTO> convertScheduleListToScheduleDTOList(List<Schedule> schedules){
        List<ScheduleDTO> scheduleDTOs= new ArrayList<>();
        for (Schedule sched:
             schedules) {
            scheduleDTOs.add(convertScheduleToScheduleDTO(sched));
        }
        return scheduleDTOs;
    }
}
