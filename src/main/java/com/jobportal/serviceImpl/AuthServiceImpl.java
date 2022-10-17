package com.jobportal.serviceImpl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.dto.EmailOtpDto;
import com.jobportal.dto.ForgotPasswordConfirmDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.OtpEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.excetpion.ResourceNotFoundException;
import com.jobportal.repositories.AuthRepository;
import com.jobportal.repositories.OTPRepository;
import com.jobportal.serviceInterface.AuthInterface;

@Service
public class AuthServiceImpl implements AuthInterface, UserDetailsService {
	private static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private OTPRepository otpRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {

		UserEntity userEntity = this.authRepository.findByEmailContainingIgnoreCase(email);

		if (userEntity == null) {
			throw new ResourceNotFoundException("User OR Password not found");
		}

		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
				new ArrayList<>());
	}

	// for compare password
	@Override
	public Boolean comparePassword(String password, String hashPassword) {

		return passwordEncoder.matches(password, hashPassword);

	}

	@Override
	public void AddUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setEducation(userDto.getEducation());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setExperience(userDto.getExperience());
		userEntity.setName(userDto.getName());
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userEntity.setMobileNumber(userDto.getMobileNumber());
		this.authRepository.save(userEntity);
	}

	void forgotPassword(EmailOtpDto emailOtpDto) {
		UserEntity userEntity = this.authRepository.findByEmailContainingIgnoreCase(emailOtpDto.getEmail());

	}

	@Override
	public boolean forgotPasswordConfirm(ForgotPasswordConfirmDto passwordConfirmDto, UserEntity userEntity,
			OtpEntity otpEntity) throws Exception {
		LOG.info("AuthServiceImpl >> forgotPasswordConfirm() >> ");

		LOG.info("AuthServiceImpl >> forgotPasswordConfirm() >> check user email");

		userEntity.setPassword(passwordEncoder.encode(passwordConfirmDto.getPassword()));

		this.authRepository.save(userEntity);
		this.otpRepository.deleteAll();
		LOG.info("AuthServiceImpl >> forgotPasswordConfirm() >> Done password changed");
		return false;
	}

}
