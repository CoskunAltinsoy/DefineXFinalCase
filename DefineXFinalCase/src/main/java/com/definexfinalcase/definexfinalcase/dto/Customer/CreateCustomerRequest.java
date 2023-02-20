package com.definexfinalcase.definexfinalcase.dto.Customer;

import com.definexfinalcase.definexfinalcase.dto.credit.CreditDto;
import com.definexfinalcase.definexfinalcase.model.Credit;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {
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
