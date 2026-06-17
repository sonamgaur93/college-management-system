package com.cms.controller;

import com.cms.constant.UriConstant;
import com.cms.dto.LoginRequest;
import com.cms.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UriConstant.AUTH)
public class AuthController {

    @Autowired
    private AuthenticationManagerBuilder authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(UriConstant.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        String role = authentication.getAuthorities()
                .stream()
                .findFirst()
                .get()
                .getAuthority();

        String token = jwtUtil.generateToken(username, role);

        return ResponseEntity.ok(token);
    }
}
