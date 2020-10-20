package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entitiy.Customer;
import com.udacity.jdnd.course3.critter.entitiy.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.view.PetDTO;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOTOPet(petDTO);
        Long newID = petService.savePit(pet);
        pet.setId(newID);
        return  converPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) throws NotFoundException {
        Pet pet = petService.getPetByID(petId);
        return converPetToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> allPets = petService.getAllPets();
        List<PetDTO> petDtos = converListPetsToListPetDTOs(allPets);

        return petDtos;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> allPets = petService.getPetsByOwner(ownerId);
        return converListPetsToListPetDTOs(allPets);
    }

    public Pet convertPetDTOTOPet(PetDTO petDTO)  {
        Pet pet  = new Pet();
        Customer customer = null;
        try {
            customer = customerService.getCustomerByID(petDTO.getOwnerId());
            pet.setOwnerId(customer);
            BeanUtils.copyProperties(petDTO, pet, "ownerId");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return pet;
    }

    public PetDTO converPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        petDTO.setOwnerId(pet.getOwnerId().getId());
        BeanUtils.copyProperties(pet, petDTO, "ownerId");
        return petDTO;
    }

    public List<PetDTO> converListPetsToListPetDTOs(List<Pet> allPets){
        List<PetDTO> petDtos = new ArrayList<>();
        for (Pet pet:
                allPets) {
            PetDTO petDTO = converPetToPetDTO(pet);
            petDtos.add(petDTO);
        }
        return petDtos;
    }
}
