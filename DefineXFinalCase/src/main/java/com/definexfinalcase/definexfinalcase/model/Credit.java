package com.definexfinalcase.definexfinalcase.model;

import com.definexfinalcase.definexfinalcase.model.enums.CreditStatus;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "credit_Status")
    private CreditStatus creditStatus;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Credit(LocalDateTime createdDate, String creditType,
                  double creditLimit, CreditStatus creditStatus, String description, Customer customer) {
        super(createdDate);
        this.creditType = creditType;
        this.creditLimit = creditLimit;
        this.creditStatus = creditStatus;
        this.description = description;
        this.customer = customer;
    }

    public Credit(LocalDateTime createdDate, String creditType,
               String description) {
        super(createdDate);
        this.creditType = creditType;
        this.description = description;
    }

    public Credit(String creditType, String description, Customer customer) {
        this.creditType = creditType;
        this.description = description;
        this.customer = customer;
    }
}
