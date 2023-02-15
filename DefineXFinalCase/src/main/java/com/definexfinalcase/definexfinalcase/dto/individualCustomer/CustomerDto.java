package com.definexfinalcase.definexfinalcase.dto.individualCustomer;

import com.definexfinalcase.definexfinalcase.dto.credit.CreditDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationalIdentity;
    private String income;
    private double guarantee;
    private LocalDate dateOfBirth;
    private List<CreditDto> creditDtos;
}
