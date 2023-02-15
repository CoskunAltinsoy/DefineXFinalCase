package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.Customer.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.Customer.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.converter.CustomerConverter;
import com.definexfinalcase.definexfinalcase.dto.Customer.CustomerDto;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.repository.CustomerRepository;

import java.time.LocalDateTime;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer = new Customer(createCustomerRequest.getEmail(),
                createCustomerRequest.getPassword(),
                createCustomerRequest.getPhoneNumber(),
                createCustomerRequest.getFirstName(),
                createCustomerRequest.getLastName(),
                createCustomerRequest.getNationalIdentity(),
                createCustomerRequest.getIncome(),
                createCustomerRequest.getGuarantee(),
                createCustomerRequest.getDateOfBirth());
        customer.setCreatedDate(LocalDateTime.now());
       return customerConverter.convert(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(UpdateCustomerRequest updateCustomerRequest){
        Customer customer = findCustomerById(updateCustomerRequest.getId());
        customer.setEmail(updateCustomerRequest.getEmail());
        customer.setPassword(updateCustomerRequest.getPassword());
        customer.setPhoneNumber(updateCustomerRequest.getPhoneNumber());
        customer.setFirstName(updateCustomerRequest.getFirstName());
        customer.setLastName(updateCustomerRequest.getLastName());
        customer.setNationalIdentity(updateCustomerRequest.getNationalIdentity());
        customer.setIncome(updateCustomerRequest.getIncome());
        customer.setGuarantee(updateCustomerRequest.getGuarantee());
        customer.setDateOfBirth(updateCustomerRequest.getDateOfBirth());
        customer.setCreatedDate(LocalDateTime.now());
        return customerConverter.convert(customerRepository.save(customer));
    }

    private Customer findCustomerById(Long id){
        return this.customerRepository.findById(id).orElseThrow();//Exception
    }

}
