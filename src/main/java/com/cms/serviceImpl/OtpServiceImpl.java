package com.cms.serviceImpl;

import com.cms.dto.OtpRequest;
import com.cms.entity.Otp;
import com.cms.entity.User;
import com.cms.exception.GenericException;
import com.cms.repository.OtpRepository;
import com.cms.repository.UserRepository;
import com.cms.service.OtpService;
import com.cms.util.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public String generateOtp(OtpRequest otpRequest) {
        User user = userRepository.findByMobileNo(otpRequest.getMobileNumber()).orElseThrow(() -> new
                GenericException("User not found", HttpStatus.NOT_FOUND));

        otpRepository.deleteByUserAndVerifiedFalse(user);
        String otp = OtpGenerator.generateOTP();

        Otp otpEntity = new Otp();

        otpEntity.setUser(user);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedAt(LocalDateTime.now());
        otpEntity.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otpEntity.setAttempts(0);
        otpEntity.setVerified(false);

        otpRepository.save(otpEntity);
        System.out.println("otp" + "  " + otp);
        return otp;
    }

    @Override
    public boolean verifyOtp(OtpRequest otpRequest) {
        User user = userRepository.findByMobileNo(otpRequest.getMobileNumber())
                .orElseThrow(() -> new GenericException("User not found", HttpStatus.NOT_FOUND));

        Otp otpEntity = otpRepository
                .findByUserAndVerifiedFalse(user)
                .orElseThrow(() -> new GenericException("Otp not found", HttpStatus.NOT_FOUND));

        if (otpEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new GenericException("OTP is expired", HttpStatus.BAD_REQUEST);
        }

        if (!otpEntity.getOtp().equals(otpRequest.getOtp())) {
            otpEntity.setAttempts(otpEntity.getAttempts() + 1);
            otpRepository.save(otpEntity);
            return false;
        }

        otpRepository.delete(otpEntity);
        return true;
    }
}
