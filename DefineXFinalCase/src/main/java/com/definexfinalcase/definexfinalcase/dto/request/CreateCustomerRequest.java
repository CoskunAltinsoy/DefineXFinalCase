package com.definexfinalcase.definexfinalcase.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {
    @NotNull
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String nationalIdentity;
    @NotNull
    private String income;
    private String collateral;
    @NotNull
    private LocalDate dateOfBirth;

}
