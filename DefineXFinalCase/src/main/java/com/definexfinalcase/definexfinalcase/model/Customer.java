package com.definexfinalcase.definexfinalcase.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    @Column(name = "collateral")
    private String collateral;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "customer")
    List<Credit> credits;

    public Customer(String email, String password,
            String phoneNumber, String firstName, String lastName,
                    String nationalIdentity, String income, String collateral, LocalDate dateOfBirth) {
        super(email, password, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdentity = nationalIdentity;
        this.income = income;
        this.collateral = collateral;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer(Long id, String email, String password, String phoneNumber,
                    String firstName, String lastName, String nationalIdentity,
                    String income, String collateral, LocalDate dateOfBirth, List<Credit> credits) {
        super(id, email, password, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdentity = nationalIdentity;
        this.income = income;
        this.collateral = collateral;
        this.dateOfBirth = dateOfBirth;
        this.credits = credits;
    }

    public Customer(Long id, String email, String password, String phoneNumber,
                    String firstName, String lastName, String nationalIdentity,
                    String income, String collateral, LocalDate dateOfBirth) {
        super(id, email, password, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdentity = nationalIdentity;
        this.income = income;
        this.collateral = collateral;
        this.dateOfBirth = dateOfBirth;
    }


}
