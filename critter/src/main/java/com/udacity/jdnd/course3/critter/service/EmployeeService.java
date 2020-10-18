package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitiy.Activity;
import com.udacity.jdnd.course3.critter.entitiy.Employee;
import com.udacity.jdnd.course3.critter.entitiy.Schedule;
import com.udacity.jdnd.course3.critter.entitiy.WeekDays;
import com.udacity.jdnd.course3.critter.entitiy.types.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;


// TODO: 18/10/2020 add @Transactional


@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Long saveEmployee(Employee employee){
        Set<Activity> kills  = employee.getSkills();
        employeeRepository.save(employee);
        return  employee.getId();

    }

    public Employee getEmployeeByID(Long employeeID) throws NotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeID);
        if (optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        throw new NotFoundException("Employee not found. ID: " + employeeID);
    }

    public void addEmployeeShedule(Employee employee, Schedule schedule){

    }

    public List<Employee> checkAvailability(List<Activity> skills, LocalDate forDate) throws ParseException {

        List<Employee> finalResult = new ArrayList<>();
        List<Long> compareList = getIDsListFromObjects(skills);
        DayOfWeek day = forDate.getDayOfWeek();
        int x  = day.ordinal()+1;
        List<Employee> allAvailable = employeeRepository.findAllByWorkingDaysContains(new WeekDays((long) day.ordinal()+1));

        for (Employee emp:
             allAvailable) {
            List<Long> empActsIDs = getIDsListFromObjects(emp.getSkills());
            if (empActsIDs.containsAll(compareList)){
                finalResult.add(emp);
            }

        }
        //Employee emp = employeeRepository.findBySkillsContainingAndIdAndWorkingDaysContaining(act1, 1L, day1);
        //Employee emp1 = employeeRepository.findBySkillsIn(acts);
        //Employee emp2 = employeeRepository.findBySkillsIn(acts);
        return finalResult;
    }

    private List<Long> getIDsListFromObjects(Collection<Activity> activities){
        List<Long> result =new ArrayList<>();
        for (Activity act:
             activities) {
            result.add(act.getId());
        }
        return  result;
    }




//    public Set<DayOfWeek> getRestOfDays(Set<DayOfWeek> currentDays){
//        Set<DayOfWeek> resultDays = new HashSet<>();
//        for (DayOfWeek day:
//             DayOfWeek.values()) {
//            if (currentDays.add(day)){
//                resultDays.add(day);
//            }
//        }
//        return resultDays;
//    }

}
