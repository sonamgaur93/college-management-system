package com.cms.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String fullName;

    private String email;

    private String password;

    private String role;
}
