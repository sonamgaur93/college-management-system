package com.cms.dto;

import lombok.Data;

@Data
public class RegisterUser {

    private String fullName;

    private String email;

    private String password;

    private String role;
}
