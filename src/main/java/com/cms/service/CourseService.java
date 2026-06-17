package com.cms.service;

import com.cms.dto.CourseDto;
import com.cms.entity.Course;

import java.util.List;

public interface CourseService {

    Course save(CourseDto courseDto);

    CourseDto update(Long id, CourseDto courseDto);

    CourseDto getById(Long id);

    List<CourseDto> getAll();

    Course delete(Long id);
}
