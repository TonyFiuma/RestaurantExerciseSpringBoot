package com.jagaad.ExerciseJagaad.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

}
