package com.ies.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterForm {

    private String fullName;
    private String email;
    private Integer ssn;
    private LocalDate dob;
    private String gender;
    private Long phoneNumber;

}
