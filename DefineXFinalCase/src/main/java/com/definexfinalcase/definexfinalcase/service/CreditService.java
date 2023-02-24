package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.credit.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.credit.UpdateCreditRequest;
import com.definexfinalcase.definexfinalcase.util.result.Result;

public interface CreditService {
    Result createCreditDemand(CreateCreditRequest createCreditRequest);
    Result createCredit(UpdateCreditRequest updateCreditRequest);
}
