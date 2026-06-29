package com.cms.serviceImpl;

import com.cms.dao.EnquiryDao;
import com.cms.dto.EnquiryDto;
import com.cms.entity.CollegeCourse;
import com.cms.entity.Enquiry;
import com.cms.exception.GenericException;
import com.cms.mapper.EnquiryMapper;
import com.cms.repository.CollegeCourseRepository;
import com.cms.repository.EnquiryRepository;
import com.cms.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private EnquiryMapper enquiryMapper;

    @Autowired
    private CollegeCourseRepository collegeCourseRepository;

    @Autowired
    private EnquiryDao enquiryDao;

    @Override
    public Enquiry save(EnquiryDto enquiryDto, Long collegeCourseId) {
        CollegeCourse collegeCourse = collegeCourseRepository.findById(collegeCourseId).orElseThrow(() ->
                new GenericException("College course id does not exist", HttpStatus.NOT_FOUND));

        return enquiryMapper.toEntity(enquiryDto, collegeCourse);
    }

    @Override
    public EnquiryDto update(EnquiryDto enquiryDto, Long id, Long collegeCourseId) {
        Enquiry enquiry = enquiryRepository.findByCollegeCourseIdAndId(collegeCourseId, id).orElseThrow(() ->
                new GenericException("College course id does not exist ", HttpStatus.NOT_FOUND));

        enquiry.setStudentName(enquiryDto.getStudentName());
        enquiry.setMessage(enquiryDto.getMessage());
        enquiry.setMobile(enquiryDto.getMobile());
        enquiry.setEmail(enquiryDto.getEmail());
        enquiry.setStatus(enquiryDto.getStatus());
        enquiry.setUpdatedAt(LocalDateTime.now());

        if (enquiry.getCollegeCourse() != null) {
            enquiry.setCollegeCourse(enquiry.getCollegeCourse());
        }
        enquiry = enquiryRepository.save(enquiry);

        return enquiryMapper.toDto(enquiry);
    }

    @Override
    public EnquiryDto getById(Long id, Long collegeCourseId) {
        Enquiry enquiry = enquiryRepository.findByCollegeCourseIdAndId(collegeCourseId, id).orElseThrow(() ->
                new GenericException("Enquiry id does not exist ", HttpStatus.NOT_FOUND));

        return enquiryMapper.toDto(enquiry);
    }

    @Override
    public List<EnquiryDto> getAll(Long collegeCourseId, String search, int page, int size, String sortBy, String sortOrder) {
        List<Enquiry> enquiryList = enquiryDao.findAllEnquiries(collegeCourseId, search, page, size, sortBy, sortOrder);
        List<EnquiryDto> enquiryDtos = new ArrayList<>();

        for (Enquiry enquiry : enquiryList) {
            EnquiryDto enquiryDto = enquiryMapper.toDto(enquiry);
            enquiryDtos.add(enquiryDto);
        }
        return enquiryDtos;
    }

    @Override
    public void delete(Long id, Long collegeCourseId) {
        Enquiry enquiry = enquiryRepository.findByCollegeCourseIdAndId(collegeCourseId, id).orElseThrow(() ->
                new GenericException("College course id does not exist ", HttpStatus.NOT_FOUND));

        enquiryRepository.delete(enquiry);
    }
}
