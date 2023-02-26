package com.definexfinalcase.definexfinalcase.controller;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CreditDto;
import com.definexfinalcase.definexfinalcase.dto.response.GetCreditResponse;
import com.definexfinalcase.definexfinalcase.service.CreditService;
import com.definexfinalcase.definexfinalcase.util.result.DataResult;
import com.definexfinalcase.definexfinalcase.util.result.Result;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getByCredit")
    public ResponseEntity<DataResult<List<CreditDto>>> getByCredit(GetCreditResponse getCreditResponse){
        return ResponseEntity.ok(creditService.getCreditByNatIdAndDateOfBirth(getCreditResponse));
    }
}
