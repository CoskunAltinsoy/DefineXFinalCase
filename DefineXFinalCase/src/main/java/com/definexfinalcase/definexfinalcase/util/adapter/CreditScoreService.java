package com.definexfinalcase.definexfinalcase.util.adapter;

import com.definexfinalcase.definexfinalcase.util.externalService.CustomerCreditScoreGenerator;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreService implements CustomerCheckCreditScore{
    @Override
    public int checkUserCreditScore(String nationalIdentity) {
        CustomerCreditScoreGenerator generator = new CustomerCreditScoreGenerator();
        return generator.CustomerCreditScore(nationalIdentity);
    }
}
