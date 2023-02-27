package com.definexfinalcase.definexfinalcase.controller;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){
        return ResponseEntity.ok(customerService.createCustomer(createCustomerRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
       return (ResponseEntity<Void>) ResponseEntity.ok();
    }
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        return ResponseEntity.ok(customerService.updateCustomer(updateCustomerRequest));
    }


}
