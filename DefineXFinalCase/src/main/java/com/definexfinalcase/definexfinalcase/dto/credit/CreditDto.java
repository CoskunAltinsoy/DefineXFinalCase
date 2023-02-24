package com.definexfinalcase.definexfinalcase.dto.credit;

import com.definexfinalcase.definexfinalcase.dto.customer.CustomerDto;
import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
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
    private CreditStatus creditStatus;
    private String description;
    private CustomerDto customerDto;

}
