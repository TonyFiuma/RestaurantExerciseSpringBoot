package com.jagaad.ExerciseJagaad.controllers;

import com.jagaad.ExerciseJagaad.DTO.CustomerDTO;
import com.jagaad.ExerciseJagaad.entities.Customer;
import com.jagaad.ExerciseJagaad.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = customerService.convertToEntity(customerDTO);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer,HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(
            @RequestParam(required = false) String name,@RequestParam(required = false) String phoneNumber){
        List<Customer> customers = customerService.searchCustomers(name,phoneNumber);
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("/search-id/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
