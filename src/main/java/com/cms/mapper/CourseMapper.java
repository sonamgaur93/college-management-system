package com.cms.mapper;

import com.cms.dto.CourseDto;
import com.cms.entity.Course;
import com.cms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CourseMapper {

    @Autowired
    private CourseRepository courseRepository;

    public Course toEntity(CourseDto courseDto) {
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setDuration(courseDto.getDuration());
        course.setDescription(courseDto.getDescription());
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());

        return courseRepository.save(course);
    }

    public CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDescription(course.getDescription());
        courseDto.setDuration(course.getDuration());

        return courseDto;
    }
}
