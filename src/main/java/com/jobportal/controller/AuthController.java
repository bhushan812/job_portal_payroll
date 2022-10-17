package com.jobportal.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.AuthResponceDto;
import com.jobportal.dto.EmailOtpDto;
import com.jobportal.dto.ErrorResponseDto;
import com.jobportal.dto.ForgotPasswordConfirmDto;
import com.jobportal.dto.JwtRequest;
import com.jobportal.dto.LoggerDto;
import com.jobportal.dto.SuccessResponseDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.OtpEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.excetpion.ResourceNotFoundException;
import com.jobportal.repositories.AuthRepository;
import com.jobportal.repositories.OTPRepository;
import com.jobportal.serviceInterface.AuthInterface;
import com.jobportal.serviceInterface.EmailServiceInterface;
import com.jobportal.serviceInterface.JwtTokenUtilInterface;
import com.jobportal.serviceInterface.LoggerInterface;
import com.jobportal.serviceInterface.OtpInterface;
import com.jobportal.utils.PasswordValidator;

@RestController
public class AuthController {
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private JwtTokenUtilInterface jwtTokenUtilInterface;
	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private AuthInterface authInterface;

	@Autowired
	private LoggerInterface loggerInterface;

	@Autowired
	EmailServiceInterface emailServiceInterface;
	@Autowired
	private OTPRepository otpRepository;
	@Autowired
	private OtpInterface otpInterface;

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception, DataIntegrityViolationException {

		String email = userDto.getEmail();
		String password = userDto.getPassword();

		if (PasswordValidator.isValid(password)) {

			UserEntity databaseName = authRepository.findByEmailContainingIgnoreCase(email);

			if (databaseName == null) {

				authInterface.AddUser(userDto);

				return new ResponseEntity<>(new SuccessResponseDto("User created", "userCreated", "Data added"),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(
						new ErrorResponseDto("User email id already exist", "userEmailIdAlreadyExist"),
						HttpStatus.BAD_REQUEST);
			}
		}

		else {

			return new ResponseEntity<>(new ErrorResponseDto(
					"Password should have Minimum 8 and maximum 50 characters, at least one uppercase letter, one lowercase letter, one number and one special character and no white spaces",
					"Password validation not done"), HttpStatus.BAD_REQUEST);

		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest createAuthenticationToken)
			throws Exception {
		try {

			UserEntity user = authRepository.findByEmailContainingIgnoreCase(createAuthenticationToken.getEmail());

			if (this.authInterface.comparePassword(createAuthenticationToken.getPassword(), user.getPassword())) {
				final UserDetails userDetails = this.authInterface
						.loadUserByUsername(createAuthenticationToken.getEmail());

				final UserEntity userEntity = this.authRepository
						.findByEmailContainingIgnoreCase(createAuthenticationToken.getEmail());

				final String token = this.jwtTokenUtilInterface.generateToken(userDetails);

				// ArrayList<String> permissions =
				// authInterface.getUserPermission(user.getId());

				LoggerDto loggerDto = new LoggerDto();

				loggerDto.setToken(token);

				Calendar calendar = Calendar.getInstance();

				calendar.add(Calendar.HOUR_OF_DAY, 5);

				loggerDto.setExpireAt(calendar.getTime());

				this.loggerInterface.createLogger(loggerDto, userEntity);

				return new ResponseEntity<>(new SuccessResponseDto("Login successfull", "token",
						new AuthResponceDto(token, userEntity.getEmail(), userEntity.getName(), userEntity.getId())),
						HttpStatus.OK); // permission add

			} else {
				return new ResponseEntity<>(new ErrorResponseDto("Invalid password", "Please enter valid password"),
						HttpStatus.BAD_REQUEST);

			}
		} catch (Exception e) {

			return new ResponseEntity<>(
					new ErrorResponseDto("Invalid email or Password", "Please enter valid email or password"),
					HttpStatus.BAD_REQUEST);
		}
	}

////forgot password 
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody EmailOtpDto emailOtpDto) {
		try {
			UserEntity userEntity = this.authRepository.findByEmailContainingIgnoreCase(emailOtpDto.getEmail());
			if (userEntity == null) {
				return new ResponseEntity<>(new ErrorResponseDto("User not found", "check your email"),
						HttpStatus.BAD_REQUEST);
			}

			this.emailServiceInterface.generateOtpAndSendEmail(emailOtpDto.getEmail(), userEntity.getId());
			return new ResponseEntity<>(
					new SuccessResponseDto("OTP send user email", "OTP send succesfully", userEntity.getEmail()),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("User not found", "Not found"), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/forgot-password-confirm")
	public ResponseEntity<?> forgotPasswordConfirm(@RequestBody ForgotPasswordConfirmDto forgotPasswordConfirmDto)
			throws Exception {

		try {
			UserEntity userEntity = this.authRepository
					.findByEmailContainingIgnoreCase(forgotPasswordConfirmDto.getEmail());

			OtpEntity otpEntity = this.otpRepository.findByOtp(forgotPasswordConfirmDto.getOtp());

			if (null == otpEntity) {
				return new ResponseEntity<>(new ErrorResponseDto("Check your OTP", "Enter valid OTP"),
						HttpStatus.BAD_REQUEST);
			} else {
				if (!otpEntity.getEmail().equals(forgotPasswordConfirmDto.getEmail())) {
					return new ResponseEntity<>(new ErrorResponseDto("Invalid email", "Enter valid email"),
							HttpStatus.BAD_REQUEST);
				}
			}

			if (!PasswordValidator.isValid(forgotPasswordConfirmDto.getPassword())) {
				return new ResponseEntity<>(new ErrorResponseDto(
						"Password should have Minimum 8 and maximum 50 characters, at least one uppercase letter, one lowercase letter, one number and one special character and no white spaces",
						"Enter valid password"), HttpStatus.BAD_REQUEST);
			}

			this.authInterface.forgotPasswordConfirm(forgotPasswordConfirmDto, userEntity, otpEntity);

			return new ResponseEntity<>(new SuccessResponseDto("Password updated successfully", "successfull"),
					HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Not found"), HttpStatus.BAD_REQUEST);
		}
	}
}
