package com.definexfinalcase.definexfinalcase.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditRequest {
    @NotNull
    private String creditType;
    @NotNull
    private String description;
    @NotNull
    private Long customerId;

}
