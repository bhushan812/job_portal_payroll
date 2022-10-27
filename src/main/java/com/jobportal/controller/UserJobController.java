package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.IUserJobListDto;
import com.jobportal.dto.SuccessResponseDto;
import com.jobportal.dto.UserJobDto;
import com.jobportal.entity.UserEntity;
import com.jobportal.repositories.UserRepository;
import com.jobportal.serviceInterface.EmailServiceInterface;
import com.jobportal.serviceInterface.UserJobInterface;
import com.jobportal.utils.Comman;

@RestController
@RequestMapping("/user-jobs")
public class UserJobController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserJobInterface userJobInterface;
	@Autowired
	private EmailServiceInterface emailServiceInterface;

	@PreAuthorize("hasRole('applyMultipleJobs')")
	@PostMapping
	public ResponseEntity<?> applyMultipleJobs(@RequestAttribute(Comman.CUSTUM_ATTRIBUTE_USER_ID) Long userId,@RequestBody UserJobDto userJobDto) throws Exception {
		
		this.userJobInterface.applyJobs(userId, userJobDto);

		return new ResponseEntity<>(new SuccessResponseDto("Job applied sucessfully", "Sucess"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('getByUserIdJobsList')")
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getByUserIdJobsList(@PathVariable(name = "id") Long UserId,
			@RequestParam(defaultValue = "1") String PageNo, @RequestParam(defaultValue = "5") String PageSize) {
		System.err.println(1);
		
		Page<IUserJobListDto> iListUserDto = this.userJobInterface.getByUserIdJobsList(UserId, PageNo, PageSize);
		System.err.println(2);
		return new ResponseEntity<>(new SuccessResponseDto("All jobs", "Success", iListUserDto.getContent()),
				HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('getByJobIdUserList')")
	@GetMapping("/job")
	public ResponseEntity<?> getByJobIdUserList(@RequestParam(defaultValue = "")  Long jobId,
			@RequestParam(defaultValue = "1") String PageNo, @RequestParam(defaultValue = "5") String PageSize) {
		System.err.println(1);
		
		Page<IUserJobListDto> iListUserDto = this.userJobInterface.getByJobIdUserList(jobId, PageNo, PageSize);
		System.err.println(2);
		return new ResponseEntity<>(new SuccessResponseDto("All user", "Success", iListUserDto.getContent()),
				HttpStatus.OK);

	}
	
	
	
}
