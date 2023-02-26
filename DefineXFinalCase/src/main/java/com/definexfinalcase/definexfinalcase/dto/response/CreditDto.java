package com.definexfinalcase.definexfinalcase.dto.response;

import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditDto {
    private Long id;
    private String creditType;
    private double creditLimit;
    private CreditStatus creditStatus;
    private String description;
    private CustomerDto customerDto;

}
