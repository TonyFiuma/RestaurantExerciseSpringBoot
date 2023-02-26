package com.jagaad.ExerciseJagaad.services;

import com.jagaad.ExerciseJagaad.entities.Order;
import com.jagaad.ExerciseJagaad.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    /**
     The base price for a single pilote.
     */
    private static final double pilotesPrice = 1.33;

    /**
     The number of minutes allowed for updating an order after its creation time.
     */
    private static final int updateWindowMinutes = 5;

    @Autowired
    private IOrderRepository orderRepository;

    /**
     Saves the given Order entity after setting the order total and created date.
     @param order the Order entity to be saved
     @return the saved Order entity
     */
    public Order saveOrder(Order order) {
        order.setOrderTotal(order.getNumberOfPilotes() * pilotesPrice);
        order.setCreatedDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    /**
     Updates the Order entity with the given ID if it was created less than or equal to the update window time in minutes.
     @param id the ID of the Order entity to be updated
     @param updatedOrder the updated Order entity
     @return the updated Order entity, or null if the update window has passed or the order with the given ID does not exist
     */
    public Order updateOrder(Long id, Order updatedOrder) {
        Order order = orderRepository.findById(id).orElse(null);
        if(order != null){
            LocalDateTime createdAt = order.getCreatedDate();
            LocalDateTime now = LocalDateTime.now();
            if(Duration.between(createdAt,now).toMinutes() <= updateWindowMinutes){
                order.setDeliveryAddress(updatedOrder.getDeliveryAddress());
                order.setNumberOfPilotes(updatedOrder.getNumberOfPilotes());
                order.setOrderTotal(order.getNumberOfPilotes() * pilotesPrice);
                return orderRepository.save(order);
            }
        }
        return null;
    }

    /**
     Searches for Order entities based on the customer's first name and/or phone number.
     @param name the customer's first name to search for, can be null or empty
     @param phoneNumber the customer's phone number to search for, can be null or empty
     @return a list of Order entities matching the search criteria, or all Order entities if no criteria are specified
     */
    public List<Order> searchOrders(String name,String phoneNumber){
        if(name != null && !name.isEmpty() && phoneNumber != null && !phoneNumber.isEmpty()){
            return orderRepository.findByCustomerFirstNameContainingIgnoreCaseAndCustomerPhoneNumberContaining(
                    name,phoneNumber);
        } else if(name != null && !name.isEmpty()){
            return orderRepository.findByCustomerFirstNameContainingIgnoreCase(name);
        } else if(phoneNumber != null && !phoneNumber.isEmpty()){
            return orderRepository.findByCustomerPhoneNumberContaining(phoneNumber);
        } else {
            return orderRepository.findAll();
        }
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}

