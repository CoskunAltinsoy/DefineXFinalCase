package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.customer.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.customer.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.util.result.Result;

public interface CustomerService {
    Result createCustomer(CreateCustomerRequest createCustomerRequest);
    Result updateCustomer(UpdateCustomerRequest updateCustomerRequest);
    Result deleteCustomer(Long id);
}
