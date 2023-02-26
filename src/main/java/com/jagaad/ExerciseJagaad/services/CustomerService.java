package com.jagaad.ExerciseJagaad.services;

import com.jagaad.ExerciseJagaad.DTO.CustomerDTO;
import com.jagaad.ExerciseJagaad.entities.Customer;
import com.jagaad.ExerciseJagaad.repositories.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     Search for customers based on the given name and/or phone number.
     @param name the name to search for (can be null or empty to ignore)
     @param phoneNumber the phone number to search for (can be null or empty to ignore)
     @return a list of customers matching the search criteria, or all customers if no criteria are specified
     */
    public List<Customer> searchCustomers(String name, String phoneNumber) {
        if (name != null && !name.isEmpty() && phoneNumber != null && !phoneNumber.isEmpty()) {
            return customerRepository.searchCustomers(name, phoneNumber);
        } else if (name != null && !name.isEmpty()) {
            return customerRepository.findByFirstNameContainingIgnoreCase(name);
        } else if (phoneNumber != null && !phoneNumber.isEmpty()) {
            return customerRepository.findByPhoneNumberContaining(phoneNumber);
        } else {
            return customerRepository.findAll();
        }
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    /**
     Converts a CustomerDTO object to a Customer entity.
     @param customerDTO the CustomerDTO object to be converted
     @return the converted Customer entity
     */
    public Customer convertToEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }
}

