package com.definexfinalcase.definexfinalcase.repository;

import com.definexfinalcase.definexfinalcase.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
