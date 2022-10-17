package com.jobportal.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.OtpEntity;
import com.jobportal.repositories.OTPRepository;
import com.jobportal.serviceInterface.OtpInterface;

@Service
public class OtpServiceImpl implements OtpInterface {

	@Autowired
	OTPRepository otpRepository;

	@Override
	public OtpEntity saveOtp(String email, Integer otp, Long userId, Date expiry) {

		OtpEntity otpEntity = new OtpEntity();

		otpEntity.setEmail(email);
		otpEntity.setOtp(otp);

		otpEntity.setUserId(userId);
		otpEntity.setExpireAt(expiry);
		this.otpRepository.save(otpEntity);
		return otpEntity;
	}

}
