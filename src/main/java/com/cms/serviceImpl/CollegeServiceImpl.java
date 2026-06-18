package com.cms.serviceImpl;

import com.cms.dao.CollegeDao;
import com.cms.dto.CollegeDto;
import com.cms.entity.College;
import com.cms.exception.GenericException;
import com.cms.mapper.CollegeMapper;
import com.cms.repository.CollegeRepository;
import com.cms.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CollegeDao collegeDao;

    @Override
    public College save(CollegeDto collegeDto) {
        return collegeMapper.toEntity(collegeDto);
    }

    @Override
    public CollegeDto update(Long id, CollegeDto collegeDto) {
        College college = collegeRepository.findById(id).orElseThrow(() -> new
                GenericException("College id does not exist", HttpStatus.NOT_FOUND));
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
        college.setUpdatedAt(LocalDateTime.now());
        college = collegeRepository.save(college);

        return collegeMapper.toDto(college);
    }

    @Override
    public CollegeDto getById(Long id) {
        College college = collegeRepository.findById(id).orElseThrow(() -> new
                GenericException("College id does not exist", HttpStatus.NOT_FOUND));
        return collegeMapper.toDto(college);
    }

    @Override
    public List<CollegeDto> getAll(String search, Boolean status, int page, int size, String sortBy, String sortOrder) {
        List<College> colleges = collegeDao.findAllColleges(search, status, page, size, sortBy, sortOrder);
        List<CollegeDto> collegeDtos = new ArrayList<>();

        for (College college : colleges) {
            CollegeDto collegeDto = collegeMapper.toDto(college);
            collegeDtos.add(collegeDto);
        }
        return collegeDtos;
    }

    @Override
    public College delete(Long id) {
        College college = collegeRepository.findById(id).orElseThrow(() -> new
                GenericException("College id does not exist", HttpStatus.NOT_FOUND));

        college.setStatus(Boolean.FALSE);
        return collegeRepository.save(college);
    }
}
