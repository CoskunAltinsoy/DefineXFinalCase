package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.converter.CreditConverter;
import com.definexfinalcase.definexfinalcase.dto.credit.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.credit.CreditDto;
import com.definexfinalcase.definexfinalcase.model.Credit;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
import com.definexfinalcase.definexfinalcase.repository.CreditRepository;
import com.definexfinalcase.definexfinalcase.util.adapter.CustomerCheckCreditScore;
import com.definexfinalcase.definexfinalcase.util.result.ErrorResult;
import com.definexfinalcase.definexfinalcase.util.result.Result;
import com.definexfinalcase.definexfinalcase.util.result.SuccessResult;
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

    public Result createCredit(CreateCreditRequest createCreditRequest){
        Credit credit = creditConverter.convertToEntity(createCreditRequest);
        checkScore(createCreditRequest.getCustomerDto().getId(),credit.getId());
        creditRepository.save(credit);
        return  new SuccessResult("CREDIT.ADDED");
    }
    protected Credit findCreditById(Long id){
        return this.creditRepository.findById(id).orElseThrow();//Exception
    }

    private void checkScore(Long customerId, Long creditId){
        Customer customer = customerService.findCustomerById(customerId);
        int score = this.customerCheckCreditScore.checkUserCreditScore(customer.getNationalIdentity());
        Credit credit = findCreditById(creditId);
        if (score<500) {
            credit.setCreditStatus(CreditStatus.REJECTED);
        }
    }

}
