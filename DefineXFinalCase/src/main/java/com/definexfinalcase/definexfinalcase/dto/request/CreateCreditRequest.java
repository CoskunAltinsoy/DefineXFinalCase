package com.definexfinalcase.definexfinalcase.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditRequest {
    @NotNull
    @NotBlank
    private String creditType;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private Long customerId;

}
