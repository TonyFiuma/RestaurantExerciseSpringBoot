package com.jagaad.ExerciseJagaad.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    private CustomerDTO customer;

    private String deliveryAddress;

    private int numPilotes;

    private double orderTotal;

    private LocalDateTime creationTime;

    private LocalDateTime lastUpdateTime;

}
