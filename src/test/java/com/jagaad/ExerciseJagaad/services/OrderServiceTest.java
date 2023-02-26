package com.jagaad.ExerciseJagaad.services;

import com.jagaad.ExerciseJagaad.entities.Order;
import com.jagaad.ExerciseJagaad.repositories.IOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private IOrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setNumberOfPilotes(2);
        order.setDeliveryAddress("Via Roma, 1");
        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setNumberOfPilotes(2);
        savedOrder.setDeliveryAddress("Via Roma, 1");
        savedOrder.setOrderTotal(2.66);
        savedOrder.setCreatedDate(LocalDateTime.now());

        when(orderRepository.save(order)).thenReturn(savedOrder);

        Order result = orderService.saveOrder(order);

        verify(orderRepository, times(1)).save(order);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(savedOrder.getNumberOfPilotes(), result.getNumberOfPilotes());
        Assertions.assertEquals(savedOrder.getDeliveryAddress(), result.getDeliveryAddress());
        Assertions.assertEquals(savedOrder.getOrderTotal(), result.getOrderTotal());
        Assertions.assertEquals(savedOrder.getCreatedDate(), result.getCreatedDate());
    }

    @Test
    public void testUpdateOrder() {
        Order existingOrder = new Order();
        existingOrder.setId(1L);
        existingOrder.setNumberOfPilotes(2);
        existingOrder.setDeliveryAddress("Via Roma, 1");
        existingOrder.setOrderTotal(2.66);
        existingOrder.setCreatedDate(LocalDateTime.now().minusMinutes(4));
        Order updatedOrder = new Order();
        updatedOrder.setNumberOfPilotes(3);
        updatedOrder.setDeliveryAddress("Via Milano, 2");
        Order savedOrder = new Order();
        savedOrder.setId(1L);
        savedOrder.setNumberOfPilotes(3);
        savedOrder.setDeliveryAddress("Via Milano, 2");
        savedOrder.setOrderTotal(3.99);
        savedOrder.setCreatedDate(existingOrder.getCreatedDate());

        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(savedOrder);

        Order result = orderService.updateOrder(1L, updatedOrder);

        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(existingOrder);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedOrder.getId(), result.getId());
        Assertions.assertEquals(savedOrder.getNumberOfPilotes(), result.getNumberOfPilotes());
        Assertions.assertEquals(savedOrder.getDeliveryAddress(), result.getDeliveryAddress());
        Assertions.assertEquals(savedOrder.getOrderTotal(), result.getOrderTotal());
        Assertions.assertEquals(savedOrder.getCreatedDate(), result.getCreatedDate());
    }
}
