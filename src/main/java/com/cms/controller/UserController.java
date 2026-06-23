package com.cms.controller;

import com.cms.constant.UriConstant;
import com.cms.dto.UserDto;
import com.cms.entity.User;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UriConstant.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(UriConstant.SAVE)
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.OK);
    }

    @PutMapping(UriConstant.UPDATE)
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_BY_ID)
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping(UriConstant.GET_ALL)
    public ResponseEntity<List<UserDto>> GetAll(@RequestParam(required = false) String search,
                                                @RequestParam(required = false) Boolean status,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "createdAt") String sortBy,
                                                @RequestParam(defaultValue = "desc") String sortOrder) {
        return new ResponseEntity<>(userService.getAll(search, status, page, size, sortBy, sortOrder), HttpStatus.OK);
    }

    @DeleteMapping(UriConstant.DELETE + "/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
