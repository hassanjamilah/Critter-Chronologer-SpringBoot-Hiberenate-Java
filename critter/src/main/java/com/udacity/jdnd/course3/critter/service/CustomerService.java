package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitiy.Customer;
import com.udacity.jdnd.course3.critter.entitiy.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: 18/10/2020 add @Transactional

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetService petService;

    // TODO: 18/10/2020 Save customer
    public Long saveCustomer(Customer customer){
        Customer cust1 = customerRepository.save(customer);
        return cust1.getId();
    }

    // TODO: 18/10/2020
    public List<Customer> getAllCustomers(){

        List<Customer> all = customerRepository.findAll();
        Customer cust = customerRepository.findById(1L).get();
        List<Pet> pets = petService.getAllPets();
        return customerRepository.findAll();
    }

    // TODO: 18/10/2020  
    public Customer getOwnerByPet(Long petID) throws NotFoundException {
        Pet pet = petService.getPetByID(petID);
        Customer customer = customerRepository.findByPetsContaining(pet);

        return customer;
    }

    public Customer getCustomerByID(Long customerID) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
        if (optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }
        throw new NotFoundException("Customer not found");
    }






}
