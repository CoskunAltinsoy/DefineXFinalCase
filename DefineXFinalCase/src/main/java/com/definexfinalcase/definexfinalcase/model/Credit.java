package com.definexfinalcase.definexfinalcase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credits")
public class Credit extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "credit_type")
    private String creditType;
    @Column(name = "credit_limit")
    private double creditLimit;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Credit(LocalDateTime createdDate, String creditType,
                  double creditLimit, String description, Customer customer) {
        super(createdDate);
        this.creditType = creditType;
        this.creditLimit = creditLimit;
        this.description = description;
        this.customer = customer;
    }

    public Credit(LocalDateTime createdDate, String creditType, double creditLimit, String description) {
        super(createdDate);
        this.creditType = creditType;
        this.creditLimit = creditLimit;
        this.description = description;
    }
}
