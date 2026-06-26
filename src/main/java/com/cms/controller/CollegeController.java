package com.cms.controller;

import com.cms.constant.UriConstant;
import com.cms.dto.CollegeDto;
import com.cms.dto.ResponseDto;
import com.cms.entity.College;
import com.cms.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UriConstant.COLLEGE)
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @PostMapping(UriConstant.SAVE)
    public ResponseEntity<College> save(@RequestBody CollegeDto collegeDto) {
        return new ResponseEntity<>(collegeService.save(collegeDto), HttpStatus.OK);
    }

    @PutMapping(UriConstant.UPDATE)
    public ResponseEntity<CollegeDto> update(@RequestBody CollegeDto collegeDto, @PathVariable Long id) {
        return new ResponseEntity<>(collegeService.update(id, collegeDto), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_BY_ID)
    public ResponseEntity<CollegeDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(collegeService.getById(id), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_ALL)
    public ResponseEntity<List<CollegeDto>> GetAll(@RequestParam(required = false) String search,
                                                   @RequestParam(required = false) Boolean status,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "createdAt") String sortBy,
                                                   @RequestParam(defaultValue = "desc") String sortOrder) {
        return new ResponseEntity<>(collegeService.getAll(search, status, page, size, sortBy, sortOrder), HttpStatus.OK);
    }

    @DeleteMapping(UriConstant.DELETE + "/{id}")
    public void delete(@PathVariable Long id) {
        collegeService.delete(id);
    }

    @GetMapping(UriConstant.GET_ID_NAME)
    public ResponseEntity<List<ResponseDto>> getIdAndName(){
        return new ResponseEntity<>(collegeService.getIdAndName(), HttpStatus.OK);
    }
}
