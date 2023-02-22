package com.definexfinalcase.definexfinalcase.dto.converter;

import com.definexfinalcase.definexfinalcase.dto.credit.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.credit.CreditDto;
import com.definexfinalcase.definexfinalcase.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreditConverter {
    private final CustomerConverter customerConverter;

    @Autowired
    public CreditConverter(@Lazy CustomerConverter customerConverter) {
        this.customerConverter = customerConverter;
    }

    public CreditDto convertToDto(Credit credit){
        return new CreditDto(credit.getId(),
                credit.getCreditType(),
                credit.getCreditLimit(),
                credit.getCreditStatus(),
                credit.getDescription(),
                customerConverter.convertToDto(credit.getCustomer()));
    }

    public Credit convertToEntity(CreditDto creditDto){
        return new Credit(creditDto.getId(),
                creditDto.getCreditType(),
                creditDto.getCreditLimit(),
                creditDto.getCreditStatus(),
                creditDto.getDescription(),
                customerConverter.convertToEntity(creditDto.getCustomerDto()));
    }

    public Credit convertToEntity(CreateCreditRequest createCreditRequest){
        return new Credit(createCreditRequest.getCreditType(),
                createCreditRequest.getDescription(),
                customerConverter.convertToEntity(createCreditRequest.getCustomerDto()));
    }
    public List<CreditDto> convertToDto(List<Credit> credits){
        return credits.stream().map(x->convertToDto(x)).collect(Collectors.toList());
    }
    public List<Credit> convertToEntity(List<CreditDto> creditDtos){
        return creditDtos.stream().map(x->convertToEntity(x)).collect(Collectors.toList());
    }
}
