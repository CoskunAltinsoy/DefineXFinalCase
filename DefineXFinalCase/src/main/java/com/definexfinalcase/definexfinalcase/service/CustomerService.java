package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.util.result.DataResult;
import com.definexfinalcase.definexfinalcase.util.result.Result;

import java.time.LocalDate;

public interface CustomerService {
    Result createCustomer(CreateCustomerRequest createCustomerRequest);

    Result updateCustomer(UpdateCustomerRequest updateCustomerRequest);

    Result deleteCustomer(Long id);

    DataResult<CustomerDto> findCustomerById(Long id);
    Customer findCustomerByNatIdAndDateOfBirth(String natId, LocalDate dateOfBirth);
}
