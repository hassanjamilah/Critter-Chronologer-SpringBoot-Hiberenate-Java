package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entitiy.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekDaysRepository extends JpaRepository<WeekDays, Long> {
}
