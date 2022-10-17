package com.jobportal.serviceInterface;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.jobportal.entity.UserEntity;

@Service
public interface EmailServiceInterface {

	public String sendSimpleMessage(String emailTo, String subject, String text) throws MessagingException;

	public String sendMail(String emailTo, String subject, String text, UserEntity userEntity);

	public int generateOTP();

	void generateOtpAndSendEmail(String email, Long userId) throws MessagingException;
}
