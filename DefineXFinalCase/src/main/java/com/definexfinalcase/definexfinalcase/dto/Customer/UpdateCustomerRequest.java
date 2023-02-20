package com.definexfinalcase.definexfinalcase.dto.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {
    private Long id;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String nationalIdentity;
    private String income;
    private String collateral;
    private LocalDate dateOfBirth;
}
