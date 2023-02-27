package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CreditDto;
import com.definexfinalcase.definexfinalcase.dto.response.GetCreditResponse;
import com.definexfinalcase.definexfinalcase.model.Credit;


import java.util.List;

public interface CreditService {
    CreditDto createCreditDemand(CreateCreditRequest createCreditRequest);
    CreditDto createCredit(UpdateCreditRequest updateCreditRequest);
    Credit findCreditById(Long id);
    List<CreditDto> findCreditByNatIdAndDateOfBirth(GetCreditResponse getCreditResponse);
}
