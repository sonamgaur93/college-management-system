package com.cms.serviceImpl;

import com.cms.dao.CourseDao;
import com.cms.dto.CourseDto;
import com.cms.entity.Course;
import com.cms.exception.GenericException;
import com.cms.mapper.CourseMapper;
import com.cms.repository.CourseRepository;
import com.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseDao courseDao;

    @Override
    public Course save(CourseDto courseDto) {
        return courseMapper.toEntity(courseDto);
    }

    @Override
    public CourseDto update(Long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new GenericException("Course id does not exist", HttpStatus.NOT_FOUND));
        course.setCourseName(courseDto.getCourseName());
        course.setDuration(courseDto.getDuration());
        course.setDescription(courseDto.getDescription());
        course.setUpdatedAt(LocalDateTime.now());
        course = courseRepository.save(course);

        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new GenericException("Course id does not exist", HttpStatus.NOT_FOUND));

        return courseMapper.toDto(course);
    }

    @Override
    public List<CourseDto> getAll(String search, Boolean status, int page, int size, String sortBy, String sortOrder) {
        List<Course> courses = courseDao.findAllCourses(search,status,page,size,sortBy,sortOrder);
        List<CourseDto> courseDtos = new ArrayList<>();

        for (Course course : courses) {
            CourseDto courseDto = courseMapper.toDto(course);
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    @Override
    public Course delete(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new GenericException("Course id does not exist", HttpStatus.NOT_FOUND));

        course.setStatus(Boolean.FALSE);
        return courseRepository.save(course);
    }
}
