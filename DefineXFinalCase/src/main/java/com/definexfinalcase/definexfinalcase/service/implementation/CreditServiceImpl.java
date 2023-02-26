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
import com.definexfinalcase.definexfinalcase.util.result.DataResult;
import com.definexfinalcase.definexfinalcase.util.result.Result;
import com.definexfinalcase.definexfinalcase.util.result.SuccessDataResult;
import com.definexfinalcase.definexfinalcase.util.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

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
    public Result createCreditDemand(CreateCreditRequest createCreditRequest){
        Customer customer = customerConverter.convertToEntity
                (customerService.findCustomerById(createCreditRequest.getCustomerId()).getData());

        Credit credit = new Credit(createCreditRequest.getCreditType(),
                createCreditRequest.getDescription(),
                customer);

        credit.setCreditStatus(CreditStatus.REVIEW);
        credit.setCreatedDate(LocalDateTime.now());
        creditRepository.save(credit);
        smsSender.sendSms(credit.getCustomer().getPhoneNumber(),"Your credit card request has been received");
        return new SuccessResult("CREDIT.DEMAND.CREATED");
    }
    @Override
    public Result createCredit(UpdateCreditRequest updateCreditRequest){
        Credit credit = creditConverter
                .convertToEntity(findCreditById(updateCreditRequest.getId()).getData());

        Customer customer = customerConverter.convertToEntity
                         (customerService.findCustomerById(updateCreditRequest.getCustomerId()).getData());

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
        creditRepository.save(credit);
        smsSender.sendSms(credit.getCustomer().getPhoneNumber(),"Your Application is Approved");
        return  new SuccessResult("CREDIT.CREATED");
    }

    @Override
    public DataResult<List<CreditDto>> findCreditByNatIdAndDateOfBirth(GetCreditResponse getCreditResponse) {
        Customer customer = customerService
                .findCustomerByNatIdAndDateOfBirth(getCreditResponse.getNationalIdentity(),
                                                  getCreditResponse.getDateOfBirth());
        List<Credit> credits = customer.getCredits();
        List<CreditDto> creditDtos = creditConverter.convertToDto(credits);
        return new SuccessDataResult<List<CreditDto>>(creditDtos,"CREDIT.LISTED");
    }
    @Override
    public DataResult<CreditDto> findCreditById(Long id){
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.NotFoundException("Credit not found"));
        return new SuccessDataResult<CreditDto>
                (creditConverter.convertToDto(credit),"CREDIT.LISTED");
    }

}
