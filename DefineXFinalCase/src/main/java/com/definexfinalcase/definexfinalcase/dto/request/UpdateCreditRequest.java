package com.definexfinalcase.definexfinalcase.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCreditRequest {
    @NotNull
    private Long id;

    private String creditType;

    private String description;

    private Long customerId;
}
