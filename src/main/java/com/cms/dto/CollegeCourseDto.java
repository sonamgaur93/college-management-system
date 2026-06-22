package com.cms.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CollegeCourseDto {

    private Long id;

    private BigDecimal fees;

    private Integer totalSeats;

    private Integer availableSeats;

}
