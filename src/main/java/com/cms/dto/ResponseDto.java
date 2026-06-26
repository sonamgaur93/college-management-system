package com.cms.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private Long id;

    private String name;

    public ResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
