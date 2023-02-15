package com.definexfinalcase.definexfinalcase.dto.converter;

import com.definexfinalcase.definexfinalcase.dto.credit.CreditDto;
import com.definexfinalcase.definexfinalcase.dto.individualCustomer.CustomerDto;
import com.definexfinalcase.definexfinalcase.model.Credit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditConverter {
    public CreditDto convert(Credit credit){
        return new CreditDto(credit.getId(),
                credit.getCreditType(),
                credit.getCreditLimit(),
                credit.getDescription());
    }
    public List<CreditDto> convert(List<Credit> credits){
        return credits.stream().map(x->convert(x)).collect(Collectors.toList());
    }
}
