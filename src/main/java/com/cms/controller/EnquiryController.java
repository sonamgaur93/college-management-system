package com.cms.controller;

import com.cms.constant.UriConstant;
import com.cms.dto.EnquiryDto;
import com.cms.entity.Enquiry;
import com.cms.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UriConstant.ENQUIRY)
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @PostMapping(UriConstant.SAVE)
    public ResponseEntity<Enquiry> save(@RequestBody EnquiryDto enquiryDto, @RequestParam("collegeCourseId") Long collegeCourseId) {
        return new ResponseEntity<>(enquiryService.save(enquiryDto, collegeCourseId), HttpStatus.OK);
    }

    @PutMapping(UriConstant.UPDATE)
    public ResponseEntity<EnquiryDto> update(@RequestBody EnquiryDto enquiryDto, @RequestParam("collegeCourseId") Long collegeCourseId,
                                             @PathVariable Long id) {
        return new ResponseEntity<>(enquiryService.update(enquiryDto, id, collegeCourseId), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_BY_ID)
    public ResponseEntity<EnquiryDto> getById(@RequestParam("collegeCourseId") Long collegeCourseId, @PathVariable Long id) {
        return new ResponseEntity<>(enquiryService.getById(id, collegeCourseId), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_ALL)
    public ResponseEntity<List<EnquiryDto>> GetAll(@RequestParam("collegeCourseId") Long collegeCourseId,
                                                   @RequestParam(required = false) String search,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "createdAt") String sortBy,
                                                   @RequestParam(defaultValue = "desc") String sortOrder) {
        return new ResponseEntity<>(enquiryService.getAll(collegeCourseId, search, page, size, sortBy, sortOrder), HttpStatus.OK);
    }

    @DeleteMapping(UriConstant.DELETE + "/{id}")
    public void delete(@RequestParam("collegeCourseId") Long collegeCourseId, @PathVariable Long id) {
        enquiryService.delete(id, collegeCourseId);
    }
}
