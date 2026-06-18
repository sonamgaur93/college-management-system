package com.cms.controller;

import com.cms.constant.UriConstant;
import com.cms.dto.CollegeCourseDto;
import com.cms.entity.CollegeCourse;
import com.cms.service.CollegeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UriConstant.COLLEGE_COURSE)
public class CollegeCourseController {

    @Autowired
    private CollegeCourseService collegeCourseService;

    @PostMapping(UriConstant.SAVE)
    public ResponseEntity<CollegeCourse> save(@RequestBody CollegeCourseDto collegeCourseDto, @RequestParam("collegeId") Long collegeId,
                                              @RequestParam("courseId") Long courseId) {
        return new ResponseEntity<>(collegeCourseService.save(collegeCourseDto, collegeId, courseId), HttpStatus.OK);
    }

    @PutMapping(UriConstant.UPDATE)
    public ResponseEntity<CollegeCourseDto> update(@RequestBody CollegeCourseDto collegeCourseDto, @RequestParam("collegeId") Long collegeId,
                                                   @RequestParam("courseId") Long courseId, @PathVariable Long id) {
        return new ResponseEntity<>(collegeCourseService.update(id, collegeId, courseId, collegeCourseDto), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_BY_ID)
    public ResponseEntity<CollegeCourseDto> getById(@RequestParam("collegeId") Long collegeId,
                                                    @RequestParam("courseId") Long courseId, @PathVariable Long id) {
        return new ResponseEntity<>(collegeCourseService.getById(id, collegeId, courseId), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_ALL)
    public ResponseEntity<List<CollegeCourseDto>> GetAll(@RequestParam("collegeId") Long collegeId,
                                                         @RequestParam("courseId") Long courseId,
                                                         @RequestParam(required = false) String search,
                                                         @RequestParam(required = false) Boolean status,
                                                         @RequestParam(required = false) Boolean admissionOpen,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "createdAt") String sortBy,
                                                         @RequestParam(defaultValue = "desc") String sortOrder) {
        return new ResponseEntity<>(collegeCourseService.getAll(collegeId, courseId, search, status, admissionOpen, page, size, sortBy, sortOrder), HttpStatus.OK);
    }

    @DeleteMapping(UriConstant.DELETE + "/{id}")
    public ResponseEntity<CollegeCourseDto> delete(@RequestParam("collegeId") Long collegeId,
                                                   @RequestParam("courseId") Long courseId, @PathVariable Long id) {
        return new ResponseEntity<>(collegeCourseService.delete(id, collegeId, courseId), HttpStatus.OK);
    }
}
