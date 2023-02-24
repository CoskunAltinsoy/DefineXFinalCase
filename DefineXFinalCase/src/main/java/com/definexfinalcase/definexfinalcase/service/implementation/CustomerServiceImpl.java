package com.definexfinalcase.definexfinalcase.service.implementation;

import com.definexfinalcase.definexfinalcase.dto.customer.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.customer.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.converter.CustomerConverter;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.repository.CustomerRepository;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import com.definexfinalcase.definexfinalcase.util.result.Result;
import com.definexfinalcase.definexfinalcase.util.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Result createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer = customerConverter.convertToEntity(createCustomerRequest);
        customer.setCreatedDate(LocalDateTime.now());
        customerRepository.save(customer);
       return new SuccessResult("CUSTOMER.ADDED");
    }

    public Result updateCustomer(UpdateCustomerRequest updateCustomerRequest){
        Customer customer = customerConverter.convertToEntity(updateCustomerRequest);
        customerRepository.save(customer);
        //customer.setCreatedDate(LocalDateTime.now());
        return new SuccessResult("CUSTOMER.UPDATED");
    }

    public Result deleteCustomer(Long id){
        customerRepository.delete(findCustomerById(id));
        return new SuccessResult("CUSTOMER.DELETED");
    }

    protected Customer findCustomerById(Long id){
        return this.customerRepository.findById(id).orElseThrow();//Exception
    }

}
