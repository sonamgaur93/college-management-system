package com.cms.controller;

import com.cms.constant.UriConstant;
import com.cms.dto.CourseDto;
import com.cms.entity.Course;
import com.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UriConstant.COURSE)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(UriConstant.SAVE)
    public ResponseEntity<Course> save(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.save(courseDto), HttpStatus.OK);
    }

    @PutMapping(UriConstant.UPDATE)
    public ResponseEntity<CourseDto> update(@RequestBody CourseDto courseDto, @PathVariable Long id) {
        return new ResponseEntity<>(courseService.update(id, courseDto), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_BY_ID)
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getById(id), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_ALL)
    public ResponseEntity<List<CourseDto>> GetAll() {
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping(UriConstant.DELETE + "/{id}")
    public ResponseEntity<Course> delete(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.delete(id), HttpStatus.OK);
    }
}
