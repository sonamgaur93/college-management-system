package com.cms.serviceImpl;

import com.cms.dto.CollegeCourseDto;
import com.cms.entity.College;
import com.cms.entity.CollegeCourse;
import com.cms.entity.Course;
import com.cms.exception.GenericException;
import com.cms.mapper.CollegeCourseMapper;
import com.cms.repository.CollegeCourseRepository;
import com.cms.repository.CollegeRepository;
import com.cms.repository.CourseRepository;
import com.cms.service.CollegeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollegeCourseServiceImpl implements CollegeCourseService {

    @Autowired
    private CollegeCourseRepository collegeCourseRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CollegeCourseMapper collegeCourseMapper;


    @Override
    public CollegeCourse save(CollegeCourseDto collegeCourseDto, Long collegeId, Long courseId) {
        College college = collegeRepository.findById(collegeId).orElseThrow(() ->
                new GenericException("College id does not exist", HttpStatus.NOT_FOUND));

        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new GenericException("Course id does not exist", HttpStatus.NOT_FOUND));

        return collegeCourseMapper.toEntity(collegeCourseDto, college, course);
    }

    @Override
    public CollegeCourseDto update(Long id, Long collegeId, Long courseId, CollegeCourseDto collegeCourseDto) {
        CollegeCourse collegeCourse = collegeCourseRepository.findByCollegeAndCourseIdAndId(id, collegeId, courseId).orElseThrow(() ->
                new GenericException("College course id does not exist", HttpStatus.NOT_FOUND));

        collegeCourse.setFees(collegeCourseDto.getFees());
        collegeCourse.setTotalSeats(collegeCourseDto.getTotalSeats());
        collegeCourse.setAvailableSeats(collegeCourseDto.getAvailableSeats());

        if (collegeCourse.getCollege() != null) {
            collegeCourse.setCollege(collegeCourse.getCollege());
        }

        if (collegeCourse.getCourse() != null) {
            collegeCourse.setCourse(collegeCourse.getCourse());
        }
        collegeCourse.setUpdatedAt(LocalDateTime.now());
        collegeCourse = collegeCourseRepository.save(collegeCourse);

        return collegeCourseMapper.toDto(collegeCourse);
    }

    @Override
    public CollegeCourseDto getById(Long id, Long collegeId, Long courseId) {
        CollegeCourse collegeCourse = collegeCourseRepository.findByCollegeAndCourseIdAndId(id, collegeId, courseId).orElseThrow(() ->
                new GenericException("College course id does not exist", HttpStatus.NOT_FOUND));

        return collegeCourseMapper.toDto(collegeCourse);
    }

    @Override
    public List<CollegeCourseDto> getAll(Long collegeId, Long courseId) {
        List<CollegeCourse> collegeCourseList = collegeCourseRepository.findByCollegeAndCourseId(collegeId, courseId);
        List<CollegeCourseDto> collegeCourseDtos = new ArrayList<>();

        for (CollegeCourse collegeCourse : collegeCourseList) {
            CollegeCourseDto collegeCourseDto = collegeCourseMapper.toDto(collegeCourse);
            collegeCourseDtos.add(collegeCourseDto);
        }
        return collegeCourseDtos;
    }

    @Override
    public CollegeCourseDto delete(Long id, Long collegeId, Long courseId) {
        CollegeCourse collegeCourse = collegeCourseRepository.findByCollegeAndCourseIdAndId(id, collegeId, courseId).orElseThrow(() ->
                new GenericException("College course id does not exist", HttpStatus.NOT_FOUND));

        collegeCourse.setStatus(Boolean.FALSE);
        collegeCourseRepository.save(collegeCourse);

        return collegeCourseMapper.toDto(collegeCourse);
    }
}
