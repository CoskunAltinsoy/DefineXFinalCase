package com.definexfinalcase.definexfinalcase.service.implementation;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.converter.CustomerConverter;
import com.definexfinalcase.definexfinalcase.exception.ServiceOperationException;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.repository.CustomerRepository;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import com.definexfinalcase.definexfinalcase.util.result.Result;
import com.definexfinalcase.definexfinalcase.util.result.SuccessResult;
import io.jsonwebtoken.io.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public Result createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer = customerConverter.convertToEntity(createCustomerRequest);
        customer.setCreatedDate(LocalDateTime.now());
        customerRepository.save(customer);
       return new SuccessResult("CUSTOMER.ADDED");
    }
    @Override
    public Result updateCustomer(UpdateCustomerRequest updateCustomerRequest){
        Customer customer = findCustomerById(updateCustomerRequest.getId());
        //Customer customer = customerConverter.convertToEntity(updateCustomerRequest);
        customerRepository.save(customer);
        //customer.setCreatedDate(LocalDateTime.now());
        return new SuccessResult("CUSTOMER.UPDATED");
    }
    @Override
    public Result deleteCustomer(Long id){
        customerRepository.delete(findCustomerById(id));
        return new SuccessResult("CUSTOMER.DELETED");
    }
    protected Customer findCustomerById(Long id){
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.NotFoundException("Customer not found."));
    }
    protected Customer findCustomerByNatIdAndDateOfBirth(String natId, LocalDate dateOfBirth) {
        return customerRepository.findCustomerByNationalIdentityAndDateOfBirth(natId,dateOfBirth)
                .orElseThrow(() -> new ServiceOperationException.NotFoundException("Customer not found."));

    }

}
