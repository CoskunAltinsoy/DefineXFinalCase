package com.definexfinalcase.definexfinalcase.service;

import com.definexfinalcase.definexfinalcase.dto.converter.CreditConverter;
import com.definexfinalcase.definexfinalcase.dto.credit.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.credit.CreditDto;
import com.definexfinalcase.definexfinalcase.model.Credit;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
import com.definexfinalcase.definexfinalcase.repository.CreditRepository;
import com.definexfinalcase.definexfinalcase.util.adapter.CustomerCheckCreditScore;
import com.definexfinalcase.definexfinalcase.util.result.DataResult;
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
    static final double CREDIT_LIMIT=4;
    @Autowired
    public CreditService(CreditRepository creditRepository,
                         CustomerCheckCreditScore customerCheckCreditScore, CustomerService customerService, CreditConverter creditConverter) {
        this.creditRepository = creditRepository;
        this.customerCheckCreditScore = customerCheckCreditScore;
        this.customerService = customerService;
        this.creditConverter = creditConverter;
    }

    public Result createCreditDemand(CreateCreditRequest createCreditRequest){
        Credit credit = creditConverter.convertToEntity(createCreditRequest);
        credit.setCreditStatus(CreditStatus.REVIEW);
        creditRepository.save(credit);
        return new SuccessResult("CREDIT.DEMAND.CREATED");
    }
    
    public Result createCredit(CreateCreditRequest createCreditRequest){
        Credit credit = creditConverter.convertToEntity(createCreditRequest);
        checkScore(createCreditRequest.getCustomerDto().getId(),credit.getId());
        creditRepository.save(credit);
        return  new SuccessResult("CREDIT.CREATED");
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
        if(score>=500 && score<1000 && Double.valueOf(customer.getIncome()) < 5000){
            credit.setCreditStatus(CreditStatus.APPROVED);
            credit.setCreditLimit(10000);
            if(!customer.getCollateral().isEmpty()){
                double totalCollateral =
                        (credit.getCreditLimit() +  (Double.valueOf(customer.getCollateral()) / 10));
                credit.setCreditLimit(totalCollateral);
            }
        }
        if(score>=500 && score<1000 &&
                Double.valueOf(customer.getIncome()) >=5000 && Double.valueOf(customer.getIncome()) < 10000  ){
            credit.setCreditStatus(CreditStatus.APPROVED);
            credit.setCreditLimit(20000);
            if(!customer.getCollateral().isEmpty()){
                double totalCollateral =
                        (credit.getCreditLimit() +  (Double.valueOf(customer.getCollateral()) / 5));
                credit.setCreditLimit(totalCollateral);
            }
        }
        if(score>=500 && score<1000 &&
                Double.valueOf(customer.getIncome()) >=10000){
            credit.setCreditStatus(CreditStatus.APPROVED);
            double totalLimit = (Double.valueOf(customer.getIncome()) * CREDIT_LIMIT) / 2;
            credit.setCreditLimit(totalLimit);
            if(!customer.getCollateral().isEmpty()){
                double totalCollateral =
                        (credit.getCreditLimit() +  (Double.valueOf(customer.getCollateral()) / 4));
                credit.setCreditLimit(totalCollateral);
            }
        }
    }

}
