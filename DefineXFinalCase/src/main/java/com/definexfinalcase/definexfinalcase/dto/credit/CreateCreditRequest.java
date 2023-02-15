package com.definexfinalcase.definexfinalcase.dto.credit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditRequest {
    private String creditType;
    private double creditLimit;
    private String description;
}
