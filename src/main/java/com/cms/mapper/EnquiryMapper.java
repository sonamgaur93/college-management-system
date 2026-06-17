package com.cms.mapper;

import com.cms.dto.EnquiryDto;
import com.cms.entity.CollegeCourse;
import com.cms.entity.Enquiry;
import com.cms.repository.EnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EnquiryMapper {

    @Autowired
    private EnquiryRepository enquiryRepository;

    public Enquiry toEntity(EnquiryDto enquiryDto, CollegeCourse collegeCourse) {
        Enquiry enquiry = new Enquiry();
        enquiry.setStudentName(enquiryDto.getStudentName());
        enquiry.setMessage(enquiryDto.getMessage());
        enquiry.setMobile(enquiryDto.getMobile());
        enquiry.setEmail(enquiryDto.getEmail());
        enquiry.setStatus(enquiryDto.getStatus());
        enquiry.setCreatedAt(LocalDateTime.now());
        enquiry.setUpdatedAt(LocalDateTime.now());
        enquiry.setCollegeCourse(collegeCourse);

        return enquiryRepository.save(enquiry);
    }

    public EnquiryDto toDto(Enquiry enquiry) {
        EnquiryDto enquiryDto = new EnquiryDto();
        enquiryDto.setStudentName(enquiry.getStudentName());
        enquiryDto.setEmail(enquiry.getEmail());
        enquiryDto.setMobile(enquiry.getMobile());
        enquiryDto.setMessage(enquiry.getMessage());
        enquiryDto.setStatus(enquiry.getStatus());

        if (enquiry.getCollegeCourse() != null) {
            enquiryDto.setCollegeCourseId(enquiry.getCollegeCourse().getId());
        }

        return enquiryDto;
    }
}
