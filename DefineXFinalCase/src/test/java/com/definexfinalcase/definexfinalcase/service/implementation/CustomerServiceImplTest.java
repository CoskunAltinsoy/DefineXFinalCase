package com.definexfinalcase.definexfinalcase.service.implementation;

import com.definexfinalcase.definexfinalcase.dto.converter.CustomerConverter;
import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.exception.ServiceOperationException;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.repository.CustomerRepository;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    private CustomerService customerService;

    private CustomerRepository customerRepository;
    private CustomerConverter customerConverter;

     static final Long id = 1L;

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

    Customer savedCustomer =
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
    Customer updateCustomer =
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
    UpdateCustomerRequest updateCustomerRequest =
            new UpdateCustomerRequest(
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

    CustomerDto customerDto =
            new CustomerDto(id,
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

    @BeforeEach
    void setUp() {

        customerRepository = Mockito.mock(CustomerRepository.class);
        customerConverter = Mockito.mock(CustomerConverter.class);

        customerService = new CustomerServiceImpl(customerRepository, customerConverter);
    }

    @Test
    public void whenCreateCustomer_itShouldReturnCustomerDto(){

        Mockito.when(customerRepository.save(customer)).thenReturn(savedCustomer);
        Mockito.when(customerConverter.convertToDto(savedCustomer)).thenReturn(customerDto);

        CustomerDto result = customerService.createCustomer(createCustomerRequest);

        assertEquals(result, customerDto);

        Mockito.verify(customerRepository).save(customer);
        Mockito.verify(customerConverter).convertToDto(savedCustomer);
    }

    @Test
    public void testUpdateCustomer_whenCustomerIdExist_itShouldReturnCustomerDto(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(updateCustomer)).thenReturn(savedCustomer);
        Mockito.when(customerConverter.convertToDto(savedCustomer)).thenReturn(customerDto);

        CustomerDto result = customerService.updateCustomer(updateCustomerRequest);

       assertEquals(result, customerDto);

        Mockito.verify(customerRepository).findById(id);
        Mockito.verify(customerRepository).save(updateCustomer);
        Mockito.verify(customerConverter).convertToDto(savedCustomer);

    }

    @Test
    public void testUpdateCustomer_whenCustomerIdDoesNotExist_itShouldReturnCustomerDto(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ServiceOperationException.NotFoundException.class,
                () -> customerService.updateCustomer(updateCustomerRequest));

        Mockito.verify(customerRepository).findById(id);
    }
    @Test
    public void testDeleteCustomer_whenCustomerIdExist_itShouldDeleteCustomer(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        customerService.deleteCustomer(id);

        Mockito.verify(customerRepository).findById(id);
    }

    @Test
    public void testDeleteCustomer_whenCustomerIdDoesNotExist_itShouldReturnThrowNotFoundException(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ServiceOperationException.NotFoundException.class,
                () -> customerService.deleteCustomer(id));

        Mockito.verify(customerRepository).findById(id);
    }



}