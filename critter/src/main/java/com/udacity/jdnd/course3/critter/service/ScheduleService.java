package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitiy.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: 18/10/2020 add @Transactional 

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Long createSchedule(Schedule schedule) {
        return 0L;
    }

    public Schedule findScheduleByPet(Long petID){
        return null;
    }

    public Schedule findScheduleByEmployee(Long employeeID){
        return null;
    }

    public Schedule findScheduleByOwner(Long customerID){
        return null;
    }
}
