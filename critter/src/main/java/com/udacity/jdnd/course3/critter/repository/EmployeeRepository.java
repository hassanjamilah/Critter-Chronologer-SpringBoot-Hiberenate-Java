package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entitiy.Activity;
import com.udacity.jdnd.course3.critter.entitiy.Employee;
import com.udacity.jdnd.course3.critter.entitiy.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //@Query("select e from Employee e")
    List<Employee> findAllByWorkingDaysContains(WeekDays day);

    List<Employee> findAllBySkillsContains(Activity skill);

    List<Employee> findAllByWorkingDaysContaining(WeekDays day);

//    Employee findBySkillsContainingAndIdAndWorkingDaysContaining(Activity act, Long empID,WeekDays day);
//
//
//    Employee findBySkillsIn(Set<Activity> skills);







}
