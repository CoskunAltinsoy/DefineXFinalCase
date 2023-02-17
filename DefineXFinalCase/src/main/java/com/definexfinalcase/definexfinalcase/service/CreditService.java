package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.converter.CreditConverter;
import com.definexfinalcase.definexfinalcase.dto.credit.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.credit.CreditDto;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.repository.CreditRepository;
import com.definexfinalcase.definexfinalcase.util.adapter.CustomerCheckCreditScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private final CustomerCheckCreditScore  customerCheckCreditScore;
    private final  CustomerService customerService;
    private final CreditConverter creditConverter;
    @Autowired
    public CreditService(CreditRepository creditRepository,
                         CustomerCheckCreditScore customerCheckCreditScore, CustomerService customerService, CreditConverter creditConverter) {
        this.creditRepository = creditRepository;
        this.customerCheckCreditScore = customerCheckCreditScore;
        this.customerService = customerService;
        this.creditConverter = creditConverter;
    }

    public CreditDto createCredit(CreateCreditRequest createCreditRequest){
        Customer customer = customerService.findCustomerById(createCreditRequest.getCustomerDto().getId());
        int score = this.customerCheckCreditScore.checkUserCreditScore(customer.getNationalIdentity());
        if(score<500){

        }
        return creditConverter.convert(creditRepository.save());
    }


}
