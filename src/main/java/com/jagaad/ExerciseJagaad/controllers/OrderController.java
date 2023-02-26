package com.jagaad.ExerciseJagaad.controllers;

import com.jagaad.ExerciseJagaad.DTO.OrderDTO;
import com.jagaad.ExerciseJagaad.entities.Customer;
import com.jagaad.ExerciseJagaad.entities.Order;
import com.jagaad.ExerciseJagaad.services.CustomerService;
import com.jagaad.ExerciseJagaad.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController{
    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO){
        Customer customer = customerService.getCustomerById(orderDTO.getCustomer().getId());
        if(customer == null){
            return ResponseEntity.badRequest().build();
        }else{
        Order order = new Order();
        order.setCustomer(customer);
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setNumberOfPilotes(orderDTO.getNumPilotes());
        order.setOrderTotal(orderDTO.getOrderTotal());
        order.setCreatedDate(orderDTO.getCreationTime());
        return ResponseEntity.ok(orderService.saveOrder(order));}
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id,@RequestBody OrderDTO updatedOrderDTO){
        Order updatedOrder = new Order();
        updatedOrder.setDeliveryAddress(updatedOrderDTO.getDeliveryAddress());
        updatedOrder.setNumberOfPilotes(updatedOrderDTO.getNumPilotes());
        updatedOrder.setOrderTotal(updatedOrderDTO.getOrderTotal());
        Order order = orderService.updateOrder(id,updatedOrder);
        if(order == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Order>> searchOrders(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber){
        List<Order> orders = orderService.searchOrders(name,phoneNumber);
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/search-id/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        Order order = orderService.getOrderById(id);
        if(order == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}