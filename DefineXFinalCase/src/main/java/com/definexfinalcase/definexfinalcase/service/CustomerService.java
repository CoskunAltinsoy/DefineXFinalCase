package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.util.result.Result;

public interface CustomerService {
    Result createCustomer(CreateCustomerRequest createCustomerRequest);
    Result updateCustomer(UpdateCustomerRequest updateCustomerRequest);
    Result deleteCustomer(Long id);

}
