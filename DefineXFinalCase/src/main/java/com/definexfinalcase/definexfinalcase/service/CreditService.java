package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CreditDto;
import com.definexfinalcase.definexfinalcase.dto.response.GetCreditResponse;
import com.definexfinalcase.definexfinalcase.util.result.DataResult;
import com.definexfinalcase.definexfinalcase.util.result.Result;

import java.util.List;

public interface CreditService {
    Result createCreditDemand(CreateCreditRequest createCreditRequest);
    Result createCredit(UpdateCreditRequest updateCreditRequest);
    DataResult<CreditDto> findCreditById(Long id);
    DataResult<List<CreditDto>> findCreditByNatIdAndDateOfBirth(GetCreditResponse getCreditResponse);
}
