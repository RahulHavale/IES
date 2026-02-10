package com.ies.dtos;

import lombok.Data;

@Data
public class UnlockForm {

    private String email;
    private String tempPzwd;
    private String pzwd;
}
