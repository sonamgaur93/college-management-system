package com.cms.controller;

import com.cms.dto.RegisterUser;
import com.cms.entity.User;
import com.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUser registerUser) {
        return new ResponseEntity<>(userService.registerUser(registerUser), HttpStatus.OK);
    }
}
