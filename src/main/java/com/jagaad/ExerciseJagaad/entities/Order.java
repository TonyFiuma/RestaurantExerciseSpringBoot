package com.jagaad.ExerciseJagaad.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotBlank
    private String deliveryAddress;

    @Min(value = 5)
    @Max(value = 15)
    private Integer numberOfPilotes;

    @NotNull
    private Double orderTotal;
    @Column(name = "created_date")
    private LocalDateTime createdDate;


}

