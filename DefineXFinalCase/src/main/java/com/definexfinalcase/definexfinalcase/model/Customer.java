package com.definexfinalcase.definexfinalcase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends User {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "national_identity")
    private String nationalIdentity;
    @Column(name = "income")
    private String income;
    @Column(name = "guarantee")
    private double guarantee;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "customer")
    List<Credit> credits;

    public Customer(String email, String password,
            String phoneNumber, String firstName, String lastName,
                    String nationalIdentity, String income, double guarantee, LocalDate dateOfBirth) {
        super(email, password, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdentity = nationalIdentity;
        this.income = income;
        this.guarantee = guarantee;
        this.dateOfBirth = dateOfBirth;
    }
}
