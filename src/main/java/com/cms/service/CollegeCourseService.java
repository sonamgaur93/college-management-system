package com.cms.service;

import com.cms.dto.CollegeCourseDto;
import com.cms.entity.CollegeCourse;

import java.util.List;

public interface CollegeCourseService {

    CollegeCourse save(CollegeCourseDto collegeCourseDto, Long collegeId, Long courseId);

    CollegeCourseDto update(Long id, Long collegeId, Long courseId, CollegeCourseDto collegeCourseDto);

    CollegeCourseDto getById(Long id, Long collegeId, Long courseId);

    List<CollegeCourseDto> getAll(Long collegeId, Long courseId);

    CollegeCourseDto delete(Long id, Long collegeId, Long courseId);
}
