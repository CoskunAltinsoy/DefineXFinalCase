package com.definexfinalcase.definexfinalcase.service.implementation;

import com.definexfinalcase.definexfinalcase.dto.converter.CreditConverter;
import com.definexfinalcase.definexfinalcase.dto.credit.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.model.Credit;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
import com.definexfinalcase.definexfinalcase.repository.CreditRepository;
import com.definexfinalcase.definexfinalcase.service.CreditService;
import com.definexfinalcase.definexfinalcase.util.adapter.CustomerCheckCreditScore;
import com.definexfinalcase.definexfinalcase.util.adapter.SmsSender;
import com.definexfinalcase.definexfinalcase.util.result.Result;
import com.definexfinalcase.definexfinalcase.util.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final CustomerCheckCreditScore  customerCheckCreditScore;
    private final CustomerServiceImpl customerService;
    private final CreditConverter creditConverter;
    private final SmsSender smsSender;
    static final double CREDIT_LIMIT=4;
    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository,
                             CustomerCheckCreditScore customerCheckCreditScore,
                             CustomerServiceImpl customerServiceImpl,
                             CreditConverter creditConverter,
                             SmsSender smsSender) {
        this.creditRepository = creditRepository;
        this.customerCheckCreditScore = customerCheckCreditScore;
        this.customerService = customerServiceImpl;
        this.creditConverter = creditConverter;
        this.smsSender = smsSender;
    }

    public Result createCreditDemand(CreateCreditRequest createCreditRequest){
        Credit credit = creditConverter.convertToEntity(createCreditRequest);
        credit.setCreditStatus(CreditStatus.REVIEW);
        creditRepository.save(credit);
        smsSender.sendSms(credit.getCustomer().getPhoneNumber(),"Your Application is Rejected");
        return new SuccessResult("CREDIT.DEMAND.CREATED");
    }

    public Result createCredit(CreateCreditRequest createCreditRequest){
        Credit credit = creditConverter.convertToEntity(createCreditRequest);
        checkScore(createCreditRequest.getCustomerDto().getId(),credit.getId());
        creditRepository.save(credit);
        smsSender.sendSms(credit.getCustomer().getPhoneNumber(),"Your Application is Approved");
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

        if(score >= 1000){
            credit.setCreditStatus(CreditStatus.APPROVED);
            double totalLimit = (Double.valueOf(customer.getIncome()) * CREDIT_LIMIT) / 2;
            credit.setCreditLimit(totalLimit);
            if(!customer.getCollateral().isEmpty()){
                double totalCollateral =
                        (credit.getCreditLimit() +  (Double.valueOf(customer.getCollateral()) / 2));
                credit.setCreditLimit(totalCollateral);
            }
        }
    }

}
