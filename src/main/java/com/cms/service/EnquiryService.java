package com.cms.service;

import com.cms.dto.EnquiryDto;
import com.cms.entity.Enquiry;

import java.util.List;

public interface EnquiryService {

    Enquiry save(EnquiryDto enquiryDto, Long collegeCourseId);

    EnquiryDto update(EnquiryDto enquiryDto, Long id, Long collegeCourseId);

    EnquiryDto getById(Long id, Long collegeCourseId);

    List<EnquiryDto> getAll(Long collegeCourseId,String search, int page, int size, String sortBy, String sortOrder);

    EnquiryDto delete(Long id,  Long collegeCourseId);
}
