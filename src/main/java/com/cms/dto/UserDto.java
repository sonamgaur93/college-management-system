package com.cms.dto;

import lombok.Data;

@Data
public class UserDto {

    private String fullName;

    private String email;

    private String password;

    private String role;
}
