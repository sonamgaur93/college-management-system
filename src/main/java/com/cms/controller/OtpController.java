package com.cms.controller;

import com.cms.constant.UriConstant;
import com.cms.dto.OtpRequest;
import com.cms.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UriConstant.OTP)
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping(UriConstant.GENERATE)
    public ResponseEntity<String> generateOtp(@RequestBody OtpRequest otpRequest) {
        return new ResponseEntity<>(otpService.generateOtp(otpRequest), HttpStatus.OK);
    }

    @PostMapping(UriConstant.VERIFY)
    public boolean verifyOtp(@RequestBody OtpRequest otpRequest) {
        return otpService.verifyOtp(otpRequest);
    }
}
