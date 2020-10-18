package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entitiy.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// TODO: 18/10/2020 add @Transactional

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    // TODO: 18/10/2020 Save customer
    public Long saveCustomer(Customer customer){
        return 0L;
    }

    // TODO: 18/10/2020
    public List<Customer> getAllCustomers(){
        return null;
    }

    // TODO: 18/10/2020  
    public Customer getOwnerByPet(Long pitID){
        return null;
    }






}
