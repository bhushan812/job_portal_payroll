package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

@RestController
@RequestMapping("/user-jobs")
public class UserJobController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserJobInterface userJobInterface;
	@Autowired
	private EmailServiceInterface emailServiceInterface;

	@PostMapping
	public ResponseEntity<?> applyMultipleJobs(@RequestBody UserJobDto userJobDto) throws Exception {
		// try {
		Long userEntity = userJobDto.getUserId();
		UserEntity userEntity2 = this.userRepository.findById(userEntity).orElseThrow();
		System.err.println("userINFO: "+userEntity2);
		String email = userEntity2.getEmail();
		this.userJobInterface.applyMultipleJob(userJobDto);

		emailServiceInterface.sendSimpleMessage(email, "Apna jobs", "Job applied sucessfully");
		return new ResponseEntity<>(new SuccessResponseDto("Job applied sucessfully", "Sucess"), HttpStatus.CREATED);
//		} catch (ResourceNotFoundException e) {
//			return new ResponseEntity<>(new ErrorResponseDto("You already applied for this position", "check new jo"),
//					HttpStatus.BAD_REQUEST);
//		}
	}
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getByUserIdJobsList(@PathVariable(name = "id") Long UserId,
			@RequestParam(defaultValue = "1") String PageNo, @RequestParam(defaultValue = "5") String PageSize) {
		System.err.println(1);
		
		Page<IUserJobListDto> iListUserDto = this.userJobInterface.getByUserIdJobsList(UserId, PageNo, PageSize);
		System.err.println(2);
		return new ResponseEntity<>(new SuccessResponseDto("All jobs", "Success", iListUserDto.getContent()),
				HttpStatus.OK);

	}
	
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
