package com.definexfinalcase.definexfinalcase.service.implementation;

import com.definexfinalcase.definexfinalcase.dto.converter.CreditConverter;
import com.definexfinalcase.definexfinalcase.dto.converter.CustomerConverter;
import com.definexfinalcase.definexfinalcase.dto.request.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CreditDto;
import com.definexfinalcase.definexfinalcase.dto.response.GetCreditResponse;
import com.definexfinalcase.definexfinalcase.exception.ServiceOperationException;
import com.definexfinalcase.definexfinalcase.model.Credit;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
import com.definexfinalcase.definexfinalcase.repository.CreditRepository;
import com.definexfinalcase.definexfinalcase.service.CreditService;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import com.definexfinalcase.definexfinalcase.util.adapter.CustomerCheckCreditScore;
import com.definexfinalcase.definexfinalcase.util.adapter.SmsSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final CustomerCheckCreditScore  customerCheckCreditScore;
    private final CustomerService customerService;
    private final CreditConverter creditConverter;
    private final CustomerConverter customerConverter;
    private final SmsSender smsSender;
    static final double CREDIT_LIMIT=4;
    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository,
                             CustomerCheckCreditScore customerCheckCreditScore,
                             CustomerService customerService,
                             CreditConverter creditConverter,
                             CustomerConverter customerConverter, SmsSender smsSender) {
        this.creditRepository = creditRepository;
        this.customerCheckCreditScore = customerCheckCreditScore;
        this.customerService = customerService;
        this.creditConverter = creditConverter;
        this.customerConverter = customerConverter;
        this.smsSender = smsSender;
    }
    @Override
    public CreditDto createCreditDemand(CreateCreditRequest createCreditRequest){

        Customer customer = customerService.findCustomerById(createCreditRequest.getCustomerId());
        Credit credit =
                new Credit(
                        createCreditRequest.getCreditType(),
                        createCreditRequest.getDescription(),
                        customer
                );
        credit.setCreditStatus(CreditStatus.REVIEW);
        credit.setCreatedDate(LocalDateTime.now());
        smsSender.sendSms(credit.getCustomer().getPhoneNumber(),"Your credit card request has been received");
        log.info(String.format("Credit demand created, date: %s", credit.getCreatedDate()));
        return creditConverter.convertToDto(creditRepository.save(credit));
    }
    @Override
    public CreditDto createCredit(UpdateCreditRequest updateCreditRequest){

        Credit credit = findCreditById(updateCreditRequest.getId());
        Customer customer = customerService.findCustomerById(updateCreditRequest.getCustomerId());

        int score = this.customerCheckCreditScore.checkUserCreditScore(customer.getNationalIdentity());

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
        credit.setCreatedDate(LocalDateTime.now());
        smsSender.sendSms(credit.getCustomer().getPhoneNumber(),"Your Application is Approved");
        log.info(String.format("Credit created, date: %s", credit.getCreatedDate()));
        return  creditConverter.convertToDto(creditRepository.save(credit));
    }

    @Override
    public List<CreditDto> findCreditByNatIdAndDateOfBirth(GetCreditResponse getCreditResponse) {
        Customer customer = customerService
                .findCustomerByNatIdAndDateOfBirth(getCreditResponse.getNationalIdentity(),
                                                  getCreditResponse.getDateOfBirth());
        List<Credit> credits = customer.getCredits();
        return creditConverter.convertToDto(credits);
    }
    @Override
    public Credit findCreditById(Long id){
        return creditRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.NotFoundException("Credit not found"));
    }

}
