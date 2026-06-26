package com.cms.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CollegeCourseDto {

    private Long id;

    private BigDecimal fees;

    private Integer totalSeats;

    private Integer availableSeats;

    private String collegeName;

    private String courseName;

    private String courseDescription;

    private String courseDuration;

    private LocalDate startDate;
}
