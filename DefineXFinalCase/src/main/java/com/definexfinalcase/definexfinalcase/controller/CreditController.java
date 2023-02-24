package com.definexfinalcase.definexfinalcase.controller;

import com.definexfinalcase.definexfinalcase.dto.credit.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.credit.UpdateCreditRequest;
import com.definexfinalcase.definexfinalcase.service.CreditService;
import com.definexfinalcase.definexfinalcase.service.implementation.CreditServiceImpl;
import com.definexfinalcase.definexfinalcase.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credits")
public class CreditController {
    private final CreditService creditService;
    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }
    @PostMapping("/createCreditDemand")
    public ResponseEntity<Result> createCreditDemand(@RequestBody CreateCreditRequest createCreditRequest){
        return ResponseEntity.ok(creditService.createCreditDemand(createCreditRequest));
    }
    @PostMapping("/createCredit")
    public ResponseEntity<Result> createCredit(@RequestBody UpdateCreditRequest updateCreditRequest){
        return ResponseEntity.ok(creditService.createCredit(updateCreditRequest));
    }
}
