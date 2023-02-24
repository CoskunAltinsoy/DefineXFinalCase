package com.definexfinalcase.definexfinalcase.dto.credit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCreditRequest {
    private Long id;
    private String creditType;
    private String description;
    private Long customerId;
}
