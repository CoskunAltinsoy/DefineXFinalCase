package com.definexfinalcase.definexfinalcase.repository;

import com.definexfinalcase.definexfinalcase.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
   Optional<Customer> findCustomerByNationalIdentityAndDateOfBirth(String nationalIdentity, LocalDate dateOfBirth);
}
