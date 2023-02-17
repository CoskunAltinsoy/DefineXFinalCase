package com.definexfinalcase.definexfinalcase.dto.converter;

import com.definexfinalcase.definexfinalcase.dto.credit.CreditDto;
import com.definexfinalcase.definexfinalcase.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditConverter {
    private final CustomerConverter customerConverter;

    @Autowired
    public CreditConverter(CustomerConverter customerConverter) {
        this.customerConverter = customerConverter;
    }

    public CreditDto convert(Credit credit){
        return new CreditDto(credit.getId(),
                credit.getCreditType(),
                credit.getCreditLimit(),
                credit.getDescription(),
                customerConverter.convert(credit.getCustomer()));
    }
    public List<CreditDto> convert(List<Credit> credits){
        return credits.stream().map(x->convert(x)).collect(Collectors.toList());
    }
}
