package com.definexfinalcase.definexfinalcase.dto.credit;

import com.definexfinalcase.definexfinalcase.dto.Customer.CustomerDto;
import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditRequest {
    private String creditType;
    private double creditLimit;
    private CreditStatus creditStatus;
    private String description;
    private CustomerDto customerDto;

}
