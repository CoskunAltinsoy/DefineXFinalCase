package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.model.Customer;


import java.time.LocalDate;

public interface CustomerService {
    CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerDto updateCustomer(UpdateCustomerRequest updateCustomerRequest);

    void deleteCustomer(Long id);

    Customer findCustomerById(Long id);
    Customer findCustomerByNatIdAndDateOfBirth(String natId, LocalDate dateOfBirth);
}
