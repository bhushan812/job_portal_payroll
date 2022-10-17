package com.jobportal.serviceInterface;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jobportal.dto.ForgotPasswordConfirmDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.OtpEntity;
import com.jobportal.entity.UserEntity;

@Service
public interface AuthInterface {

	UserDetails loadUserByUsername(String email);

	public Boolean comparePassword(String password, String hashPassword);

	void AddUser(UserDto userDto);

	boolean forgotPasswordConfirm(ForgotPasswordConfirmDto passwordConfirmDto, UserEntity userEntity,
			OtpEntity otpEntity) throws Exception;
}
