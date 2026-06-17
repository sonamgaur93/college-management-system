package com.cms.service;

import com.cms.dto.CollegeDto;
import com.cms.entity.College;

import java.util.List;

public interface CollegeService {

    College save(CollegeDto collegeDto);

    CollegeDto update(Long id, CollegeDto collegeDto);

    CollegeDto getById(Long id);

    List<CollegeDto> getAll();

    College delete(Long id);
}
