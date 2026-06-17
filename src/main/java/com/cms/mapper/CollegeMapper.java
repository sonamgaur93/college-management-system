package com.cms.mapper;

import com.cms.dto.CollegeDto;
import com.cms.entity.College;
import com.cms.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CollegeMapper {

    @Autowired
    private CollegeRepository collegeRepository;

    public College toEntity(CollegeDto collegeDto) {
        College college = new College();
        college.setCollegeName(collegeDto.getCollegeName());
        college.setCode(collegeDto.getCode());
        college.setAddress(collegeDto.getAddress());
        college.setCity(collegeDto.getCity());
        college.setState(collegeDto.getState());
        college.setPincode(collegeDto.getPincode());
        college.setMobile(collegeDto.getMobile());
        college.setEmail(collegeDto.getEmail());
        college.setWebsite(collegeDto.getWebsite());
        college.setLogo(collegeDto.getLogo());
        college.setDescription(collegeDto.getDescription());
        college.setCreatedAt(LocalDateTime.now());
        college.setUpdatedAt(LocalDateTime.now());

        return collegeRepository.save(college);
    }

    public CollegeDto toDto(College college) {
        CollegeDto collegeDto = new CollegeDto();
        collegeDto.setCollegeName(college.getCollegeName());
        collegeDto.setCode(college.getCode());
        collegeDto.setAddress(college.getAddress());
        collegeDto.setCity(college.getCity());
        collegeDto.setState(college.getState());
        collegeDto.setPincode(college.getPincode());
        collegeDto.setMobile(college.getMobile());
        collegeDto.setEmail(college.getEmail());
        collegeDto.setWebsite(college.getWebsite());
        collegeDto.setLogo(college.getLogo());
        collegeDto.setDescription(college.getDescription());

        return collegeDto;
    }
}
