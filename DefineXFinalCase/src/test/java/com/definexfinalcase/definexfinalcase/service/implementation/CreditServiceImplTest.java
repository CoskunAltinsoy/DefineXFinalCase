package com.definexfinalcase.definexfinalcase.service.implementation;

import com.definexfinalcase.definexfinalcase.dto.converter.CreditConverter;
import com.definexfinalcase.definexfinalcase.dto.converter.CustomerConverter;
import com.definexfinalcase.definexfinalcase.dto.request.CreateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCreditRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CreditDto;
import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.model.Credit;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
import com.definexfinalcase.definexfinalcase.repository.CreditRepository;
import com.definexfinalcase.definexfinalcase.repository.CustomerRepository;
import com.definexfinalcase.definexfinalcase.service.CreditService;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import com.definexfinalcase.definexfinalcase.util.adapter.CustomerCheckCreditScore;
import com.definexfinalcase.definexfinalcase.util.adapter.SmsSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreditServiceImplTest {
    private CreditService creditService;

    private CreditRepository creditRepository;
    private CustomerCheckCreditScore customerCheckCreditScore;
    private CustomerService customerService;
    private CustomerConverter customerConverter;
    private CreditConverter creditConverter;
    private SmsSender smsSender;



    static final Long id = 1L;
    static final Long customerId = 1L;

    Customer customer =
            new Customer(id,
                    "coskun@gmail.com",
                    "12345",
                    "+905455727995",
                    "Mücahit",
                    "Altınsoy",
                    "21182764900",
                    "9000",
                    "10000",
                    LocalDate.of(1996,07,29)
            );

    CustomerDto customerDto = new CustomerDto(
            id,
            "coskun@gmail.com",
            "12345",
            "+905455727995",
            "Mücahit",
            "Altınsoy",
            "21182764900",
            "9000",
            "10000",
            LocalDate.of(1996,07,29)
    );
    CreateCustomerRequest createCustomerRequest =
            new CreateCustomerRequest(
                    "coskun@gmail.com",
                    "12345",
                    "+905455727995",
                    "Mücahit",
                    "Altınsoy",
                    "21182764900",
                    "9000",
                    "10000",
                    LocalDate.of(1996,07,29)
            );
    CreateCreditRequest createCreditRequest =
            new CreateCreditRequest(
                    "İhtiyaç Kredisi",
                    "İhtiyaç Kredisi",
                    customerId
            );

    UpdateCreditRequest updateCreditRequest =
            new UpdateCreditRequest(
                    id,
                    "İhtiyaç Kredisi",
                    "İhtiyaç Kredisi",
                    customerId
            );

    Credit credit = new Credit(
            id,
            "İhtiyaç Kredisi",
            10000D,
            CreditStatus.REVIEW,
            "İhtiyaç Kredisi",
            customer
    );
    Credit savedCredit = new Credit(
            id,
            "İhtiyaç Kredisi",
            10000D,
            CreditStatus.REVIEW,
            "İhtiyaç Kredisi",
            customer
    );
    Credit updateCredit = new Credit(
            id,
            "İhtiyaç Kredisi",
            10000D,
            CreditStatus.REVIEW,
            "İhtiyaç Kredisi",
            customer
    );

    CreditDto creditDto = new CreditDto(
            id,
            "İhtiyaç Kredisi",
            10000D,
            CreditStatus.REVIEW,
            "İhtiyaç Kredisi",
            customerDto
    );

    @BeforeEach
    void setUp() {

        creditRepository = Mockito.mock(CreditRepository.class);
        customerCheckCreditScore = Mockito.mock(CustomerCheckCreditScore.class);
        customerService = Mockito.mock(CustomerService.class);
        customerConverter = Mockito.mock(CustomerConverter.class);
        creditConverter = Mockito.mock(CreditConverter.class);
        smsSender = Mockito.mock(SmsSender.class);

        creditService = new CreditServiceImpl(
                creditRepository,
                customerCheckCreditScore,
                customerService,
                creditConverter,
                customerConverter,
                smsSender);
    }

    @Test
    public void whenCreateCreditDemand_itShouldReturnSuccessResult(){

        /*Mockito.when(customerService.findCustomerById(id)).thenReturn(customer);
        Mockito.when(creditRepository.save(credit)).thenReturn(savedCredit);
        Mockito.when(creditConverter.convertToDto(savedCredit)).thenReturn(creditDto);

        CreditDto result = creditService.createCreditDemand(createCreditRequest);

        assertEquals(result, creditDto);

        Mockito.verify(customerService).findCustomerById(id);
        Mockito.verify(creditRepository).save(credit);
        Mockito.verify(creditConverter).convertToDto(savedCredit);*/
    }
    @Test
    public void whenCreateCredit_itShouldReturnSuccessResult(){

    }

}