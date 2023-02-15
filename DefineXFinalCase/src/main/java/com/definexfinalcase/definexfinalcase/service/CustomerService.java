package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.repository.CustomerRepository;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


}
