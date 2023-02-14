package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.model.IndividualCustomer;
import com.definexfinalcase.definexfinalcase.repository.IndividualCustomerRepository;

public class IndividualCustomerService {
    private final IndividualCustomerRepository customerRepository;

    public IndividualCustomerService(IndividualCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


}
