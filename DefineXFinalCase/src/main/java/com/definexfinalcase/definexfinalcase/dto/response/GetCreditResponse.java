package com.definexfinalcase.definexfinalcase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCreditResponse {
    private String nationalIdentity;
    private LocalDate dateOfBirth;
}
