package com.definexfinalcase.definexfinalcase.controller;

import com.definexfinalcase.definexfinalcase.dto.Customer.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.Customer.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import com.definexfinalcase.definexfinalcase.service.implementation.CustomerServiceImpl;
import com.definexfinalcase.definexfinalcase.util.result.Result;
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
    public ResponseEntity<Result> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return ResponseEntity.ok(customerService.createCustomer(createCustomerRequest));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteCustomer(@PathVariable Long id){
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
    @PutMapping("/update")
    public ResponseEntity<Result> updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest){
        return ResponseEntity.ok(customerService.updateCustomer(updateCustomerRequest));
    }


}
