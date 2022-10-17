package com.jobportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.OtpEntity;

public interface OTPRepository extends JpaRepository<OtpEntity, Long> {
	OtpEntity findByOtp(Integer otp);

}
