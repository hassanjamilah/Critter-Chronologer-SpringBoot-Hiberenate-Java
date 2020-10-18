package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitiy.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: 18/10/2020 add @Transactional

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    // TODO: 18/10/2020
    public Long savePit(Pet pet){
        return 0L;
    }

    public List<Pet> getAllPets(){
        return null;
    }

    public List<Pet> getPetsByOwner(){
        return null;
    }



}
