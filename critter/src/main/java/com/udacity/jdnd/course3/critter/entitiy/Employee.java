package com.udacity.jdnd.course3.critter.entitiy;

import com.udacity.jdnd.course3.critter.entitiy.types.EmployeeSkill;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized
    private String name;

    @ManyToMany
    private Set<Activity> skills;

    @ManyToMany
    private Set<WeekDays> workingDays;
}
