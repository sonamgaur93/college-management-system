package com.cms.repository;

import com.cms.dto.ResponseDto;
import com.cms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select s.id as id ,s.courseName as name from Course s")
    List<ResponseDto> getCourseNameAndId();
}
