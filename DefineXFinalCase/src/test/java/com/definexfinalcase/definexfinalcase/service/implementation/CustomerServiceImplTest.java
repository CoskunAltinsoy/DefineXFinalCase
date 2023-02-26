package com.definexfinalcase.definexfinalcase.service.implementation;

import com.definexfinalcase.definexfinalcase.dto.converter.CustomerConverter;
import com.definexfinalcase.definexfinalcase.dto.request.CreateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.request.UpdateCustomerRequest;
import com.definexfinalcase.definexfinalcase.dto.response.CustomerDto;
import com.definexfinalcase.definexfinalcase.exception.ServiceOperationException;
import com.definexfinalcase.definexfinalcase.model.Customer;
import com.definexfinalcase.definexfinalcase.repository.CustomerRepository;
import com.definexfinalcase.definexfinalcase.service.CustomerService;
import com.definexfinalcase.definexfinalcase.util.result.Result;
import com.definexfinalcase.definexfinalcase.util.result.SuccessResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
    public void whenCreateCustomer_itShouldReturnSuccessResult(){

        Mockito.when(customerConverter.convertToEntity(createCustomerRequest)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        Result result = customerService.createCustomer(createCustomerRequest);

        assertTrue(result.isSuccess());

        Mockito.verify(customerConverter).convertToEntity(createCustomerRequest);
        Mockito.verify(customerRepository).save(customer);
    }

    @Test
    public void testUpdateCustomer_whenCustomerIdExist_itShouldReturnSuccessResult(){

        Mockito.when(customerConverter.convertToEntity(updateCustomerRequest)).thenReturn(customer);
        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        Result result = customerService.updateCustomer(updateCustomerRequest);

        assertTrue(result.isSuccess());

        Mockito.verify(customerConverter).convertToEntity(updateCustomerRequest);
        Mockito.verify(customerRepository).findById(id);
        Mockito.verify(customerRepository).save(customer);

    }

    @Test
    public void testUpdateCustomer_whenCustomerIdDoesNotExist_itShouldReturnSuccessResult(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ServiceOperationException.NotFoundException.class,
                () -> customerService.updateCustomer(updateCustomerRequest));

        Mockito.verify(customerRepository).findById(id);
        Mockito.verifyNoMoreInteractions(customerRepository);
    }
    @Test
    public void testDeleteCustomer_whenCustomerIdExist_itShouldReturnSuccessResult(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        Result result = customerService.deleteCustomer(id);

        assertTrue(result.isSuccess());

        Mockito.verify(customerRepository).findById(id);
    }

    @Test
    public void testDeleteCustomer_whenCustomerIdDoesNotExist_itShouldReturnSuccessResult(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ServiceOperationException.NotFoundException.class,
                () -> customerService.deleteCustomer(id));

        Mockito.verify(customerRepository).findById(id);
    }

    @Test
    public void testGetAllUsers_itShouldReturnSuccessResult(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        Mockito.when(customerConverter.convertToDto(customer)).thenReturn(customerDto);

        Result result = customerService.findCustomerById(id);
        assertTrue(result.isSuccess());

        Mockito.verify(customerRepository).findById(id);
        Mockito.verify(customerConverter).convertToDto(customer);
    }

    @Test
    public void testGetAllUsers_whenCustomerIdDoesNotExist_itShouldReturnSuccessResult(){

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ServiceOperationException.NotFoundException.class,
                () -> customerService.findCustomerById(id));

        Mockito.verify(customerRepository).findById(id);
    }

}