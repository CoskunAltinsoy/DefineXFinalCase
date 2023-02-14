package com.definexfinalcase.definexfinalcase.repository;

import com.definexfinalcase.definexfinalcase.model.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Long> {
}
