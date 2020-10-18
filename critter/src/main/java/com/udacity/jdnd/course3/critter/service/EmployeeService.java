package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitiy.Employee;
import com.udacity.jdnd.course3.critter.entitiy.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// TODO: 18/10/2020 add @Transactional


@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Long saveEmployee(Employee employee){
        return 0L;
    }

    public void addEmployeeShedule(Employee employee, Schedule schedule){

    }

    public boolean checkAvailability(Employee employee){
        return false;
    }

}
