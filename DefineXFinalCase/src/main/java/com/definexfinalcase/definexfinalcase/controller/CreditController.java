package com.definexfinalcase.definexfinalcase.controller;

import com.definexfinalcase.definexfinalcase.dto.request.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CreditDto;
import com.definexfinalcase.definexfinalcase.dto.response.GetCreditResponse;
import com.definexfinalcase.definexfinalcase.service.CreditService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CreditDto> createCreditDemand(@RequestBody @Valid CreateCreditRequest createCreditRequest){
        return ResponseEntity.ok(creditService.createCreditDemand(createCreditRequest));
    }
    @PostMapping("/createCredit")
    public ResponseEntity<CreditDto> createCredit(@RequestBody @Valid UpdateCreditRequest updateCreditRequest){
        return ResponseEntity.ok(creditService.createCredit(updateCreditRequest));
    }
    @GetMapping("/getByCredit")
    public ResponseEntity<List<CreditDto>> getByCredit(GetCreditResponse getCreditResponse){
        return ResponseEntity.ok(creditService.findCreditByNatIdAndDateOfBirth(getCreditResponse));
    }
}
