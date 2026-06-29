package com.cms.dto;

import lombok.Data;

@Data
public class EnquiryDto {

    private Long id;

    private String studentName;

    private String mobile;

    private String email;

    private String message;

    private String status;

    private Long collegeCourseId;

    private String collegeName;

    private String courseName;

}
