package com.cms.repository;

import com.cms.entity.CollegeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollegeCourseRepository extends JpaRepository<CollegeCourse, Long> {

    @Query(value = "select * from college_courses s where s.college_id =:collegeId", nativeQuery = true)
    List<CollegeCourse> findByCollegeId(@Param("collegeId") Long collegeId);

    @Query(value = "select * from college_courses s where s.course_id =:courseId", nativeQuery = true)
    List<CollegeCourse> findByCourseId(@Param("courseId") Long courseId);

    @Query(value = """
            SELECT * FROM college_courses s 
                WHERE s.id = :id AND (:collegeId IS NULL OR s.college_id = :collegeId) AND 
                                  (:courseId IS NULL OR s.course_id = :courseId)
            """, nativeQuery = true)
    Optional<CollegeCourse> findByCollegeAndCourseIdAndId(
            @Param("id") Long id,
            @Param("collegeId") Long collegeId,
            @Param("courseId") Long courseId);
}
