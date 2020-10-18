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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Activity> getSkills() {
        return skills;
    }

    public void setSkills(Set<Activity> skills) {
        this.skills = skills;
    }

    public Set<WeekDays> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Set<WeekDays> workingDays) {
        this.workingDays = workingDays;
    }
}
