package com.definexfinalcase.definexfinalcase.repository;

import com.definexfinalcase.definexfinalcase.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Long> {
}
