package com.cms.service;

import com.cms.dto.CourseDto;
import com.cms.dto.ResponseDto;
import com.cms.entity.Course;

import java.util.List;

public interface CourseService {

    Course save(CourseDto courseDto);

    CourseDto update(Long id, CourseDto courseDto);

    CourseDto getById(Long id);

    List<CourseDto> getAll(String search, Boolean status, int page, int size, String sortBy, String sortOrder);

    void delete(Long id);

    List<ResponseDto> getIdAndName();

}
