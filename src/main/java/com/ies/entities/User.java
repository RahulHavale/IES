package com.ies.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fullName;
    private String email;
    private Long phoneNumber;
    private String password;
    private String role;
    private Integer ssn;
    private LocalDate dob;
    private String accStatus;
    private String gender;

}
