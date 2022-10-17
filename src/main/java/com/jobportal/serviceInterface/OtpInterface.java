package com.jobportal.serviceInterface;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.jobportal.entity.OtpEntity;

@Service
public interface OtpInterface {

	OtpEntity saveOtp(String email, Integer otp, Long userId, Date expiry);

}
