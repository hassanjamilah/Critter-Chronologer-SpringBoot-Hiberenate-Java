package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entitiy.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
