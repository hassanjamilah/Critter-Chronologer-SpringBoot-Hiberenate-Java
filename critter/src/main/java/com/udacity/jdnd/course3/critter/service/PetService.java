package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitiy.Customer;
import com.udacity.jdnd.course3.critter.entitiy.Pet;
import com.udacity.jdnd.course3.critter.entitiy.types.PetType;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

// TODO: 18/10/2020 add @Transactional

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    // TODO: 18/10/2020
    public Long savePit(Pet pet){

        Pet newPet = petRepository.save(pet);
        Customer customer = newPet.getOwnerId();
        customer.addPet(newPet);
        customerRepository.save(customer);
        return newPet.getId();
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long ownerID){
        Customer customer = customerRepository.findById(ownerID).get();
       // List<Pet> pets = petRepository.getAllMy(ownerID);
        List<Pet> pets1 = petRepository.findAll();
        List<Pet> pets = petRepository.findAllByOwnerId(customer);
        return pets;

    }

    public void addPetToCustomer(Long petID, Long customerID) throws NotFoundException {
        Optional<Pet> optionalPet = petRepository.findById(petID);
        if (optionalPet.isPresent()){
            Customer customer = customerService.getCustomerByID(customerID);
            Pet pet = optionalPet.get();
            pet.setOwnerId(customer);
        }
        throw new NotFoundException("Pet not found");
    }

    public Pet getPetByID(Long petID) throws NotFoundException {
        Optional<Pet> optionalPet = petRepository.findById(petID);
        if (optionalPet.isPresent()){
            return optionalPet.get();
        }
        throw new NotFoundException("Pet Not found for id: " + petID);
    }



}
