package com.udacity.jdnd.course3.critter.entitiy;

import org.checkerframework.checker.index.qual.GTENegativeOne;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<Employee> employees;

    @ManyToMany
    private List<Pet> pets;

    private LocalDate scheduleDate;

    @ManyToMany
    private Set<Activity> activities;

}
