package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entitiy.Customer;
import com.udacity.jdnd.course3.critter.entitiy.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

//    @Query("select p from Pet p where p.ownerId = :owner")
//    List<Pet> findAllByOwnerIDsID(@Param("owner") Customer owner);
    @Query("select p from Pet p where p.ownerId.id = :id")
    List<Pet> getAllMy(@Param("id") Long id);
}
