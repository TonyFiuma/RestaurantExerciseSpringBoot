package com.jagaad.ExerciseJagaad.repositories;

import com.jagaad.ExerciseJagaad.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface IOrderRepository extends JpaRepository<Order, Long>{

    List<Order> findByCustomerFirstNameContainingIgnoreCase(String name);

    List<Order> findByCustomerPhoneNumberContaining(String phoneNumber);

    List<Order> findByCustomerFirstNameContainingIgnoreCaseAndCustomerPhoneNumberContaining(String firstName,String phoneNumber);
}




