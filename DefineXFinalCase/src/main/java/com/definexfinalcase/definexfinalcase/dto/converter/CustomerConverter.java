package com.definexfinalcase.definexfinalcase.dto.converter;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {
    private final CreditConverter creditConverter;
    @Autowired
    public CustomerConverter(CreditConverter creditConverter) {
        this.creditConverter = creditConverter;
    }

    public CustomerDto convertToDto(Customer customer){
        return new CustomerDto(customer.getId(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getPhoneNumber(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getNationalIdentity(),
                customer.getIncome(),
                customer.getCollateral(),
                customer.getDateOfBirth()
                /*creditConverter.convertToDto(customer.getCredits())*/);
    }

    public Customer convertToEntity(CustomerDto customerDto){
        return new Customer(customerDto.getId(),
                customerDto.getEmail(),
                customerDto.getPassword(),
                customerDto.getPhoneNumber(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getNationalIdentity(),
                customerDto.getIncome(),
                customerDto.getCollateral(),
                customerDto.getDateOfBirth()
                /*creditConverter.convertToEntity(customerDto.getCreditDtos())*/);
    }
    public Customer convertToEntity(CreateCustomerRequest createCustomerRequest){
        return new Customer(createCustomerRequest.getEmail(),
                createCustomerRequest.getPassword(),
                createCustomerRequest.getPhoneNumber(),
                createCustomerRequest.getFirstName(),
                createCustomerRequest.getLastName(),
                createCustomerRequest.getNationalIdentity(),
                createCustomerRequest.getIncome(),
                createCustomerRequest.getCollateral(),
                createCustomerRequest.getDateOfBirth());
    }
    public Customer convertToEntity(UpdateCustomerRequest updateCustomerRequest){
        return new Customer(updateCustomerRequest.getId(),
                updateCustomerRequest.getEmail(),
                updateCustomerRequest.getPassword(),
                updateCustomerRequest.getPhoneNumber(),
                updateCustomerRequest.getFirstName(),
                updateCustomerRequest.getLastName(),
                updateCustomerRequest.getNationalIdentity(),
                updateCustomerRequest.getIncome(),
                updateCustomerRequest.getCollateral(),
                updateCustomerRequest.getDateOfBirth());
    }


    public List<CustomerDto> convertToDto(List<Customer> customers){
        return customers.stream().map(x->convertToDto(x)).collect(Collectors.toList());
    }
    public List<Customer> convertToEntity(List<CustomerDto> customerDtos){
        return customerDtos.stream().map(x->convertToEntity(x)).collect(Collectors.toList());
    }
}
