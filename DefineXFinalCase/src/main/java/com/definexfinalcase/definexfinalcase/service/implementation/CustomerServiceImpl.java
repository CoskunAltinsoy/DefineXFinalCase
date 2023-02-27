package com.definexfinalcase.definexfinalcase.service.implementation;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.converter.CustomerConverter;
import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.exception.ServiceOperationException;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.repository.CustomerRepository;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }
    @Override
    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer =
                new Customer(
                        createCustomerRequest.getEmail(),
                        createCustomerRequest.getPassword(),
                        createCustomerRequest.getPhoneNumber(),
                        createCustomerRequest.getFirstName(),
                        createCustomerRequest.getLastName(),
                        createCustomerRequest.getNationalIdentity(),
                        createCustomerRequest.getIncome(),
                        createCustomerRequest.getCollateral(),
                        createCustomerRequest.getDateOfBirth()
                );
        customer.setCreatedDate(LocalDateTime.now());
        log.info(String.format("Customer saved, date: %s", customer.getCreatedDate()));
        return customerConverter.convertToDto(customerRepository.save(customer));
    }
    @Override
    public CustomerDto updateCustomer(UpdateCustomerRequest updateCustomerRequest){
        Customer customer = findCustomerById(updateCustomerRequest.getId());
        customer.setEmail(updateCustomerRequest.getEmail());
        customer.setPassword(updateCustomerRequest.getPassword());
        customer.setPhoneNumber(updateCustomerRequest.getPhoneNumber());
        customer.setFirstName(updateCustomerRequest.getFirstName());
        customer.setLastName(updateCustomerRequest.getLastName());
        customer.setNationalIdentity(updateCustomerRequest.getNationalIdentity());
        customer.setIncome(updateCustomerRequest.getIncome());
        customer.setCollateral(updateCustomerRequest.getCollateral());
        customer.setDateOfBirth(updateCustomerRequest.getDateOfBirth());
        customer.setCreatedDate(LocalDateTime.now());
        log.info(String.format("Customer updated, date: %s", customer.getCreatedDate()));
        return customerConverter.convertToDto(customerRepository.save(customer));
    }
    @Override
    public void deleteCustomer(Long id){
        customerRepository.delete(findCustomerById(id));
        log.info(String.format("Customer deleted, customerID: %s", id));
    }
    @Override
    public Customer findCustomerById(Long id){
        return  this.customerRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.NotFoundException("Customer not found."));

    }
    @Override
    public Customer findCustomerByNatIdAndDateOfBirth(String natId, LocalDate dateOfBirth) {
        return customerRepository.findCustomerByNationalIdentityAndDateOfBirth(natId,dateOfBirth)
                .orElseThrow(() -> new ServiceOperationException.NotFoundException("Customer not found."));
    }

}
