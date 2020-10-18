package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entitiy.Employee;
import com.udacity.jdnd.course3.critter.entitiy.Pet;
import com.udacity.jdnd.course3.critter.entitiy.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s where :pet member of s.pets ")
    List<Schedule> findShedulesByPetID(@Param("pet") Pet pet);

    @Query("select s from Schedule s where :respositry member of s.employees")
    List<Schedule> findSchedulesByEmployee(@Param("respositry")Employee employee);

}
