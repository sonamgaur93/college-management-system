package com.cms.repository;

import com.cms.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {

    @Query("select s from Enquiry s where s.collegeCourse.id =:collegeCourseId")
    List<Enquiry> findByCollegeCourseId(@Param("collegeCourseId") Long collegeCourseId);

    @Query(value = """
            SELECT * FROM enquiries s 
                WHERE s.id = :id AND (:collegeCourseId IS NULL OR s.college_course_id = :collegeCourseId)
            """, nativeQuery = true)
    Optional<Enquiry> findByCollegeCourseIdAndId(@Param("collegeCourseId") Long collegeCourseId,
                                                 @Param("id") Long id);
}
