package com.definexfinalcase.definexfinalcase.dto.credit;

import com.definexfinalcase.definexfinalcase.dto.individualCustomer.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDto {
    private Long id;
    private String creditType;
    private double creditLimit;
    private String description;

}
