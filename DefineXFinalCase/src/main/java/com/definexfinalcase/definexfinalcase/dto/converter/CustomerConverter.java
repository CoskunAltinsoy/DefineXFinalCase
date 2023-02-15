package com.definexfinalcase.definexfinalcase.dto.converter;

import com.definexfinalcase.definexfinalcase.dto.Customer.CustomerDto;
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

    public CustomerDto convert(Customer customer){
        return new CustomerDto(customer.getId(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getPhoneNumber(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getNationalIdentity(),
                customer.getIncome(),
                customer.getGuarantee(),
                customer.getDateOfBirth(),
                creditConverter.convert(customer.getCredits()));
    }

    public List<CustomerDto> convert(List<Customer> customers){
        return customers.stream().map(x->convert(x)).collect(Collectors.toList());
    }
}
