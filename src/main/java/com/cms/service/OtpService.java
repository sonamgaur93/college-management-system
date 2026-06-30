package com.cms.service;

import com.cms.dto.OtpRequest;

public interface OtpService {

    String generateOtp(OtpRequest otpRequest);

    boolean verifyOtp(OtpRequest otpRequest);

}