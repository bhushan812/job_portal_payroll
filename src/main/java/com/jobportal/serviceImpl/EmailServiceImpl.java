package com.jobportal.serviceImpl;

import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jobportal.entity.UserEntity;
import com.jobportal.serviceInterface.EmailServiceInterface;
import com.jobportal.serviceInterface.OtpInterface;

@Service
public class EmailServiceImpl implements EmailServiceInterface {

	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	private OtpInterface otpInterface;

	@Override
	public String sendSimpleMessage(String emailTo, String subject, String text) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//		mimeMessageHelper.setFrom("noreplayntts@gmail.com");
//		mimeMessageHelper.setTo(emailTo);
//		mimeMessageHelper.setSubject(subject);
//		mimeMessageHelper.setText(text, true);
//		javaMailSender.send(mimeMessage);

		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom("noreplayntts@gmail.com");
			mailMessage.setTo(emailTo);
			mailMessage.setText(text);
			mailMessage.setSubject(subject);

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

	@Override
	public String sendMail(String emailTo, String subject, String text, UserEntity userEntity) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom("noreplayntts@gmail.com");
		simpleMailMessage.setTo(userEntity.getEmail());
		simpleMailMessage.setSubject("Apply succesfully");
		simpleMailMessage.setText(text);
		javaMailSender.send(simpleMailMessage);
		return "Email send";
	}

	@Override
	public int generateOTP() {
		int min = 1000;
		int max = 9999;
		int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);

		return randomInt;
	}

	@Override
	public void generateOtpAndSendEmail(String email, Long userId) throws MessagingException {
		int otp = generateOTP();
		Calendar calendar = Calendar.getInstance();
		String otp1 = Integer.toString(otp);

		final String url = otp1;
		calendar.add(Calendar.MINUTE, 5);
		this.otpInterface.saveOtp(email, otp, userId, calendar.getTime());
		this.sendSimpleMessage(email, "Your one time password OTP  ", otp1);
	}

}
