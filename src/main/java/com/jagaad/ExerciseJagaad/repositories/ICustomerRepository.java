package com.jagaad.ExerciseJagaad.repositories;

import com.jagaad.ExerciseJagaad.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    List<Customer> findByPhoneNumberContaining(String phoneNumber);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) AND c.phoneNumber LIKE CONCAT('%', :phoneNumber, '%')")
    List<Customer> searchCustomers(@Param("name") String name,@Param("phoneNumber") String phoneNumber);
}

