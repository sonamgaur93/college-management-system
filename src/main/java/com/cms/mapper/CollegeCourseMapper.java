package com.cms.mapper;

import com.cms.dto.CollegeCourseDto;
import com.cms.entity.College;
import com.cms.entity.CollegeCourse;
import com.cms.entity.Course;
import com.cms.repository.CollegeCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CollegeCourseMapper {

    @Autowired
    private CollegeCourseRepository collegeCourseRepository;

    public CollegeCourse toEntity(CollegeCourseDto collegeCourseDto, College college, Course course) {
        CollegeCourse collegeCourse = new CollegeCourse();
        collegeCourse.setFees(collegeCourseDto.getFees());
        collegeCourse.setTotalSeats(collegeCourseDto.getTotalSeats());
        collegeCourse.setAvailableSeats(collegeCourseDto.getAvailableSeats());
        collegeCourse.setCollege(college);
        collegeCourse.setCourse(course);
        collegeCourse.setCreatedAt(LocalDateTime.now());
        collegeCourse.setUpdatedAt(LocalDateTime.now());

        return collegeCourseRepository.save(collegeCourse);
    }

    public CollegeCourseDto toDto(CollegeCourse collegeCourse) {
        CollegeCourseDto collegeCourseDto = new CollegeCourseDto();
        collegeCourseDto.setFees(collegeCourse.getFees());
        collegeCourseDto.setTotalSeats(collegeCourse.getTotalSeats());
        collegeCourseDto.setAvailableSeats(collegeCourse.getAvailableSeats());

        return collegeCourseDto;
    }
}
